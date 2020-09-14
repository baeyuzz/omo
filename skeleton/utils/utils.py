from datetime import datetime
import os
import matplotlib.pyplot as plt
import numpy as np
import tensorflow as tf
import logging


# Req. 2-2	세팅 값 저장
def save_config(config):
	logging.basicConfig(filename='history.log', level=logging.INFO, format='%(asctime)s %(message)s')
	logging.info(msg = config)


# Req. 4-1	이미지와 캡션 시각화
def visualize_img_caption(image_path, comment):
	img = plt.imread(image_path)
	plt.title(comment)
	plt.imshow(img)
	plt.show()