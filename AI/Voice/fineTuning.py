'''
결론 -> fine tuning ...,., 못할 거 같은ㄷㅔ??

그냥 mfcc 데이터를 디비에 저장하고
매번 그거 불러와서 훈련 시키는 건 ㅇㅓ떨지,,
용량이 글케 클 거 같지는 않은데

mfcc 데이터는 이런 식으로 생김
[-9.0882562e+02  0.0000000e+00  0.0000000e+00 ...  0.0000000e+00
   0.0000000e+00  0.0000000e+00]
 [-9.0882562e+02  0.0000000e+00  0.0000000e+00 ...  0.0000000e+00
   0.0000000e+00  0.0000000e+00]
 ...
 [-6.9728809e+02  7.3600662e+01 -2.9227192e+01 ...  3.2631698e+00
   1.7435810e-01  9.6503925e+00]
 [-7.4111652e+02  5.8638481e+01 -3.3538574e+01 ...  2.7156784e+01
   1.5752094e+01  2.6518044e+00]
 [-7.6090662e+02  9.0644150e+01  3.0132837e+00 ...  1.5083523e+01
  -4.5183194e-01  2.8305345e+00]]
'''


import librosa
import numpy as np
from sklearn.model_selection import train_test_split
import os
from keras.models import load_model, Model
from keras.layers import Dense, Flatten

DATA_PATH = ("C:\ssafy\project2\pjt2\s03p22a509\AI\Voice\data")
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
        if not os.path.isdir(path): continue  # 폴더가 아니면 continue
        files = os.listdir(path + "/" + folder)
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

np.random.seed(77)

pmodel = load_model("C:\ssafy\project2\pjt2\s03p22a509\AI\Voice\model0915.h5")
for layer in pmodel.layers:
    layer.trainable = False  # 설계된 vgg16 모델의 w,b를 업데이트 안 하겠다

pmodel.summary()

pmodel_dense5 = pmodel.get_layer('d6').output
FeatureFlatten = Flatten()(pmodel_dense5)
dense = Dense(10, name='dense10', activation='relu')(FeatureFlatten)

predictions = Dense(1, activation='softmax')(dense)
New_pmodel = Model(inputs=pmodel.input, outputs=predictions)
New_pmodel.compile(optimizer='rmsprop', loss='binary_crossentropy', metrics=['accuracy'])

New_pmodel.summary()

history = New_pmodel.fit(X_train, Y_train, steps_per_epoch=32, epochs=10)

#

y, sr = librosa.load("C:\ssafy\project2\pjt2\s03p22a509\AI\Voice\\test_청하.wav")

X_test = librosa.feature.mfcc(y=y, sr=sr, n_mfcc=13, hop_length=int(sr * 0.01), n_fft=int(sr * 0.02)).T
print(X_test)
output = New_pmodel.predict(X_test, steps=1)

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
