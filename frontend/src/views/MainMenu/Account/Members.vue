<template>
    <div class="visitor">
        <head> <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet"> <!--CDN 링크 --> </head>

        <Nav/>
            <div class="intro">
                 <router-link to="/account" style="text-decoration:none; color : white;">
                    <h1>◀</h1>
                </router-link>
                <h1>
                    등록 회원 관리
                </h1>
                    <button class="train" @click="train"> 음성 훈련</button>
                
                <h2 />
            </div>
            <div class="list">
                    <table>
                        <tbody>
                            <tr>
                                <td>이름</td>
                                <td>번호</td>
                                <td>삭제</td>
                            </tr>
                            <tr v-for="member in memberList" :key="member">
                                <td>member.name</td>
                                <td>member.phone</td>
                                <td class="remove" @click="remove(member.name, member.phone)"><i class="far fa-trash-alt"></i></td> 
                            </tr>
                            <tr>
                                <td>asdf</td>
                                <td>010</td>
                                <td class="remove"><i class="far fa-trash-alt"></i></td> 
                            </tr>
                        </tbody>
                    </table>
                    
            </div>
  </div>
</template>


<script>
import Nav from '@/components/Nav.vue'
// import http from '@/http-common.js'
import axios from "axios";

export default {
    name: 'Members',
    components: {
        Nav,
    },
     data() {
    return {
        memberList : [],
    };
  },
  methods: {
      train(){
        //   let code = 'ssafy';
        let code = this.$store.state.code

          axios.get(`http://localhost:8081/api/ai/train?code=${code}`)
          alert("음성데이터를 훈련 시킵니다 ! ")
      },
      remove(name, phone){
          const info = {
              name : name,
              phone : phone,
            }
        //   let code = 'ssafy';
          let code = this.$store.state.code

          axios.delete(`http://localhost:8080/api/members?code=${code}`, info)
          .then()
          .catch()
      }

  },
  created () {

    //   let code = 'ssafy';
      let code = this.$store.state.code
      console.log(code)

      axios.get(`http://localhost:8080/api/members?code=${code}`)
      .then((res) => {
          this.memberList = res.data.members;
        }
      )
       .catch((err) => {console.log(err)});

  }
}
</script>
<style scoped>
.intro {
  margin: auto;
  margin-left: 7%;
  margin-right: 7%;
  display: flex;
  z-index: 4;
  justify-items: center;
  justify-content: space-between;
}
.list {
    text-align: center;
    margin : auto;
    margin-top : 3%;
}
table {
    margin : auto;
    text-align: center;
    width : 50%;
}
.visitor {
    text-align: center;
}
td {
    width: 33%;
}
.remove {
    cursor: pointer;
}
.train {
    font-size: 15px;
    outline: none;
    border : none;
    background-color: rgb(0, 161, 224);
    color : white;
    height : 40px;
    margin: auto;
    margin-left : 0;
    margin-right : 0;
    border-radius: 10px;
    cursor: pointer;
    padding : 5px 10px 5px 10px;
}
</style>