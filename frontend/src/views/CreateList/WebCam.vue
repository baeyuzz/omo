<template>
    <div id="webcam">
        <div><video ref="video" id="video" width="640" height="480" autoplay></video></div>
        <div><button id="snap" v-on:click="capture()">사진 촬영</button></div>
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
    import axios from 'axios'
    export default {
        name: 'WebCam',
        data() {
            return {
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
        },
        methods: {
            capture() {
                this.canvas = this.$refs.canvas;
                var context = this.canvas.getContext("2d").drawImage(this.video, 0, 0, 640, 480);
                this.captures.push(this.canvas.toDataURL("image/png"));

                console.log(context);
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

<style>
    #webcam {
        text-align: center;
        color: #2c3e50;
        margin-top: 60px;
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
</style>