<template>
    <div class="visitor">
        <Nav/>
            <div class="intro">
                 <router-link to="/account" style="text-decoration:none; color : white;">
                    <h1>◀</h1>
                </router-link>
                <h1>
                    방문자 출입 명부
                </h1>
                <h2 />
            </div>
            <div class="list">
                <!-- <div v-if="visitorlogs != ''"> -->
                    <table>
                        <thead>
                            <tr>
                                <th>날짜</th>
                                <th>이름</th>
                                <th>번호</th>
                                <th>주소</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="log in visitorlogs" :key="log">
                                <td>log.visitTime</td>
                                <td>log.name</td>
                                <td>log.phone</td>
                                <td>log.address</td>
                            </tr>
                            <tr>
                                <td>2020/10/05 14:46:35</td>
                                <td>배재원</td>
                                <td>010-1234-5678</td>
                                <td>경기 성남</td>
                            </tr>
                            <tr>
                                <td>2020/09/25 20:35:13</td>
                                <td>김강현</td>
                                <td>010-6564-5678</td>
                                <td>서울 관악</td>
                            </tr>
                            <tr>
                                <td>2020/09/25 16:38:24</td>
                                <td>김강현</td>
                                <td>010-6564-5678</td>
                                <td>서울 관악</td>
                            </tr>
                            <tr>
                                <td>2020/09/25 15:10:51</td>
                                <td>최문경</td>
                                <td>010-6547-3215</td>
                                <td>서울 송파</td>
                            </tr>
                            <tr>
                                <td>2020/09/24 19:30:23</td>
                                <td>배유진</td>
                                <td>010-3030-4565</td>
                                <td>경기 하남</td>
                            </tr>
                            <tr>
                                <td>2020/09/24 11:43:20</td>
                                <td>정용우</td>
                                <td>010-6547-3214</td>
                                <td>서울 관악</td>
                            </tr>
                        </tbody>
                    </table>
                <!-- </div>
                <div v-else>내용 없음</div> -->
            </div>
  </div>
</template>


<script>
import Nav from '@/components/Nav.vue'
import http from '@/http-common.js'

export default {
    name: 'VisitorList',
    components: {
        Nav,
    },
     data() {
    return {
        visitorlogs : [],
    };
  },
  methods: {

  },
  created () {
      http.get("api/visitors/"+ this.$store.state.code)
      .then(res =>{
          this.visitorlogs = res;
          console.log(res);
      })
      .catch( err =>{
          console.log(err)
      });
  }
}
</script>
<style scoped>
.intro {
  margin-left: 7%;
  margin-right: 7%;
  display: flex;
  z-index: 2;
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
    width: 25%;
    padding : 10px;
}
th {
    padding-bottom: 20px;
}
</style>