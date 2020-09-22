<template>
  <div class="record">

    <div class="recorder">
      <audio-recorder
          :attempts=attempts
          :time=time
          :after-recording='upload'
          :show-upload-button=uploadBtn
          :show-download-button='false'
          />
          <!--
            attempts - 몇번 녹음 가능한지
            time - 몇 분 녹음할지 (분단위임!)
            after-recording - 녹음 정지 / 시간 초과 시 실행되는 함수
            show-xxx-button - 업로드/다운로드 버튼 보이게 할 건지 
          -->
    </div>
  </div>
</template>

<script>
import Vue from 'vue'
import VueAudioRecorder from 'vue-audio-recorder'

import http from '@/http-common.js'

Vue.use(VueAudioRecorder)

export default {
    name: 'Record',
    props: {
      time :{
        type: Number,
        required: true,
      },
      attempts :{
        type: Number,
        required: true,
      },
      uploadBtn :{
        type: Boolean,
        required: true,
      },
     
    },

    methods: {
      callback (data) {
        console.log('callback')
        console.debug(data)
      },
      callbackUploading (data) {
        console.log('upload')
        console.debug(data)
      },
      upload(data){
         console.log(data.blob)

          const audio = new FormData()
          audio.append('audio', data.blob, `record.mp3`)

          console.log(audio.getAll('audio'))
          
          const headers = Object.assign({})
          headers['Content-Type'] = `multipart/form-data`
          
          console.log(headers)

        if(!this.uploadBtn){ // 업로드 버튼이 false면 자동 업로드 -> 명부 작성 시 사용

         
          http.post("/uploadAudio4list", audio, { headers: headers }).then(resp => {
            // 백으로 mp3파일 보내줌,, wav로 보내고 싶은데 ㅠ
            console.log(data)
             console.log(resp)
            })
              .catch(error => {
                console.log(error)
            })
        }
        else {
           
          http.post("/uploadAudio4member", audio, { headers: headers }).then(resp => {
            // 백으로 mp3파일 보내줌,, wav로 보내고 싶은데 ㅠ
            console.log(data)
             console.log(resp)
            })
              .catch(error => {
                console.log(error)
            })
            // upload 버튼이 true면 그거 눌러서 업로드하면 됨 -> 음성 등록 시 사용
        }
      },
      download (data) { // download는 사실 쓸 일 없을 듯?
        const type = data.blob.type.split('/')[1]
        const link = document.createElement('a')
        link.href = data.url
        link.download = `${this.filename}.${type}`
        link.click()
      }
    },
   
}
</script>
<style scoped>
.recorder {
  display: flex;
  justify-content: center;
  text-align: center;
}

</style>
