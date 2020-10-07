<template>
  <div class="visitor">
    <Nav />
    <div class="intro">
      <router-link to="/account" style="text-decoration: none; color: white">
        <h1>◀</h1>
      </router-link>
      <h1>방문자 출입 명부</h1>
      <h2 />
    </div>
    <div class="list" style="margin-bottom : 2%;">
      <table class="logtable">
        <thead>
          <tr>
            <th>날짜</th>
            <th>이름</th>
            <th>번호</th>
            <!-- <th>주소</th> -->
          </tr>
        </thead>
      </table>
    </div>
    <div class="list" style="height : 400px;">
      <div v-if="visitorlogs != null">
      <table class="logtable">
        <tbody>
          <tr v-for="(log, index) in visitorlogs" :key="index">
            <td>{{ log.visitTime.replace("T", " ") }}</td>
            <td>{{ log.name }}</td>
            <td>{{ log.phone }}</td>
            <!-- <td>{{log.address}}</td> -->
          </tr>
        </tbody>
      </table>
      </div>
        <div v-else>내용 없음</div>
    </div>
  </div>
</template>


<script>
import Nav from "@/components/Nav.vue";
import http from "@/http-common.js";

export default {
  name: "VisitorList",
  components: {
    Nav,
  },
  data() {
    return {
      visitorlogs: [],
    };
  },
  methods: {},
  created() {
    this.$store.state.code = this.$cookies.get("code");

    http
      .get("api/visitors/" + this.$store.state.code)
      .then((res) => {
        this.visitorlogs = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
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
  margin: auto;
  overflow: auto;
  width: 50%;
}
.logtable {
  margin: auto;
  text-align: center;
  width : 100%;
}
.visitor {
  text-align: center;
  height: 100%;
}
td {
  width: 33%;
  padding: 10px;
}
th {
  width: 33%;
}
.list::-webkit-scrollbar {
  width: 6px;
}
.list::-webkit-scrollbar-track {
  background-color: transparent;
}
.list::-webkit-scrollbar-thumb {
  border-radius: 3px;
  background-color: rgba(255, 255, 255, 0.300);
}
.list::-webkit-scrollbar-button {
  width: 0;
  height: 0;
}
</style>