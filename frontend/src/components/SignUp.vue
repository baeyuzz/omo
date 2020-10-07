<template>
  <div id="signUp">
    <input type="text" placeholder="기관명 (3자~15자)" v-model="name" />
    <br />
    <input type="text" placeholder="Email" v-model="email" />
    <br />
    <input type="password" placeholder="Password (6자~20자)" v-model="password" />
    <br />
    <input type="password" placeholder="Password Confirm" v-model="passwordConfirm" />
    <br />
    <!-- <h5>Sign Up here!</h5> -->
    <button class="loginBtn" @click="signUp">SignUp</button>
   
  </div>
</template>

<script>
// import constants from "@/lib/constants.js";
// import axios from "axios";
import { createUser } from "@/lib/userApi.js";

export default {
  methods: {
    openSignUp() {
      this.isShow = !this.isShow;
    },
    signUp() {
      if (this.name.length<3) {
        alert("기관명은 3자 이상으로 해주세요.");
        return;
      }
      if (this.password != this.passwordConfirm) {
        alert("패스워드가 다릅니다.");
        return;
      }
      if (this.email.indexOf('@') == -1 || this.email.indexOf('.') == -1) {
        alert("유효하지 않은 이메일 입니다.");
        return;
      }      
      if (this.password.length<6) {
        alert("패스워드는 6자 이상으로 해주세요.");
        return;
      }
      
      const data = {
        username: this.name,
        email: this.email,
        password: this.password,
      }
      createUser(data)
        .then(() => {
          // console.log("Success");
          this.$store.commit("setCode", this.name);
          alert("회원가입 성공 ! 로그인 해주세요")
          this.name = '';
          this.phone = '';
          this.email = '';
          this.password = '';
          this.passwordConfirm = '';

        })
        .catch(() => {
          // console.log(err);
          alert("회원가입 실패")
        });
    },
  },
  data: () => {
    return {
      name: "",
      email: "",
      password: "",
      passwordConfirm: "",
    };
  },
};
</script>

<style scoped>
#signUp {
  position: absolute;
  width: 90%;
  margin: 0% 5%;
  top: 65%;
  text-align: center;

}

input {
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

.loginBtn:hover {
  color: #ffffffbb;
}
</style>
