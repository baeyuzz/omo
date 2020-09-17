<template>
  <div class="account">
    <Nav/>

    <div class="intro">
        <router-link to="/main" style="text-decoration:none; color : white;">
          <h1>◀</h1>
        </router-link>
        <h2>계정 설정</h2>
        <h2/>
    </div> 
    <!-- 
        1. 등록된 회원 관리
        2. 비밀번호 변경
        3. 로그아웃
    -->
   
    <div class="menu">
        <div>
            <div class="icons">
                <img src="@/assets/paper.png" style="width : 160px; height : 170px;"/>
            </div>
            <p>
                등록 회원 관리
            </p>
        </div>
        <div class="pwd-modal" data-toggle="modal" >
            <div class="icons"> 
                <img src="@/assets/pwd.png" style="width : 180px; height : 160px;"/>	
              <p>
                비밀번호 변경
              </p>
            </div>
        </div>
        <div>
            <div class="icons">
                <img src="@/assets/unlock.png" style="width : 160px; height : 160px;"/>	
            </div>
            <p>
                로그아웃
            </p>
        </div>
            
    </div>

    <div id="changPwdModal" class="modal">
      <div class="modal-content">
        <div class="close" style="text-align : right; cursor : pointer color : black">X</div>
        <ChangePwd/>
      </div>
    </div>
  </div>

</template>

<script>
import Nav from '@/components/Nav.vue'
import ChangePwd from '@/views/ChangePwd.vue'

export default {
  name: 'Account',
  components: {
    Nav,
    ChangePwd
  },
  data() {
    return {
      
    }
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
        if (
          event.target === changPwdModal
        ) {
          changPwdModal.style.display = "none";
        }
      };
    },

   },
   mounted() {
    try {
     this.attachModal();
    } catch (e) {
      return
    }
  },
}
</script>
<style scoped>
  .menu {
    margin-top : 8%;
    text-align: center;
    padding: 0 25% 0 25%;
    display: flex;
    z-index: 3;
    justify-items: center;
    justify-content: space-between;
  }
  .intro {
    padding-top : 3%;
    margin-left : 7%;
    margin-right : 7%;
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

  
.modal {
  display: none; 
  position: fixed; 
  left : 0;
  top: 0;
  width: 100%; 
  height: 100%;
  background-color: rgba(0, 0, 0, 0.4); 
  margin : 0;
}
.modal-content {
  background-color: #ffffff;
  margin : auto;
  border-radius: 15px;
  width: 600px;
}

/* The Close Button */
.close {
  margin : 20px 25px;
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