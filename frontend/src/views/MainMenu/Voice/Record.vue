<template>
  <div class="record">
    <Nav/>
    <div style="text-align : center">
      <h1>음성등록</h1>
    </div>
    <div class="recorder">
      <audio-recorder
          :attempts="1"
          :time="1.5"
          :after-recording="upload"
          :show-upload-button='false'
          :show-download-button='false'
          />
    </div>
  </div>
</template>

<script>
import Vue from 'vue'
import VueAudioRecorder from 'vue-audio-recorder'
import Nav from '@/components/Nav.vue'
import http from '@/http-common.js'

Vue.use(VueAudioRecorder)

export default {
    name: 'Record',
    components: {
        Nav
    },
    
    methods: {
      callback (data) {
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
        console.debug(audio)
        
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
  margin-top : 5%;
  display: flex;
  justify-content: center;
  text-align: center;
}
</style>
