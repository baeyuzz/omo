<template>
  <div class="nav">
        <!-- <h1 class="logo" @click="goMain()">
            ㅇㅁㅇ
        </h1> -->
        <img class="logo" @click="goMain()" src="@/assets/icon.png" width="100px"/>

        <div class="menu">
            <button>서비스 소개 </button>
            <button><router-link to="/notice">공지사항</router-link></button>
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
a:link { color: white; text-decoration: none;}
a:visited { color: white; text-decoration: none;}
a:hover { color: #ffffffbb; text-decoration: none;}
.logo {
}
.logo:hover, button:hover {
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