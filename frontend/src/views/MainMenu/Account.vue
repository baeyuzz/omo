<template>
  <div class="account">
    <Nav />

    <div class="intro">
      <router-link to="/main" style="text-decoration:none; color : white;">
        <h1>◀</h1>
      </router-link>
      <h1>{{code}} 계정 설정</h1>
      <h2 />
    </div>
    <!-- 
        1. 등록된 회원 관리
        2. 비밀번호 변경
        3. 로그아웃
    -->

    <div class="menu">
      <div style="padding-right : 2%;">
        <router-link to="/members" style="text-decoration:none">
        <div class="icons">
          <img src="@/assets/members.png" style="width : 120px; height : 130px;" />
        </div>
        <p>등록 회원 관리</p>
        </router-link>
      </div>
      <div style="padding-right : 2%;">
        <router-link to="/visitor" style="text-decoration:none">
          <div class="icons">
            <img src="@/assets/paper.png" width="110px" height="120px;" />
          </div>
          <p>명부 관리</p>
        </router-link>
      </div>
      <div style="padding-right : 2%;">
        <router-link to="/voiceReg" style="text-decoration:none">
          <div class="icons">
            <img src="@/assets/mic.png" width="130px" height="130px;" />
          </div>
          <p>음성 등록</p>
        </router-link>
      </div>

      <!-- <div class="pwd-modal" data-toggle="modal" style="padding-right : 2%;">
        <div class="icons">
          <img src="@/assets/pwd.png" style="width : 130px; height : 120px;" />
        </div>
          <p>비밀번호 변경</p>
      </div> -->
      <div>
        <div class="icons" @click="logout" style="padding-right : 2%;">
          <img src="@/assets/unlock.png" style="width : 120px; height : 120px;" />
        </div>
        <p>로그아웃</p>
      </div>
    </div>

    <div id="changPwdModal" class="modal">
      <div class="modal-content">
        <div class="close" style="text-align : right; cursor : pointer color : black">X</div>
        <ChangePwd />
      </div>
    </div>
  </div>
</template>

<script>
import Nav from "@/components/Nav.vue";
import ChangePwd from "@/views/MainMenu/Account/ChangePwd.vue";

export default {
  name: "Account",
  components: {
    Nav,
    ChangePwd,
  },
  data() {
    return {
      code : this.$store.state.code
    };
  },
  methods: {
    attachModal() {
      const changPwdModal = document.getElementById("changPwdModal");
      const btn = document.querySelector(".pwd-modal");
      const span = document.getElementsByClassName("close")[0];
      btn.onclick = function () {
        changPwdModal.style.display = "flex";
      };
      span.onclick = function () {
        changPwdModal.style.display = "none";
      };

      window.onclick = function (event) {
        if (event.target === changPwdModal) {
          changPwdModal.style.display = "none";
        }
      };
    },
    logout() {
      this.$store.commit("clearMember");
      this.$store.commit("logout");
      this.$cookies.remove("token")
      this.$cookies.remove("code")
      this.$router.push('/')
    },
  },
  mounted() {
    try {
      this.attachModal();
    } catch (e) {
      return;
    }
  },
  created() {
    this.$store.state.code = this.$cookies.get("code")
    this.code = this.$store.state.code
  }
};
</script>
<style scoped>
.menu {
  margin-top: 8%;
  text-align: center;
  padding: 0 20% 0 20%;
  display: flex;
  z-index: 5;
  justify-items: center;
  justify-content: space-between;
}
.intro {
  /* padding-top: 3%; */
  margin-left: 7%;
  margin-right: 7%;
  display: flex;
  z-index: 2;
  justify-items: center;
  justify-content: space-between;
}
.icons {
  height: 150px;
}
img {
  transform: scale(1);
  -webkit-transform: scale(1);
}
img:hover {
  transform: scale(1.2);
  -webkit-transform: scale(1.2);
}
p {
  color: white;
}

.modal {
  display: none;
  position: fixed;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.4);
  margin: 0;
}
.modal-content {
  background-color: #ffffff;
  margin: auto;
  border-radius: 15px;
  width: 600px;
}

/* The Close Button */
.close {
  margin: 20px 25px;
  font-size: 18px;
  font-weight: bold;
  color: rgb(114, 114, 114);
}

.close:hover,
.close:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}
</style>