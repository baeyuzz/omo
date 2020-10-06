<template>
  <div class="main">
    <Nav />

    <div class="menu">
      <div>
        <router-link to="/createList" style="text-decoration:none">
          <div class="icons">
            <img src="@/assets/frame.png" width="170px" height="170px;" />
          </div>
          <p>명부 작성</p>
        </router-link>
      </div>

      <!-- <div>
        <router-link to="/chat/123" style="text-decoration:none">
          <div class="icons">
            <img src="@/assets/frame.png" width="170px" height="170px;" />
          </div>
          <p>명부 작성 -yw</p>
        </router-link>
      </div>
       -->
      <div>
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
    }
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
  }          
}
</script>

<style scoped>
  .menu {
    position : fixed;
    top : 40%;
    width: 30%;
    text-align: center;
    padding: 0 35% 0 35%;
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
