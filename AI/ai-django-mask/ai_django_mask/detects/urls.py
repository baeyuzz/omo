from django.urls import path, include
from . import views


urlpatterns = [
  path('image/', views.detect_image),
  path('video/', views.detect_video),
]