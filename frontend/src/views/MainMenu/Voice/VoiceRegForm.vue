<template>
    <div>
        <Nav/>
        
        <div style="text-align : center; color : black; margin-top : 4%" class="regForm" >
            <div style="margin-top : 2%; background-color : white; width : 480px; border-radius : 15px">
            <div class="regForm" style="padding-top : 4%;">
                <h1>
                    음성등록
                </h1>
                
            </div>
                <div class="regForm">
                    <table>
                        <tbody>
                            <tr>
                                <td><label>기관 코드</label></td>
                                <td> <input class="regInput" placeholder="ex. A123" v-model="code"/></td>
                            </tr>
                            <tr>
                                <td><label>이름</label></td>
                                <td> <input class="regInput" placeholder="ex. 홍길동" v-model="name"/></td>
                            </tr>
                            <tr>
                                <td><label>휴대 전화</label></td>
                                <td><input class="regInput" type="tel" placeholder="ex. 010-1234-1234" v-model="phone"/></td>
                            </tr>
                            <tr>
                                <td><label>주소</label></td>
                                <td><input class="regInput" placeholder="ex. 경기 안양" v-model="addr"/></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="regForm regMent">
                    다음 버튼을 누르시면 음성 인식을 위한 녹음이 시작됩니다
                </div>
                <div class="regForm">
                    <router-link to="/account" class="regBtn">취소</router-link>
                    <button class="regBtn" @click=submitForm()>다음</button>
                </div>
            </div>
        </div>
  </div>
</template>


<script>

import Nav from '@/components/Nav.vue'
import http from '@/http-common.js'
export default {
  name: 'VoiceRegForm',
  components: {
    Nav
  },
  data() {
    return {
      name: '',
      phone: '',
      addr: '',
      code: '',
    };
  },
  methods: {
    async submitForm() {
        if(this.code.length == 0) {
            alert('기관 코드를 입력하세요');
            this.$refs.code.focus();
            return;
        }
        if(this.name.length == 0) {
                alert('이름을 입력하세요');
                this.$refs.name.focus();
                return;
        }
        if(this.phone.length == 0) {
            alert('휴대 전화를 입력하세요');
            this.$refs.phone.focus();
            return;
        }
        if(this.addr.length == 0) {
            alert('주소를 입력하세요');
            this.$refs.addr.focus();
            return;
        }

        this.$store.commit("setPhone", this.phone);
        this.$store.commit("setName", this.name);
        this.$store.commit("setAddr", this.addr);
        this.$store.commit("setCode", this.code);

        http.post('/아직안정해짐')
            .then((res) => {
                    if(res.data.state){ // 등록이 되면
                        this.$router.push({name: 'RegRecord',});
                        console.log('success')
                    } else {
                        console.log('fail')
                    }
                } 
            )
            .catch((err) => {
                this.$router.push({name: 'RegRecord',});
                alert('Error ', err)
                });
    },
  },

}
</script>
<style scoped>
.regForm {
    width: 100%;
    text-align: center;
    display: flex;
    justify-content: center;
}
.regInput {
    background-color: #ffffff00;
    font-size : 15px;
    padding : 15px 20px 15px 8px;
    margin: 5px;
    /* border-radius: 10px; */
    border : none;
    outline: none;
    width: 250px;
    border-bottom: 1px solid;
    color : black;
}
.regInput::placeholder {
  color: #1624429d;
  font-size : 15px;
  font-weight: bold;
}
.regBtn {
    text-decoration: none;
    padding : 5px 15px 5px 15px;
    margin: 30px 5px 8% 5px;
    border-radius: 5px;
    border : none;
    outline: none;
    background-color: #005f7c7e;
    color : black;
    box-shadow: 0.5px 1px #292929;
    text-align: center;
    display: flex;
    justify-content: center;
    font-size: 15px;
}
table{
    text-align: left;
    margin-top: 5%;
    border-spacing: 0 15px;
}

.inputGroup{
    display: flex;
    justify-content: center;
}

.regMent {
    margin-top : 40px;
    margin-bottom : 15px;
}
label {
    font-size : 18px;
    margin: 5px;
}
</style>