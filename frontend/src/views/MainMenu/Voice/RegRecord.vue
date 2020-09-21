
<template>
  <div class="record">

    <Nav/>
    <div style="text-align : center">
      <h1>음성등록</h1>
      <p>
        녹음 버튼을 누르고 아래의 내용을 읽어주세요
      </p>
    </div>
    <Record
    :attempts='1'
    :time='1.5'
    :uploadBtn='true'
    />
    <div id="notice" style="text-align : center">
      <br>
      이 내용을 읽어주세요 !! 
      <br>
      총 1분 30초 동안 녹음 됩니다.
      <br>
      큰 소리로 읽어주세요
      <br>
      가급적 마이크를 이용해 말씀해주세요
      <br>
      주변 소음이 녹음되지 않도록 해주세요
      <br>
      새로고침은 하지 말아주세요
      <br>
      녹음이 끝나면 목록에서 저장 버튼을 눌러주세요
      <br>
      재녹음을 하고 싶으시면 목록에서 X 버튼을 누르고 다시 녹음 해주세요
      <br>
      목소리를 판별하기 위한 녹음이므로, 말하는 내용은 아무 것이나 상관 없습니다.
      <br>
      따라서, 꼭 여기 있는 내용대로 말하지 않으셔도 됩니다
      <br>
      단, 1분 30초만 채워주시면 됩니다
      <br>
      지정된 시간보다 짧게 녹음 시, 음성 인식이 제대로 되지 않을 수 있습니다
      <br>
      녹음한 파일은 어디에도 저장되지 않습니다.
      <br>
      녹음 파일은 데이터화 되고 이 데이터를 통해 목소리를 판별합니다.
      <br>

    </div>
  </div>
</template>

<script>
import Nav from '@/components/Nav.vue'
import Record from '@/components/Record.vue'

export default {
    name: 'RegRecord',
    components: {
        Nav,
        Record,
    },
}
</script>
<style scoped>

</style>


