<template>
  <div id="webcam" ref="webcam" tabindex="0">
    <Nav/>
    <transition name="slide-fade" mode="out-in">
      <div id="covering" v-if="success">
          확인되었습니다
      </div>
    </transition>

    <div class="count">
      <p>현재 방문자 수: {{ count }} 명</p>
    </div>
    <div id="box">
        <div id="load" v-if="isLoading">
          <socket></socket><br>
          <p>모델을 불러오는 중입니다...</p>
          <p>잠시만 기다려주세요!</p>
        </div>
        
        <img id="stream" height="480" alt="stream" v-if="!isLoading"/>
        <div>
          <p class="caution"><strong>마스크를 벗지말고</strong> 얼굴을 찍어주세요!</p>
          <p class="caution">openCV 프로그램에서 'q' 를 누르면, 비디오 스트림이 종료됩니다</p>
        </div>

    </div>
  </div>
</template>

<script>    
import Nav from '@/components/Nav.vue'
import axios from "axios";
import {Socket} from 'vue-loading-spinner'

export default {
  data() {
    return {
      roomName: null,
      chatMessage: "",
      chatSocket: null,
      success: false,
      isLoading: true, 
      count: 0,
    };
  },
  components: {
    Nav,
    Socket
  },
  created() {
    this.roomName = this.$route.params.roomName;
  },
  mounted() {
    console.log("ws://" + "localhost:8082" + "/ws/chat/mask_" + this.roomName + "/");
    var chatSocket = new WebSocket(
      "ws://" + "localhost:8082" + "/ws/chat/mask_" + this.roomName + "/"
    );
    this.chatSocket = chatSocket;
    this.chatSocket.onmessage = function (e) {
      var data = JSON.parse(e.data);
      var message = data["message"];
      if (data["check"]) {
        this.count += 1;
        this.success = true;
        var el = document.getElementById('stream');
        el.style.borderColor = '#00FF00';
        setTimeout(function() {
          this.success = false;
          el.style.borderColor = '#FF0000';
        }.bind(this), 1500)
      }
      if (this.isLoading) this.isLoading=false;
      document.querySelector("#stream").src = 'data:image/png;base64,' + message
      // console.log(message)
    }.bind(this);

    this.chatSocket.onclose = function (e) {
      console.error("Chat socket closed unexpectedly" + e);
    };
    console.log("http://127.0.0.1:8082/chat/mask/"+this.roomName+'/')
    axios
      .get("http://127.0.0.1:8082/chat/mask/"+this.roomName+'/')
      .then((res) => {
        console.log("전송성공" + res.data);
      })
      .catch((err) => {
        // alert('서버 연결에 실패했습니다..')
        // this.$router.push("Main");
        console.log(err);
      });
  },
  methods: {

  },
  
};
</script>

<style scoped>
    #webcam {
        text-align: center;
    }
    #stream {
        border: 2px solid red;
        border-radius: 5px;
    }
    #box{
      align-content: center;
      margin: 0;
        text-align: center;
        z-index: 1;
    }
    #load {
        display: flex;
        flex-direction: column;
        height: 480px;
        justify-content: center;
        align-items: center;
        color: #dddddd;
        width: 640px;
        margin: auto;
        background-color: rgba(0,0,0,0.3);
    }
    #covering{
        height: auto;
        background: #FFFFFF30;
        position: absolute;
        vertical-align: middle;
        width: 30%;
        left: 35%;
        top: 40%;
        padding: 1%;
        font-size: xx-large;
    }
    p{
        margin-top: 1%;
    }
    #toolbox{
        margin-top: 30px;
    }
    button{
        background: none;
        border: none;
        margin-right: 100px;
    }
    .count {
      padding-left: 500px;
    }
    .success {
      display: inline-block;
      font-size: 1.2rem;
      line-height: 20px;
      padding: 10px 16px 0px 16px;
      text-align: center;
      border-radius: 5px;
      background-color: rgba(255,255,255,0.4);
    }
    .caution {
      color: #dddddd;
    }
    .caution > strong {
      color: white
    }
    .slide-fade-enter-active {
    transition: all 0.3s;
    }
    .slide-fade-leave-active {
    transition: all 0.3s;
    }
    .slide-fade-enter
    /* .slide-fade-leave-active below version 2.1.8 */ {
    transform: translateX(10px);
    opacity: 0;
    }
    .slide-fade-leave-to {
    transform: translateX(-10px);
    opacity: 0;
    }
</style>