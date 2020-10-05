import numpy as np
from sklearn.model_selection import train_test_split
from keras.models import Sequential
from keras.layers import Dense, Dropout
import os
import sys


def main(argv):
    # global X_train, X_test, Y_train, Y_test, total_class

    X_train = []
    X_test = []
    Y_train = []
    Y_test = []
    total_class = 0

    # path 설정
    code = argv[1]  # 기관명

    default_path = os.path.join(os.path.join("C:\ssafy\project2\pjt3\s03p23a509\AI\Voice", code))
    model_path = os.path.join(os.path.join("C:\ssafy\project2\pjt3\s03p23a509\AI\Voice", code), 'model.h5')
    DATA_PATH = os.path.join(default_path, 'data')

    # load_wave_generator(DATA_PATH)

    # voice data 불러옴
    X_data = []
    Y_label = []

    folders = os.listdir(DATA_PATH)

    for folder in folders:
        if not os.path.isdir(DATA_PATH): continue
        files = os.listdir(DATA_PATH + "/" + folder)

        for f in files:
            if f.endswith('.npy'):
                # y, sr = librosa.load(path + "/" + folder + "/" + f)
                # mfcc = librosa.feature.mfcc(y=y, sr=sr, n_mfcc=13, hop_length=int(sr * 0.01), n_fft=int(sr * 0.02)).T

                # wav 대신 npy 불러와서 훈련시킴

                mfcc = np.load(os.path.join(os.path.join(DATA_PATH, folder), 'data.npy'))

                X_data.extend(mfcc)

                label = [0 for i in range(len(folders))]
                label[total_class] = 1

                for i in range(len(mfcc)):
                    Y_label.append(label)

        total_class += 1

    print('total class ', total_class)

    # train , test data 나눔
    X_train, X_test, Y_train, Y_test = train_test_split(np.array(X_data), np.array(Y_label))

    # xy_data = (X_train, X_test, Y_train, Y_test)
    # np.save("./xy_data.npy", xy_data)

    # test, val data 나눔
    X_val, X_test, Y_val, Y_test = train_test_split(X_test, Y_test, test_size=0.8, random_state=1)

    print("X_train :", np.shape(X_train))
    print("Y_train :", np.shape(Y_train))
    print("X_test :", np.shape(X_test))
    print("Y_test :", np.shape(Y_test))
    print("X_val :", np.shape(X_val))
    print("Y_val :", np.shape(Y_val))

    # nn network
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
                              epochs=40,
                              validation_data=(X_val, Y_val),
                              shuffle=True)

    # 모델 저장하기 - pip install h5py 해야함
    model.save(model_path)

    res = model.evaluate(X_test, Y_test)


if __name__ == "__main__":
    main(sys.argv)
