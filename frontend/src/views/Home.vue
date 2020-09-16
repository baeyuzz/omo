<template>
  <div class="home">
    <Nav/>

    <div class="intro">
      <h2>Only Mask On your face </h2>
      <h1> OMO </h1>
      <div>
        위험한 코로나 시대,
        마스크를 벗지 않아도
        <br>
        당신을 알아봐주는
        자동 출입 명부 작성 서비스
      </div>
    </div>
    <Login/>
  </div>
</template>

<script>
import Login from '@/components/Login.vue'
import Nav from '@/components/Nav.vue'

export default {
  name: 'Home',
  components: {
    Login,
    Nav
  }
}
</script>
<style scoped>
.intro {
  padding-left : 7%;
}
</style>