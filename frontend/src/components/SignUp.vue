<template>
  <div id="signUp">
    <input type="text" placeholder="기관명" v-model="name" />
    <br />
    <input type="text" placeholder="Email" v-model="email" />
    <br />
    <input type="password" placeholder="Password" v-model="password" />
    <br />
    <input type="password" placeholder="Password Confirm" v-model="passwordConfirm" />
    <br />
    <!-- <h5>Sign Up here!</h5> -->
    <button class="loginBtn" @click="signUp">SignUp</button>
  </div>
</template>

<script>
// import constants from "@/lib/constants.js";
// import http from "@/http-common.js";
export default {
  methods: {
    signUp() {
      if (this.password != this.passwordConfirm) {
        alert("패스워드가 다릅니다.");
        return;
      }
      alert("현재 준비중입니다.");
      this.$emit("reTurn");
      // let url = "";
      // http
      //   .post(url, {
      //     name: this.name,
      //     email: this.email,
      //     password: this.password,
      //   })
      //   .then(() => {
      //     console.log("Success");
      //     this.$emit("reTurn", "aasdf");
      //   })
      //   .catch((err) => {
      //     console.log(err);

      //     this.$emit("reTurn");
      //   });
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
  /* position: relative; */
  margin: 0;
  /* width: 40vw; */
  /* height: 40vh; */
  /* border: solid red 1px; */
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
