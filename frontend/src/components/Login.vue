<template>
  <div class="login">
    <!-- <transition name="slide-fade" mode="out-in"> -->
      <div id="loginForm">
        <input class="loginInput" placeholder="email" v-model="email" />
        <br />
        <input class="loginInput" type="password" placeholder="password" v-model="password" />
        <br />
        <button class="loginBtn" @click="signIn">Login</button>

        
      </div>
    <!-- </transition> -->
  </div>
</template>

<script>
// import SignUp from "@/components/SignUp.vue";
// import constants from "@/lib/constants.js";
import { loginUser } from "@/lib/userApi.js";

export default {
  name: "Login",
  components: {
    // SignUp,
  },
  methods: {
    openSignUp() {
      this.isShow = !this.isShow;
    },
    signIn() {
      let data = {
        usernameOrEmail: this.email,
        password: this.password,
      };
      loginUser(data)
      .then((res)=>{
        console.log(res)
        this.$store.commit('setToken', res.data.accessToken);
        this.$store.commit('setCode', res.data.companyName);
        this.$store.commit('setEmail', this.email);
        this.$router.push({ name: "Main" });
      })
      .catch((err)=>{
        alert(err)
      });
      // alert("현재 준비중입니다.");
      // this.$store.dispatch(constants.METHODS.LOGIN_USER, data);
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
  position: absolute;
  width: 90%;
  margin: 0% 5%;
  top: 65%;
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

</style>
