# chat/urls.py
from django.conf.urls import url
from django.urls import path, include
from . import views

urlpatterns = [
    path('/', views.index, name='index'),
    path('<int:room_name>/', views.room, name='room'),
]