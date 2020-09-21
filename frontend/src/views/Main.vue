<template>
  <div class="main">
    <Nav />

    <div class="menu">
      <div>
        <router-link to="/createList" style="text-decoration:none">
          <div class="icons">
            <img src="@/assets/frame.png" width="170px" height="170px;" />
          </div>
          <p>명부 작성</p>
        </router-link>
      </div>

      
      <div>
        <router-link to="/account" style="text-decoration:none">
          <div class="icons">
            <img src="@/assets/key.png" width="170px" height="170px;"/>
          </div>
          <p>
          계정 설정
          </p>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import Nav from "@/components/Nav.vue";

export default {
  name: "Main",
  components: {
    Nav,
  },
};
</script>

<style scoped>
  .menu {
    position : fixed;
    top : 42%;
    width: 30%;
    text-align: center;
    padding: 0 35% 0 35%;
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
</style>
