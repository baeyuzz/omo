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
                        <tbody>
                            <tr>
                                <td>날짜</td>
                                <td>이름</td>
                            </tr>
                            <tr v-for="log in visitorlogs" :key="log">
                                <td>log.visitTime</td>
                                <td>log.name</td>
                            </tr>
                            <tr>
                                <td>2020/09/24 19:30:23</td>
                                <td>유진,,</td>
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
        visitorlogs : '',
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
    width: 50%;
}
</style>