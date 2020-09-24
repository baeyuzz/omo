
<template>
  <div class="record">

    <Nav/>
    <div style="text-align : center">
        <h1>음성 인식</h1>
        
    </div>
    <div id="notice" style="text-align : center">
            안녕하세요 ~
            <br>
            녹음 버튼을 누른 후 6초 간
            <br>
            오늘 날짜와 이름을 말씀해주세요

    </div>
    <div id="recorder">
        <Record
        :attempts='3'
        :time='0.1'
        :uploadBtn='false'
        />
        <router-link to="/main" style="text-decoration:none; color : white;">취소 </router-link>
    </div>
  </div>
</template>

<script>
import Nav from '@/components/Nav.vue'
import Record from '@/components/Record.vue'

export default {
    name: 'VoiceRecognition',
    components: {
        Nav,
        Record,
    },
}
</script>
<style scoped>
#recorder {
    text-align: center;
    
}
</style>


