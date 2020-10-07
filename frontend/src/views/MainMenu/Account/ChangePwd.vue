<template>
  <div>
    <div>
      <div>
        <div class="regForm">
          <h1>비밀번호 변경</h1>
        </div>
        <div class="regForm">
          <table>
            <tbody>
              <tr>
                <td><label>기존 비밀번호</label></td>
                <td>
                  <input class="regInput" v-model="prePwd" type="password" />
                </td>
              </tr>
              <tr>
                <td><label>새로운 비밀번호</label></td>
                <td>
                  <input class="regInput" v-model="newPwd" type="password" />
                </td>
              </tr>
              <tr>
                <td><label>비밀번호 확인</label></td>
                <td>
                  <input class="regInput" v-model="newPwd2" type="password" />
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="regForm">
          <button class="regBtn" @click="submitForm()">비밀번호 변경</button>
        </div>
        <div class="deleteDiv">
          <button class="deleteBtn" @click="deleteAcc()">회원탈퇴</button>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
// import Nav from '@/components/Nav.vue'
import http from "@/http-common.js";
import { deleteUser } from "@/lib/userApi.js";

export default {
  name: "ChangPwd",
  //   components: {
  //     Nav
  //   },
  data() {
    return {
      prePwd: "",
      newPwd: "",
      newPwd2: "",
    };
  },
  methods: {
    deleteAcc() {
    //   if (this.prePwd.length == 0) {
    //     alert("기존 비밀번호를 입력하세요");
    //     return;
    //   }
      var confirm_test = confirm("탈퇴하시겠습니까?");

      if (confirm_test == true) {
        // axios.~~.then~~.catch~~ 하기
        deleteUser()
        .then(()=>{
          alert('탈퇴 되었습니다')
          this.$router.push({ name: "Main" })
          }
        )
        .catch((err)=>alert(err))
      }
    },
    async submitForm() {
      if (this.prePwd.length == 0) {
        alert("기존 비밀번호를 입력하세요");
        this.$refs.prePwd.focus();
        return;
      }
      if (this.newPwd.length == 0) {
        alert("새로운 비밀번호를 입력하세요");
        this.$refs.newPwd.focus();
        return;
      }
      if (this.newPwd2.length == 0) {
        alert("비밀번호 확인을 입력하세요");
        this.$refs.newPwd2.focus();
        return;
      }
      if (this.newPwd2 != this.newPwd) {
        alert("비밀번호가 일치하지 않습니다");
        this.$refs.newPwd2.focus();
        return;
      }
      const params = {
        newPwd: this.newPwd,
        newPwd2: this.newPwd2,
        prePwd: this.prePwd,
      };
      http
        .post("/아직안정해짐", params)
        .then((res) => {
          if (res.data.state) {
            // 등록이 되면
            console.log("success");
            this.$router.push({
              name: "Main",
            });
          } else {
            this.$router.push({
              name: "Main",
            });
            console.log("fail");
          }
        })
        .catch((err) => {
          this.$router.push({
            name: "Main",
          });
          alert("Error ", err);
        });
    },
  },
};
</script>
<style scoped>
* {
  color: black;
}
.regForm {
  /* width: 100%; */
  text-align: center;
  display: flex;
  justify-content: center;
}
.regInput {
  background-color: #ffffff00;
  font-size: 15px;
  padding: 15px 20px 15px 8px;
  margin: 5px;
  /* border-radius: 10px; */
  border: none;
  outline: none;
  width: 250px;
  border-bottom: 1px solid;
  color: black;
}
.regInput::placeholder {
  color: #1624429d;
  font-size: 15px;
  font-weight: bold;
}
.regBtn {
  text-decoration: none;
  padding: 5px 15px 5px 15px;
  margin: 30px 5px 8% 5px;
  border-radius: 5px;
  border: none;
  outline: none;
  background-color: #005f7c7e;
  color: black;
  box-shadow: 0.5px 1px #292929;
  text-align: center;
  display: flex;
  justify-content: center;
  font-size: 15px;
}
table {
  text-align: left;
  border-spacing: 0 15px;
}

.inputGroup {
  display: flex;
  justify-content: center;
}

.regMent {
  margin-top: 40px;
  margin-bottom: 15px;
}
label {
  font-size: 18px;
  margin: 5px;
}
.deleteDiv {
  text-align: center;
  margin: auto;
}
.deleteBtn {
  text-decoration: none;
  border-radius: 5px;
  border: none;
  outline: none;
  color: black;
  text-align: center;
  margin-bottom: 15px;
  font-size: 13px;
}
</style>