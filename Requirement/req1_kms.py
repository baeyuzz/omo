import os

os.chdir('..')

import numpy as np
import matplotlib.pyplot as plt
from PIL import Image, ImageOps

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

plt.imshow(get_image('datasets/images/8237901726.jpg'))
plt.show()

#Req 1-2
color_mean = {'r':113.29718549490755,
            'g' : 107.4292929175899,
            'b' : 98.1446513821604}

color_std = {'r':72.70319221304833,
            'g':70.71531997497007,
            'b':72.88658387246883}
#전체 이미지 normalize
def normalize_image(image: np.array ,mean,stddev) :
    return (image - mean)/stddev
