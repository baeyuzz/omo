<template>
    <div id="webcam">
        <div><video ref="video" id="video" width="640" height="480" autoplay></video></div>
        <div><button id="snap" v-on:click="capture()">사진 촬영</button></div>
        <router-link to="/main" style="text-decoration:none; color : white;">취소 </router-link>
        <canvas ref="canvas" id="canvas" width="640" height="480"></canvas>
        <ul>
            <li v-for="c in captures" :key="c">
                <img v-bind:src="c" height="50" />
            </li>
        </ul>
    </div>
</template>
<script>
    import http from '@/http-common.js'

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

                http.post('/어쩌구,,.').then(
                    res =>{
                        console.log(res);
                        // routing to voice recognition page
                        this.$router.push({name: "VoiceRecognition"});
                    }
                )
                .catch(err => {
                    this.$router.push({name: "VoiceRecognition"});
                    alert('Error ', err)
                });

            }
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