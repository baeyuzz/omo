import tensorflow as tf
import matplotlib.pyplot as plt
import numpy as np
from sklearn.model_selection import train_test_split

# load fashion mnist dataset
(x_train, y_train), (x_test, y_test) = tf.keras.datasets.fashion_mnist.load_data()

print(x_train.shape[0]) # train data 개수
print(x_test.shape[0])

# data size 정규화
x_train = x_train.astype('float32') / 255
x_test = x_test.astype('float32') / 255

model = tf.keras.Sequential([tf.keras.layers.Flatten(input_shape=(28, 28))])

model.add(tf.keras.layers.Flatten()) 
model.add(tf.keras.layers.Dense(256, activation='relu')) # relu if( x > 0 ) y = x / dense -> fully connected
model.add(tf.keras.layers.Dropout(0.3)) # overfitting 방지
# 10 개 분류
model.add(tf.keras.layers.Dense(10, activation='softmax')) # softmax -> 0 기준으로 -1 / 1 로 확 꺾이는 함수

model.summary()

model.compile(loss='sparse_categorical_crossentropy', # one hot encoding / 다중 분류 손실함수 / integer type class
              optimizer='adam',
              metrics=['accuracy'])

x_train, x_val, y_train, y_val = train_test_split(x_train, y_train, test_size=0.2, random_state=1)

model.fit(x_train,
         y_train,
         batch_size=64,
         epochs=10,
         validation_data=(x_val, y_val))

test_loss, test_acc = model.evaluate(x_test,  y_test, verbose=2)

print('\n테스트 정확도:', test_acc)
predictions = model.predict(x_test) # test data 로 예측하기

def plot_image(i, predictions_array, true_label, img): # img, predicted_label 그래프에 그려주는 함수
  predictions_array, true_label, img = predictions_array[i], true_label[i], img[i]
  plt.grid(False)
  plt.xticks([])
  plt.yticks([])

  plt.imshow(img, cmap=plt.cm.binary) # img 흑백으로 표시

  predicted_label = np.argmax(predictions_array)
  if predicted_label == true_label: # 예측한 label과 실제 label 일치 시
    color = 'blue'
  else:
    color = 'red'

  # img 밑에 설명 ~~
  plt.xlabel("Prediction : {} {:2.0f}% \n Label : {} \n "
  .format(class_names[predicted_label],
          100*np.max(predictions_array),
          class_names[true_label]
          ), color=color)

# fashin mnist 분류 label
class_names = ['T-shirt/top', 'Trouser', 'Pullover', 'Dress', 'Coat',
               'Sandal', 'Shirt', 'Sneaker', 'Bag', 'Ankle boot']

num_rows = 5
num_cols = 3
plt.figure(figsize=(3*num_cols, 2*num_rows)) # subplot 사이즈
for i in range(num_rows*num_cols):
  plt.subplot(num_rows, 2*num_cols, 2*i+1)
  plot_image(i+1538, predictions, y_test, x_test) # i + n 으로 이미지 변경..

plt.show()