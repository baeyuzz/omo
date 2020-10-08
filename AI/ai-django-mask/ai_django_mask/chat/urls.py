# chat/urls.py
from django.conf.urls import url
from django.urls import path, include
from . import views

urlpatterns = [
    path('<str:room_name>/', views.room, name='room'),
    path('mask/<str:room_name>/', views.mask, name='mask'),
    path('', views.connect, name='connect')
]