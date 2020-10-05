<template>
  <div class="default">
    roomName is {{ this.roomName }}

    <div class="imageArea">
      <img id="stream" height="300" alt="stream" />
      <img id="capture" height="200" alt="capture" />
    </div>
  </div>
</template>

<script>    
import axios from "axios";

export default {
  data() {
    return {
      roomName: null,
      chatMessage: "",
      chatSocket: null,
    };
  },
  created() {
    this.roomName = this.$route.params.roomName;
  },
  mounted() {
    var chatSocket = new WebSocket(
      "ws://" + "localhost:8000" + "/ws/chat/" + this.roomName + "/"
    );
    this.chatSocket = chatSocket;
    this.chatSocket.onmessage = function (e) {
      var data = JSON.parse(e.data);
      var message = data["message"];
      console.log(data["check"])
      if (data["check"])
        document.querySelector("#capture").src = 'data:image/png;base64,' + message
      else {
        document.querySelector("#stream").src = 'data:image/png;base64,' + message
      }
      // console.log(message)
    };

    this.chatSocket.onclose = function (e) {
      console.error("Chat socket closed unexpectedly" + e);
    };
    axios
      .get("http://127.0.0.1:8000/chat/"+this.roomName+'/')
      .then((res) => {
        console.log("전송성공" + res);
      })
      .catch((err) => {
        console.log("사진 업로드 실패", err);
      });
  },
  methods: {
    sendMessage() {
      this.chatSocket.send(
        JSON.stringify({
          message: this.chatMessage,
        })
      );
      this.chatMessage = "";
    },
  },
};
</script>

<style>
.default {
  position: fixed;
  top: 42%;
  width: 30%;
  text-align: center;
  padding: 0 35% 0 35%;
  display: flex;
  z-index: 2;
  justify-items: center;
  justify-content: space-between;
}

.imageArea {
  position: fixed;
  top: 42%;
  width: 30%;
  text-align: center;
  padding: 0 0 35%;
  display: flex;
  z-index: 2;
  justify-items: center;
  justify-content: space-between;
}
</style>