import librosa
import numpy as np
import os
from keras.models import load_model
import sys

def main(argv) :
    code = argv[1]  # 기관명

    default_path = os.path.join(os.path.join("C:\ssafy\project2\pjt3\s03p23a509\AI\Voice", code))
    model_path = os.path.join(os.path.join("C:\ssafy\project2\pjt3\s03p23a509\AI\Voice", code), 'model.h5')
    wav_path = os.path.join(os.path.join("C:\ssafy\project2\pjt3\s03p23a509\AI\Voice", code), 'record.wav')
    wav_path = os.path.join("C:\ssafy\project2\pjt3\s03p23a509\AI\Voice\ssafy\data\유진",'record.wav')

    model = load_model(model_path)
    y, sr = librosa.load(wav_path)

    X_test = librosa.feature.mfcc(y=y, sr=sr, n_mfcc=13, hop_length=int(sr * 0.01), n_fft=int(sr * 0.02)).T

    # predict
    output = model.predict(X_test, steps=1)
    res = np.sum(output, axis=0) / np.sum(output) * 100


    # 결과 출력
    DATA_PATH = os.path.join(default_path,'data')
    folders = os.listdir(DATA_PATH)
    order = 0
    for folder in folders:
        if not os.path.isdir(DATA_PATH):
            continue
        elif np.argmax(res) == order:
            result = folder
            break;
        else:
            order += 1

    if np.max(res) > 50:
        print("result : ", result)
    else:
        print("uncertain result :", result)



if __name__ == "__main__":
    main(sys.argv)

