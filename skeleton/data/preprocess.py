import os
import csv
import numpy as np
import pandas as pd
from config import config
from sklearn.model_selection import train_test_split


# Req. 3-1	이미지 경로 및 캡션 불러오기
def get_path_caption():
    df = pd.read_csv(config.caption_file_path, encoding='cp949', sep='|')
    captions = df.iloc[:,2].values.tolist()
    image_names = df['image_name'].values.tolist()
    return image_names, captions


# Req. 3-2	전체 데이터셋을 분리해 저장하기
def dataset_split_save():
    df = pd.read_csv(config.caption_file_path, encoding='cp949', sep='|')
    train_set, test_set = train_test_split(df, test_size=0.2)
    train_set.to_csv(config.train_file_path, encoding='cp949', sep='|', index=False)
    test_set.to_csv(config.test_file_path, encoding='cp949', sep='|', index=False)
    return config.train_file_path, config.test_file_path


# Req. 3-3	저장된 데이터셋 불러오기
def get_data_file(dataset='train'):
    data_file_path = ""
    if dataset == 'train':
        data_file_path = config.train_file_path
    else:
        data_file_path = config.test_file_path

    df = pd.read_csv(data_file_path, encoding='cp949', sep='|')
    df['image_path'] = config.image_file_path + '\\' + df['image_name']
    image_paths = df['image_path'].values.tolist()
    captions = df.iloc[:,2].values.tolist()
    return image_paths, captions


# Req. 3-4	데이터 샘플링
def sampling_data(image_paths, captions, frac=0.8):
    data = {'image_path': image_paths,
            'comment': captions}
    df = pd.DataFrame(data)
    df = df.sample(frac=frac)
    print(df.head())
    return df['image_path'].values.tolist(), df['comment'].values.tolist()
