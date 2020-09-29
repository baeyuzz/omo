<template>
  <div id="warn">
      <div >
        <h2>Warning!</h2>
        <p>사이즈가 너무 작습니다!</p>
        <p>화면 크기를 늘리거나, 다른사이즈의 디바이스로 들어와주세요.</p>
        <p>권장사이즈 : 1920 1080</p>
        <p>권장 브라우저 : 구글 Chrome</p>
      </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>
    #warn{
        position: absolute;
        width: 100vw;
        height: 100vh;
        align-items: center;
        background-image: linear-gradient(
            -225deg,
            #473b7bdd 0%,
            #3584a7dd 51%,
            #30d2bedd 100%
        );
        margin: 0;
        text-align: center;
        padding: 15% 0;
    }
</style>