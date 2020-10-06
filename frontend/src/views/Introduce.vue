<template>
  <div class="introduce">
    <Nav />
    <div class="desc">
        <h1>Only Mask On your face</h1>
        <div>
            <h3>회사, 사무실, 학원 등 매일 정해진 사람들이 방문하는 곳에서
                마스크를 착용 여부를 확인하고 목소리를 인식하여
                자동으로 명부를 작성해주는 서비스</h3>
        </div>
    </div>
    <br>
    <br>
    <br>
    <div style="width : 50%; margin : auto">
        <carousel  
        :per-page="1" 
        :navigate-to="someLocalProperty" 
        :loop="true" 
        :navigation-enabled="true"
        :paginationPadding='5'
        :paginationActiveColor="'#00264d'"
        :navigationNextLabel="'>'"
        :navigationPrevLabel="'<'"
        >
            <slide style="text-align : center">
                <img src="@/assets/intro/join.png">
                <br>
                <br>
                OMO를 사용할 기관명 (ex. ssafy) 을 기입해서 가입해주세요
            </slide>
            <slide style="text-align : center">
            
                <img src="@/assets/intro/main.png" width="70%;" height="300px;">
                <br>
                <br>
                방문자는 명부 작성을 누르면 웹캠을 통해 마스크를 인식한 후, 음성 인식 (녹음) 페이지로 이동합니다.
                <br>
                관리자는 계정 설정을 통해 회원·명부·계정 관리 및 음성 등록을 할 수 있습니다.
            </slide>
            <slide style=" text-align : center">
                <img src="@/assets/intro/voiceReg.png" height="350px;">
                <br>
                사용자는
                https://j3a509.p.ssafy.io/voiceReg 에서
                <br>
                기관명을
                입력하시면
                해당 기관에 자신의 음성을 등륵할 수 있습니다.
            </slide>
            <slide style="padding : 10px; text-align : center">
                <img src="@/assets/intro/training.png" width="90%" height="280px;">
                <br>
                관리자는 계정 설정 > 등록 회원 관리 탭에서
                음성 훈련 및 등록된 회원들을 관리 할 수 있습니다.
                <br>
                <br>
                '음성 훈련' 버튼을 통해 새로 등록된 음성들이
                인식될 수 있도록 합니다.
                <br>
                음성 데이터 훈련 완료 알림창이 뜨기 전까지는
                <br>
                전체적인 기능이 느려질 수 있으므로
                가급적 이용자가 없을 시간에 눌러주세요.
            </slide>
        </carousel>
    </div>
  </div>
</template>

<script>
import Nav from "@/components/Nav.vue";
import { Carousel, Slide } from 'vue-carousel';

export default {
  name: "Introduce",
  components: {
    Nav,
    Carousel,
    Slide
  },
  data() {
      return {
       
      };
  }
};
</script>


<style scoped>
h1 {
  margin-top: 5vh;
}
.desc {
    padding-left: 7%;
    text-align: left;
    margin : auto;
}
.example-slide {
    align-items: center;
    background-color: #666;
    color: #999;
    display: flex;
    font-size: 1.5rem;
    justify-content: center;
    min-height: 10rem;
}
img {
    border-radius: 15px;
}
</style>