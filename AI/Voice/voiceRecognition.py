import librosa
import numpy as np
from sklearn.model_selection import train_test_split
import os
from keras.models import Sequential, load_model, save_model
from keras.layers import Dense, Dropout
from keras.optimizers import Adam

DATA_PATH = ("C:\ssafy\project2\pjt2\s03p22a509\AI\Voice\data")
X_train = []
X_test = []
Y_train = []
Y_test = []
total_class = 0


def load_wave_generator(path):
    batch_waves = []
    labels = []
    X_data = []
    Y_label = []
    global X_train, X_test, Y_train, Y_test, total_class

    folders = os.listdir(path)

    for folder in folders:
        if not os.path.isdir(path): continue  # 폴더가 아니면 continue
        files = os.listdir(path + "/" + folder)
        # 폴더 이름과 그 폴더에 속하는 파일 갯수 출력
        for wav in files:
            if not wav.endswith(".wav"):
                continue
            elif wav.endswith('.wav'):
                # print("Filename :",wav)#.wav 파일이 아니면 continue
                y, sr = librosa.load(path + "/" + folder + "/" + wav)
                mfcc = librosa.feature.mfcc(y=y, sr=sr, n_mfcc=13, hop_length=int(sr * 0.01), n_fft=int(sr * 0.02)).T

                X_data.extend(mfcc)
                # print(len(mfcc))

                label = [0 for i in range(len(folders))]
                label[total_class] = 1

                for i in range(len(mfcc)):
                    Y_label.append(label)
                # print(Y_label)
            else:
                y, sr = librosa.load(path + "/" + folder + "/" + mp3)
                mfcc = librosa.feature.mfcc(y=y, sr=sr, n_mfcc=13, hop_length=int(sr * 0.01), n_fft=int(sr * 0.02)).T

                X_data.extend(mfcc)
                # print(len(mfcc))

                label = [0 for i in range(len(folders))]
                label[total_class] = 1

                for i in range(len(mfcc)):
                    Y_label.append(label)
        total_class = total_class + 1
    # end loop
    #     print("X_data :",np.shape(X_data))
    #     print("Y_label :",np.shape(Y_label))
    X_train, X_test, Y_train, Y_test = train_test_split(np.array(X_data), np.array(Y_label))

    xy = (X_train, X_test, Y_train, Y_test)
    np.save("./data.npy", xy)


load_wave_generator(DATA_PATH)

print(total_class, "개의 클래스")

X_val, X_test, Y_val, Y_test = train_test_split(X_test, Y_test, test_size=0.8, random_state=1)

print("X_train :", np.shape(X_train))
print("Y_train :", np.shape(Y_train))
print("X_test :", np.shape(X_test))
print("Y_test :", np.shape(Y_test))
print("X_val :", np.shape(X_val))
print("Y_val :", np.shape(Y_val))

np.random.seed(777)

model = Sequential()

model.add(Dense(256, input_shape=(13,), activation='relu', name='d1'))

model.add(Dense(512, activation='relu', name='d2'))
model.add(Dropout(0.5))

model.add(Dense(256, activation='relu', name='d3'))
model.add(Dropout(0.4))

model.add(Dense(128, activation='relu', name='d4'))
model.add(Dropout(0.4))

model.add(Dense(64, activation='relu', name='d5'))
model.add(Dropout(0.3))

model.add(Dense(32, activation='relu', name='d6'))
model.add(Dropout(0.3))

model.add(Dense(total_class, activation='softmax', name='d7'))

model.compile(loss='binary_crossentropy', optimizer='Adam', metrics=['accuracy'])
model.summary()
trained_model = model.fit(X_train, Y_train,
                          batch_size=32,
                          epochs=10,
                          validation_data=(X_val, Y_val),
                          shuffle=True)

#모델 저장하기 - pip install h5py 해야함
model.save("C:\ssafy\project2\pjt2\s03p22a509\AI\Voice\model0915.h5")
# 모델 불러오기
# pmodel = load_model("C:\ssafy\project2\pjt2\s03p22a509\AI\Voice\model0915.h5")

res = model.evaluate(X_test, Y_test)

y, sr = librosa.load(os.path.join("C:\ssafy\project2\pjt2\s03p22a509\AI\Voice\\test_청하.wav"))

X_test = librosa.feature.mfcc(y=y, sr=sr, n_mfcc=13, hop_length=int(sr * 0.01), n_fft=int(sr * 0.02)).T

output = model.predict(X_test, steps=1)

'''
0 유인나
1 배철수
2 이재은
3 최일구
4 문재인 대통령
5 청하
'''

res = np.sum(output, axis=0) / np.sum(output) * 100

if np.max(res) > 50:
    print("result : ", np.argmax(res))
else:
    print("no result")
    print(res)
