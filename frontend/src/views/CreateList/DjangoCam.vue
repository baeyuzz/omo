<template>
    <div>
        <div class="imageArea">
            <img :src="imageBytes" alt="capture">
        </div>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: "DjangoCam.vue",
        data(){
            return {
                'imageBytes': null,
            }
        },
        mounted() {
            console.log('test video')
            axios.get('http://127.0.0.1:8000/cam/video/')
                .then((res) => {
                    console.log("전송성공")
                    this.result = res.data
                    console.log(this.result)
                    alert('응답왔어용')
                    this.imageBytes = 'data:image/png;base64,' + res.data['capture']
                })
                .catch((err) => {
                    console.log('사진 업로드 실패', err)
                })
        }
    }

</script>

<style scoped>
    .imageArea {
        position : fixed;
        top : 42%;
        width: 30%;
        text-align: center;
        padding: 0 35% 0 35%;
        display: flex;
        z-index: 2;
        justify-items: center;
        justify-content: space-between;
    }
</style>