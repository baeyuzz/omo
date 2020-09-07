import argparse
import json

# Req. 2-1	Config.py 파일 생성
parser = argparse.ArgumentParser()

# with open("config.json") as f:
#     data = json.load(f)
#
# caption_file_path = data['caption_file_path']
# train_file_path = data['train_file_path']
# test_file_path = data['test_file_path']
# image_file_path = data['image_file_path']
# do_sampling = data['do_sampling']

# 캡션 데이터가 있는 파일 경로 (예시)
parser.add_argument('--caption_file_path', type=str, default='.\\datasets\\captions.csv')
parser.add_argument('--train_file_path', type=str, default='.\\datasets\\train_list.csv')
parser.add_argument('--test_file_path', type=str, default='.\\datasets\\test_list.csv')
parser.add_argument('--image_file_path', type=str, default='.\\datasets\\images')
parser.add_argument('--do_sampling', type=int, default=1)
config = parser.parse_args()
