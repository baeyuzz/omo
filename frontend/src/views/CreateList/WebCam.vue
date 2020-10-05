<template>
    <div id="webcam"
    ref="webcam"
    tabindex="0"
    @keyup="capture()"
    >
    <transition name="slide-fade" mode="out-in">
        <div id="covering" v-if="send">
            전송중입니다.
        </div>
    </transition>

        <div id="box">
            <video ref="video" id="video" width="640" height="480" autoplay></video>
                <p><strong>마스크를 벗지말고</strong> 얼굴을 찍어주세요!</p>
                <p>아래의 카메라 버튼, 혹은 키보드 버튼을 눌러주세요!</p>
            <div id="toolbox">
                <button id="snap" v-on:click="capture()"><img src="@/assets/camera.png" width="50px" height="50px;" /></button>
                <router-link to="/main" style="text-decoration:none; color : white;"><img src="@/assets/close.png" width="50px" height="50px;" /></router-link>
            </div>
        </div>
        <canvas ref="canvas" id="canvas" width="640" height="480"></canvas>
        <ul>
            <li v-for="c in captures" :key="c">
                <img v-bind:src="c" height="50" />
            </li>
        </ul>
        <div><button id="submit" v-on:click="captureSubmit()">사진 전송</button></div>
    </div>
</template>

<script>
    import http from '@/http-common.js'

    import axios from 'axios'
    export default {
        name: 'WebCam',
        data() {
            return {
                send: false,
                video: {},
                canvas: {},
                captures: []
            }
        },
        mounted() {
            this.video = this.$refs.video;
            if(navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
                navigator.mediaDevices.getUserMedia({ video: true })
                .then(stream => {
                    this.video.srcObject = stream;
                    this.video.play();
                });
            }
            this.$refs.webcam.focus();
        },
        methods: {
            capture() {
                this.canvas = this.$refs.canvas;
                // var context = this.canvas.getContext("2d").drawImage(this.video, 0, 0, 640, 480);
                this.captures.push(this.canvas.toDataURL("image/png"));
                this.video.pause();

                // console.log(context);
                // console.log(this.captures);
                this.send = true;


                http.post('/어쩌구,,.').then(
                    res =>{
                        console.log(res);
                        // routing to voice recognition page
                        this.$router.push({name: "VoiceRecognition"});
                    }
                )
                .catch(err => {
                    alert('문제가 발생했습니다. 잠시후 다시 시도해주세요. ', err)
                    this.$router.push({name: "Main"});
                });

                // console.log(context);
                console.log(this.captures);
            },
            captureSubmit() {
              var capture = this.captures[(this.captures.length-1)]
              console.log("전송@@") 
              var data = new FormData()
              data.append('capture', capture)
              const config = {
                headers: {
                  Authorization: `Token ${this.$cookies.get('auth-token')}`
                }
              }
              axios.post('http://127.0.0.1:8000/cam/image/', data, config)
              .then((res) => {
                console.log("전송성공")
                this.result = res.data;
                console.log(this.result)
                alert('응답왔어용')
              })
              .catch((err) => {
                console.log('사진 업로드 실패', err)
              })
            },
        }
    }
</script>

<style scoped>
    #webcam {
        text-align: center;
        color: #2c3e50;
        padding: 10vh;
        height: 80vh;
        background: #00000055
    }
    #box{
        /* margin: 5%; */
        z-index: 1;
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
    #video {
        background-color: #000000;
    }
    #canvas {
        display: none;
    }
    li {
        display: inline;
        padding: 5px;
    }
    button{
        background: none;
        border: none;
        margin-right: 100px;
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