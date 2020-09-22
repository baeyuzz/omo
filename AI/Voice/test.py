import librosa
import numpy as np
import os
from keras.models import load_model

model = load_model("C:\ssafy\project2\pjt3\s03p23a509\AI\Voice\model0915.h5")

y, sr = librosa.load("C:\ssafy\project2\pjt3\s03p23a509\AI\Voice\\test\소미test.wav")

X_test = librosa.feature.mfcc(y=y, sr=sr, n_mfcc=13, hop_length=int(sr * 0.01), n_fft=int(sr * 0.02)).T

print(X_test)

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
9 유진
'''

print(0, '박보영 /', 1, '승철 /', 2, '청하 /', 3, '정국 /', 4, '우영 /', 5, '찬희 /', 6, '민니 /', 7, '류진 /', 8, '소미 /',9,'유진')


if np.max(res) > 50:
    print("result : ", np.argmax(res))
else:
    print("uncertain result :", np.argmax(res))
print(res)


