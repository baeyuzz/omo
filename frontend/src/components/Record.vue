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
        if(!this.uploadBtn){ // false면 자동 업로드

        console.log(data.blob)

        const audio = new FormData()
        audio.append('audio', data.blob, `record.mp3`)

        console.log(audio.getAll('audio'))
        
        const headers = Object.assign({})
        headers['Content-Type'] = `multipart/form-data`
        
        console.log(headers)

        http.post("/uploadAudio", audio, { headers: headers }).then(resp => {
          console.log(data)
            console.log(resp)
          })
            .catch(error => {
              console.log(error)
          })
        }
        else this.callback();
      },
      download (data) {
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
