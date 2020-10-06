import numpy as np
import os
import librosa
import sys


def main(argv):
    code = argv[1]  # 기관명
    name = argv[2]  # 이름 + 폰번호

    print(code, name)

    default_path = os.path.join(os.path.join(os.path.abspath(__file__)).replace('mfcctonpy.py',''), code)
    
    # default_path = os.path.join(os.path.join("C:\ssafy\project2\pjt3\s03p23a509\AI\Voice", code))

    DATA_PATH = os.path.join(default_path, 'data')
    new_path = os.path.join(DATA_PATH, name)
    print(new_path)

    load_wave_generator(new_path)


# wav -> mfcc, mfcc -> .npy 로 변환해서 저장함


def load_wave_generator(path):
    files = os.listdir(path)
    for file in files:
        if file.endswith('.wav'):
            y, sr = librosa.load(path + "/" + file)
            mfcc = librosa.feature.mfcc(y=y, sr=sr, n_mfcc=13, hop_length=int(sr * 0.01), n_fft=int(sr * 0.02)).T

            numbers = np.array(mfcc)

            np.save(os.path.join(path, 'data.npy'), arr=numbers)
            npyarr = np.load(os.path.join(path, 'data.npy'))
            print(npyarr)


if __name__ == "__main__":
    main(sys.argv)

#
# def load_wave_generator(path):
#     X_data = []
#     Y_label = []
#     global X_train, X_test, Y_train, Y_test, total_class
#
#     folders = os.listdir(path)
#
#     for folder in folders:
#         if not os.path.isdir(path): continue
#         files = os.listdir(path + "/" + folder)
#
#         for f in files:
#             if f.endswith('.wav'):
#                 y, sr = librosa.load(path + "/" + folder + "/" + f)
#                 mfcc = librosa.feature.mfcc(y=y, sr=sr, n_mfcc=13, hop_length=int(sr * 0.01), n_fft=int(sr * 0.02)).T
#
#                 numbers = np.array(mfcc)
#
#                 np.save(os.path.join(os.path.join('C:\\ssafy\\project2\\pjt3\\s03p23a509\\AI\\Voice\\data', str(total_class)), 'data.npy'), arr=numbers)
#
#                 npyarr = np.load(os.path.join(os.path.join('C:\\ssafy\\project2\\pjt3\\s03p23a509\\AI\\Voice\\data', str(total_class)), 'data.npy'))
#
#                 print(npyarr)
#
#                 print('=====================================================================================')
#
#         total_class += 1
#
#
# load_wave_generator(DATA_PATH)
