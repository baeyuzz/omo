import os

os.chdir('..')

import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import matplotlib.image as mpimg
import tensorflow as tf
from tqdm.notebook import tqdm
from PIL import Image, ImageOps


#데이터셋 인덱스 불러오기
results = pd.read_csv('datasets/images/results.csv', sep="|")
result_index = results.image_name.unique()

# #전체 이미지 x,y 크기 분포 확인
# x_list = []
# y_list = []

# #평균 250it/sec 
# for index in tqdm(result_index) :
#     path = 'datasets/images/'+index
#     x_list.append(mpimg.imread(path).shape[0])
#     y_list.append(mpimg.imread(path).shape[1])

# # 500,500이 최대이므로 사이즈는 500,500으로 통일
# max_size = max(max(x_list), max(y_list))

#Req 1-1
def get_image(path) :
    desired_size = 500
    im = Image.open(path)
    old_size = im.size

    ratio = float(desired_size)/max(old_size)
    new_size = tuple([int(x*ratio) for x in old_size])
    im = im.resize(new_size, Image.ANTIALIAS)
    
    new_im = Image.new("RGB", (desired_size, desired_size))
    new_im.paste(im, ((desired_size-new_size[0])//2,
                    (desired_size-new_size[1])//2))
    
    #PIL to Array
    new_im_array = np.array(new_im)
    return new_im_array

plt.imshow(get_image('datasets/images/10287332.jpg'))
plt.show()

# Req 1-2
#전체 이미지 RGB dict 생성
r_dict = {}
g_dict = {}
b_dict = {}

#dict = 50it/sec
for index in result_index[:100] :
    path = 'datasets/images/'+index
    image = mpimg.imread(path)
    r_dict[index] = image[:,:,0]
    g_dict[index] = image[:,:,1]
    b_dict[index] = image[:,:,2]
print(image.shape)
print(r_dict['10287332.jpg'])
print(r_dict['10287332.jpg'].shape)

#색상별 평균 작성
def calc_color_mean(color) :
    total_sum = 0
    total_num = 0
    
    if color == "r" :
        for key in r_dict :
            total_sum += r_dict[key].sum()
            total_num += r_dict[key].shape[0]*r_dict[key].shape[1]
    elif color == "g" :
        for key in g_dict :
            total_sum += g_dict[key].sum()
            total_num += g_dict[key].shape[0]*g_dict[key].shape[1]
    elif color == "b" : 
        for key in b_dict :
            total_sum += b_dict[key].sum()
            total_num += b_dict[key].shape[0]*b_dict[key].shape[1]
    else : 
        raise("색상을 입력해주세요")
    
    total_mean = total_sum/total_num
    return total_mean, total_num

color_mean = {'r':113.29718549490755,
            'g' : 107.4292929175899,
            'b' : 98.1446513821604}

color_std = {'r':72.70319221304833,
            'g':70.71531997497007,
            'b':72.88658387246883}
#전체 이미지 normalize
def normalize_image(image: np.array ,mean,stddev) :
    return (image - mean)/stddev

#R,G,B 평균
print(calc_color_mean("r"),calc_color_mean("g"),calc_color_mean("b"))



# Req 3-1

#이미지 Array를 Dataset으로 변환
data = tf.data.Dataset.from_tensor_slices(get_image('datasets/images/8237901726.jpg'))

#텍스트 string을 Dataset으로 변환
#RNN쪽에서 Tokenize 해줘야 함

def create_dataset(path) :
    image = tf.data.Dataset.from_tensor_slices(get_image(path))
    
    return image

data = create_dataset(x_train)