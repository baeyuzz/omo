<template>
  <div class="nav">
        <!-- <h1 class="logo" @click="goMain()">
            ㅇㅁㅇ
        </h1> -->
        <img class="logo" @click="goMain()" src="@/assets/icon.png" width="100px"/>

        <div class="menu">
            <button>서비스 소개 </button>
            <button> 공지사항</button>
        </div>
  </div>
</template>

<script>

export default {
    methods : {
        goMain() {
            this.$router.push({
            name: "Main",
      });
    },
    }
}
</script>
<style scoped>
.nav {
    margin : 1% 7% 0 6.5%;
    display: flex;
    justify-content: space-between;
    justify-items: center;
}

img {
    padding-top: 25px;
    transform: scale(1);
    -webkit-transform:scale(1);
  }
  img:hover {
    transform: scale(1.1);
    -webkit-transform:scale(1.1);
  }
button:hover {
    color: #ffffffbb;
}
.menu {
    display: flex;
    justify-content: space-between;
    justify-items: center;
}
button {
    background: none;
    outline: none;
    border: none;
    color : white;
    font-size: 15px;;
}

</style>