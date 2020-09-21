import librosa
import numpy as np
from sklearn.model_selection import train_test_split
import os
from keras.models import Sequential, load_model, save_model
from keras.layers import Dense, Dropout

DATA_PATH = ('C:\\ssafy\\project2\\pjt2\\s03p22a509\\AI\\Voice\\data')
X_train = []
X_test = []
Y_train = []
Y_test = []
total_class = 0


def load_wave_generator(path):
    X_data = []
    Y_label = []
    global X_train, X_test, Y_train, Y_test, total_class

    folders = os.listdir(path)

    for folder in folders:
        if not os.path.isdir(path): continue
        files = os.listdir(path + "/" + folder)

        for f in files:
            if f.endswith('.wav'):
                y, sr = librosa.load(path + "/" + folder + "/" + f)
                mfcc = librosa.feature.mfcc(y=y, sr=sr, n_mfcc=13, hop_length=int(sr * 0.01), n_fft=int(sr * 0.02)).T

                X_data.extend(mfcc)

                label = [0 for i in range(len(folders))]
                label[total_class] = 1

                for i in range(len(mfcc)):
                    Y_label.append(label)

        total_class += 1

    print('total class ', total_class)

    X_train, X_test, Y_train, Y_test = train_test_split(np.array(X_data), np.array(Y_label))

    xy_data = (X_train, X_test, Y_train, Y_test)
    np.save("./xy_data.npy", xy_data)


load_wave_generator(DATA_PATH)

X_val, X_test, Y_val, Y_test = train_test_split(X_test, Y_test, test_size=0.8, random_state=1)

print("X_train :", np.shape(X_train))
print("Y_train :", np.shape(Y_train))
print("X_test :", np.shape(X_test))
print("Y_test :", np.shape(Y_test))
print("X_val :", np.shape(X_val))
print("Y_val :", np.shape(Y_val))

np.random.seed(777)

model = Sequential()

model.add(Dense(128, input_shape=(13,), activation='relu'))

model.add(Dense(256, activation='relu'))
model.add(Dropout(0.4))

model.add(Dense(512, activation='relu'))
model.add(Dropout(0.5))

model.add(Dense(256, activation='relu'))
model.add(Dropout(0.4))

model.add(Dense(128, activation='relu'))
model.add(Dropout(0.4))

model.add(Dense(64, activation='relu'))
model.add(Dropout(0.3))

model.add(Dense(32, activation='relu'))
model.add(Dropout(0.3))

model.add(Dense(total_class, activation='softmax'))

model.compile(loss='binary_crossentropy', optimizer='Adam', metrics=['accuracy'])
model.summary()
trained_model = model.fit(X_train, Y_train,
                          batch_size=32,
                          epochs=50,
                          validation_data=(X_val, Y_val),
                          shuffle=True)

# 모델 저장하기 - pip install h5py 해야함
model.save("C:\ssafy\project2\pjt2\s03p22a509\AI\Voice\model0915.h5")

# 모델 불러오기
# model = load_model("C:\ssafy\project2\pjt2\s03p22a509\AI\Voice\model0915.h5")

res = model.evaluate(X_test, Y_test)

y, sr = librosa.load(os.path.join("C:\ssafy\project2\pjt2\s03p22a509\AI\Voice\\test\박보영test.wav"))

X_test = librosa.feature.mfcc(y=y, sr=sr, n_mfcc=13, hop_length=int(sr * 0.01), n_fft=int(sr * 0.02)).T

output = model.predict(X_test, steps=1)

res = np.sum(output, axis=0) / np.sum(output) * 100

'''
0 박보영
1 승철
2 청하
3 정국
4 우영
5 찬희
6 민니
7 류진
8 소미
'''

print(0, '박보영 /', 1, '유인나 /', 2, '청하 /', 3, '정국 /', 4, '우영 /', 5, '찬희 /', 6, '민니 /', 7, '류진 /', 8, '소미')

if np.max(res) > 50:
    print("result : ", np.argmax(res))
else:
    print("uncertain result :", np.argmax(res))
print(res)
