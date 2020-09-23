
<template>
  <div class="record">
    <head>
      <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">

    </head>
    <Nav/>
    <div style="text-align : center;   padding-top : 4%;">
      <h1>음성등록</h1>
      <p>
        녹음 버튼을 누르고 아래의 내용을 읽어주세요
      </p>
    </div>
    <div id="recordernscript">
      <Record
      :attempts='1'
      :time='1.5'
      :uploadBtn='true'
      />
      <div class="noticeBox">
        <div id="notice">
          이 내용을 읽어주세요.
          녹음이 끝나면 목록에서 녹음 파일을 선택한 후, 업로드 버튼을 눌러주세요.
          목소리를 판별하기 위한 녹음이므로, 말하는 내용은 아무 것이나 상관 없습니다.
          따라서, 꼭 여기 있는 내용대로 말하지 않으셔도 됩니다.
          단, 1분 30초만 채워주시면 됩니다.
          지정된 시간보다 짧게 녹음 시, 음성 등록이 제대로 되지 않을 수 있습니다.
          총 1분 30초 동안 녹음 됩니다.
          큰 소리로 읽어주세요.
          가급적 마이크를 이용해 말씀해주세요.
          주변 소음이 녹음되지 않도록 해주세요.
          새로고침은 하지 말아주세요.
          만일 새로고침을 했을 시에는 처음으로 돌아가 기관 코드, 이름, 휴대 전화, 주소를 다시 입력해주세요.
          재녹음을 하고 싶으시면 목록에서 X 버튼을 눌러 삭제 후, 다시 녹음 해주세요.
          녹음 파일은 데이터화 되고 원본 파일은 저장 되지 않습니다.
          업로드 다음 날부터 음성 인식이 가능합니다.
        </div>
      </div>
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

#recordernscript {
  display: flex;
  justify-content: center;
  justify-items: center;
  margin: auto;
  margin-top : 40px;
}
#notice {
  /* width: 50%; */
  color: black;
  overflow: auto;
  font-family: 'Noto Sans KR', sans-serif;
  height: 100%;
}
.noticeBox {
  background-color: #FAFAFA;
  margin-left : 10px;
  border-radius: 15px;
  padding : 30px;
  height: 325px;
  width: 370px;
  
}

#notice::-webkit-scrollbar {
  width: 3px;
  }
#notice::-webkit-scrollbar-thumb {
  background-color: #6a98ad;
    border-radius: 10px;
  }
#notice::-webkit-scrollbar-track {
  background-color: rgba(167, 167, 167, 0);
    border-radius: 30px;
    /* box-shadow: inset 0px 0px 5px white; */
  }
</style>