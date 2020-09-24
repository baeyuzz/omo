import numpy as np
import os
import librosa


# wav -> mfcc, mfcc -> .npy 로 변환해서 저장함

DATA_PATH = ('C:\\ssafy\\project2\\pjt3\\s03p23a509\\AI\\Voice\\data')

i = 0
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

                numbers = np.array(mfcc)

                np.save(os.path.join(os.path.join('C:\\ssafy\\project2\\pjt3\\s03p23a509\\AI\\Voice\\data', str(total_class)), 'data.npy'), arr=numbers)

                npyarr = np.load(os.path.join(os.path.join('C:\\ssafy\\project2\\pjt3\\s03p23a509\\AI\\Voice\\data', str(total_class)), 'data.npy'))

                print(npyarr)

                print('==================================================')

        total_class += 1


load_wave_generator(DATA_PATH)
