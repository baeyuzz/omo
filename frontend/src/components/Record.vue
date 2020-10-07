<template>
  <div class="record">
    <div v-if="!isLoading">
      <Loading/>
    </div>
    <div v-else class="recorder">
      <audio-recorder
          :attempts=attempts
          :time=time
          :after-recording='upload'
          :show-upload-button=uploadBtn
          :show-download-button='true'
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
import AudioRecorder from '@/index.js'
import Loading from "@/components/loading.vue";

// import http from '@/http-common.js'
import axios from "axios";

Vue.use(AudioRecorder)

export default {
    name: 'Record',
     components: {
       Loading
    },
    data() {
      return {
        isLoading: true,
        }
    },
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
      callback () {
        // console.log('callback')
        // console.debug(data)
      },
      callbackUploading () {
        // console.log('upload')
        // console.debug(data)
      },
      upload(data){
        // console.log(this.$store.state.code)
        this.isLoading = false;
        // console.log(data.blob)

        // this.$store.state.code = "team9"
        // this.$store.state.name = "team9"
        // this.$store.state.phone = "team9"

        const audio = new FormData()
        audio.append('audio', data.blob, `record.wav`)

        // console.log(audio.getAll('audio'))
          
        const headers = {}
        headers['Content-Type'] = `multipart/form-data;`

        // console.log(headers)
        // console.log(audio)

        if(!this.uploadBtn){ // 업로드 버튼이 false면 자동 업로드 -> 명부 작성 시 사용
          

          const info = {
            code : this.$store.state.code,
            token : this.$cookies.get('token')
          }
          axios.post("http://localhost:8081/api/ai/uploadAudio4list", audio, {headers : info})
          .then(resp => {
            console.log(resp.data)
            var confirm_test = confirm(resp.data + "님이 맞으신가요?");
            
            if ( confirm_test == true ) {
              let today = new Date();
              alert(resp.data+"님 " + today + "방문" )
              this.$router.push({name: "Main"});
          } else if ( confirm_test == false ) {
            alert("다시 녹음해 주세요")
            this.isLoading = true;
          }
          })
          .catch(error => {
            // console.log(error)
            alert(error)
            this.isLoading = true;

          })
          

        }
        else {
          // console.log(this.$store.state.code)
          this.isLoading = false;
          const info = {
            name : this.$store.state.name,
            addr : this.$store.state.addr,
            phone : this.$store.state.phone,
            code : this.$store.state.code,
            token : this.$cookies.get('token')
          }

          axios.post("http://localhost:8081/api/ai/uploadAudio4member", audio, {headers : info})
          .then(resp => {

            // console.log(resp.data)
            
            if(resp.data) {
              
              this.$store.commit("setPhone", '');
              this.$store.commit("setName", '');
              this.$store.commit("setAddr", '');
              
              alert ('등록 되었습니다')
              this.$router.push({ name: "Main" });
              }
          })
          .catch(error => {
            alert(error)
            this.isLoading = true;

          })
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
