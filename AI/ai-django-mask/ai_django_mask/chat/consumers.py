from channels.generic.websocket import AsyncWebsocketConsumer
import json
from asgiref.sync import async_to_sync
from django.db.models.signals import post_save
from django.dispatch import receiver
from channels.layers import get_channel_layer
from .models import Chat

@receiver(post_save, sender=Chat)
def announce_chats(sender, instance, created, **kwargs):
    if created:
        print(instance.check)
        channel_layer=get_channel_layer()
        async_to_sync(channel_layer.group_send)(
            "chat_123", {
                "type": "chat_message",
                "message": instance.message,
                "check": instance.check,
            }
        
    )

class ChatConsumer(AsyncWebsocketConsumer):
    async def connect(self):
        self.room_name = self.scope['url_route']['kwargs']['room_name']
        self.room_group_name = 'chat_%s' % self.room_name

        # Join room group
        await self.channel_layer.group_add(
            self.room_group_name,
            self.channel_name
        )
        await self.accept()

    async def disconnect(self, close_code):
        # Leave room group
        await self.channel_layer.group_discard(
            self.room_group_name,
            self.channel_name
        )

    # Receive message from WebSocket
    async def receive(self, text_data):
        text_data_json = json.loads(text_data)
        message = text_data_json['message']
        check = text_data_json['check']

        # Send message to room group
        await self.channel_layer.group_send(
            self.room_group_name,
            {
                'type': 'chat_message',
                'message': message,
                'check': check,
            }
        )

    # Receive message from room group
    async def chat_message(self, event):
        message = event['message']
        check = event['check']

        # Send message to WebSocket
        await self.send(text_data=json.dumps({
            'message': message,
            'check': check
        }))
      