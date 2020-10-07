<template>
  <div class="home">
    <Nav/>

    <div class="intro">
      <div class="ment">
        <div class="name">
          <h2>Only Mask On your face </h2>
          <h1> OMO </h1>
        </div>
        <div class="desc">
          위험한 코로나 시대,
          마스크를 벗지 않아도
          <br>
          당신을 알아봐주는
          자동 출입 명부 작성 서비스
        </div>
      </div>
    </div>
    <div class="loginORjoin">
      <a class="toggle" @click="openLogin">
        <span v-if="!isShow" style="color : white;"> 로그인 </span>
        <span v-else> 로그인 </span>
      </a>
      
      <a class="toggle" @click="openSignUp">
        <span v-if="isShow" style="color : white;"> 회원가입 </span>
        <span v-else> 회원가입 </span>

      </a>
    </div>
    <transition name="slide-fade" mode="out-in">
      <Login v-if="!isShow" />
      <SignUp v-else />
    </transition>
 
  </div>
</template>

<script>
import SignUp from '@/components/SignUp.vue'
import Login from '@/components/Login.vue'
import Nav from '@/components/Nav.vue'

export default {
  name: 'Home',
  components: {
    Login,
    Nav,
    SignUp
  },
  data() {
    return {
      isShow: false,
    };
  },

  methods: {
    openLogin() {
     
      this.isShow = false;
    },
    openSignUp() {
      
      this.isShow = true;
    },
  }
}
</script>
<style scoped>
.intro {
  padding-left : 7%;
}
.ment {
  margin  : 5% 0 5% 0;

}
h2 {
  margin : 0;
  font-size: 38px;
}
h1 {
  margin : 10px 0 40px 0;
  font-size : 54px;
}
.desc {
  font-size: 24px;
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
.loginORjoin {
  position: absolute;
  width: 90%;
  margin: 0% 5%;
  top: 55%;
  text-align: center;
}
a {
  padding : 15px;
  background: none;
  outline: none;
  border: none;
  color : rgb(209, 209, 209);
}


</style>