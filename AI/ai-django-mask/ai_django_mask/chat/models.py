from django.db import models

# Create your models here.
class Capture(models.Model):
  image = models.ImageField(upload_to='uploads/')

class Chat(models.Model):
  check = models.BooleanField(default=False)
  room = models.CharField(max_length=100)
  message = models.TextField()