<template>
  <div class="login">
    <transition name="slide-fade" mode="out-in">
      <div id="loginForm" v-if="!isShow">
        <input class="loginInput" placeholder="email" v-model="email" />
        <br />
        <input class="loginInput" type="password" placeholder="password" v-model="password" />
        <br />
        <button class="loginBtn" @click="signIn">Login</button>

        <h5>아직 회원이 아니세요?</h5>
        <button @click="openSignUp">회원가입</button>
      </div>
      <SignUp v-else v-on:reTurn="openSignUp" />
    </transition>
  </div>
</template>

<script>
import SignUp from "@/components/SignUp.vue";
import constants from "@/lib/constants.js";

export default {
  name: "Login.vue",
  components: {
    SignUp,
  },
  methods: {
    openSignUp() {
      this.isShow = !this.isShow;
    },
    signUp() {
      let data = {
        email: this.email,
        password: this.password,
      };
      alert("현재 준비중입니다.");
      this.$store.dispatch(constants.METHODS.LOGIN_USER, data);
    },
  },
  data() {
    return {
      email: "",
      password: "",
      isShow: false,
    };
  },
};
</script>

<style scoped>
.login {
  text-align: center;
}
.loginInput {
  background-color: #ffffffd8;
  font-size: 15px;
  padding: 15px 20px 15px 20px;
  margin: 5px;
  border-radius: 25px;
  border: none;
  outline: none;
  box-shadow: 1.5px 2px #292929;
  width: 200px;
}
.loginBtn {
  padding: 5px 15px 5px 15px;
  margin: 15px;
  border-radius: 25px;
  border: none;
  outline: none;
  background-color: #128db37e;
  color: white;
  box-shadow: 0.5px 1px #292929;
}

#loginForm {
  position: relative;
  width: 40vw;
  /* height: 40vh; */
  margin: auto;
}

button {
  background: none;
  outline: none;
  border: none;
  color: white;
}
button:hover {
  color: #ffffffbb;
}

.slide-fade-enter-active {
  transition: all 0.5s;
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
