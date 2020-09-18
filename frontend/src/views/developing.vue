<template>
  <div>
    <Nav />
    <h1>Sorry</h1>
    <h5>현재 페이지는 아직 개발중인 페이지입니다.</h5>
    <h5>빠른 시일내로 완료하겠습니다.</h5>
  </div>
</template>

<script>
import Nav from "@/components/Nav.vue";

export default {
  name: "VoiceRegForm",
  components: {
    Nav,
  },
};
</script>


<style scoped>
div {
  text-align: center;
}
h1 {
  margin-top: 20vh;
}
</style>