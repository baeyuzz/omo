<template>
  <div class="main">
    <Nav/>

    <div class="menu">
      <div>
        <div class="icons">
        <img src="@/assets/frame.png" width="120px" height="120px;"/>
        </div>
        <p>
        명부 작성
        </p>
      </div>

      <div>
        <div class="icons">
          <img src="@/assets/paper.png" width="110px" height="120px;"/>
        </div>
        <p>
        명부 관리
        </p>
      </div>

      <div>
        <router-link to="/voiceReg" style="text-decoration:none">
        <div class="icons">
          <img src="@/assets/mic.png" width="90px" height="120px;"/>
        </div>
        <p style="color : white">
          음성 등록
        </p>
        </router-link>
      </div>

      <div>
        <router-link to="/account" style="text-decoration:none">
          <div class="icons">
            <img src="@/assets/key.png" width="110px" height="120px;"/>
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
  import Nav from '@/components/Nav.vue'

  export default {
    name: 'Main',
    components: {
      Nav,
    }
  }
</script>

<style scoped>
  .menu {
    position : fixed;
    top : 42%;
    width: 60%;
    text-align: center;
    padding: 0 20% 0 20%;
    display: flex;
    z-index: 4;
    justify-items: center;
    justify-content: space-between;
  }
  .icons {
    height : 130px;
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
