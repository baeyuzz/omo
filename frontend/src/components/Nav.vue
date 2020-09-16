<template>
  <div class="nav">
        <h1 class="logo" @click="goHome()">
            ㅇㅁㅇ
        </h1>
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
}

</style>