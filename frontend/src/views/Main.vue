<template>
  <div class="main">
    <Nav />

    <div class="menu">
      <div @click="goVideo" style="padding-right : 2%; cursor:pointer;">
          <div class="icons">
            <img src="@/assets/frame.png" width="170px" height="170px;" />
          </div>
          <p>명부 작성</p>
      </div>
      <div style="padding-left : 2%;">
        <div @click="goMask" style="padding-right : 2%; cursor:pointer;">
          <div class="icons">
            <img src="@/assets/facemask.png" width="170px" height="170px;"/>
          </div>
          <p>
          마스크 인식
          </p>
        </div>
      </div>
      <div style="padding-left : 2%;">
        <router-link to="/account" style="text-decoration:none">
          <div class="icons">
            <img src="@/assets/key.png" width="170px" height="170px;"/>
          </div>
          <p>
          계정 설정
          </p>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import Nav from "@/components/Nav.vue";
import axios from 'axios'
export default {
  name: "Main",
  components: {
    Nav,
  },
  data(){
    return {
      'imageBytes': null,
      'videoUrl': null,
    }
  },
  mounted() {
    this.videoUrl = "/video/" + this.$cookies.get('code')
  },
  methods: {
    test() {

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
    },
    goVideo() {
      this.$router.push('/video/' + this.$cookies.get('code'))
    },
    goMask() {
      this.$router.push('/video/mask/' + this.$cookies.get('code'))
    }
  }          
}
</script>

<style scoped>
  .menu {
    position : fixed;
    top : 40%;
    width: 30%;
    text-align: center;
    padding: 0 33% 0 33%;
    display: flex;
    z-index: 2;
    justify-items: center;
    justify-content: space-between;
  }
  .icons {
    height : 180px;
  }
  img {
    transform: scale(1);
    -webkit-transform:scale(1);
  }
  img:hover {
    transform: scale(1.2);
    -webkit-transform:scale(1.2);
  }
  p {
    color : white;
  }
</style>
