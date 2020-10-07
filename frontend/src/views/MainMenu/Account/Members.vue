<template>
  <div class="visitor">
    <head>
      <link
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css"
        rel="stylesheet"
      />
      <!--CDN 링크 -->
    </head>

    <Nav />
    <div class="intro">
      <router-link to="/account" style="text-decoration: none; color: white">
        <h1>◀</h1>
      </router-link>
      <h1>등록 회원 관리</h1>
      <button class="train" @click="train">음성 훈련</button>

      <h2 />
    </div>
    <div class="list" style="margin-bottom: 2%">
      <table class="logtable">
        <thead>
          <tr>
            <th>#</th>
            <th>이름</th>
            <th>번호</th>
            <th>주소</th>
            <th>삭제</th>

            <!-- <th>주소</th> -->
          </tr>
        </thead>
      </table>
    </div>
    <div class="list" style="height: 400px">
      <div v-if="memberList != null">
        <table class="logtable">
          <tbody>
            <tr v-for="(member, idx) in memberList" :key="member.id">
              <td>{{ idx + 1 }}</td>
              <td>{{ member.name }}</td>
              <td>{{ member.phone }}</td>
              <td>{{ member.address }}</td>
              <td class="remove" @click="remove(member.id)">
                <i class="far fa-trash-alt"></i>
              </td>

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
// import http from '@/http-common.js'
import axios from "axios";
import { getEmp, deleteEmp } from "@/lib/userApi.js";

export default {
  name: "Members",
  components: {
    Nav,
  },
  data() {
    return {
      memberList: [],
    };
  },
  methods: {
    train() {
      //   let code = 'ssafy';
      let code = this.$store.state.code;

      alert("음성데이터를 훈련 시킵니다 !");
      axios
        .get(`http://localhost:8081/api/ai/train?code=${code}`)
        .then(() => {
          alert("음성데이터 훈련이 끝났습니다");
        })
        .catch((err) => {
          alert("음성데이터 훈련 실패 ", err);
        });
    },
    remove(id) {
      deleteEmp(id)
        .then(() => {
          alert("삭제되었습니다");

          getEmp()
            .then((res2) => {
              console.log(res2);
              this.memberList = res2.data;
            })
            .catch((err) => {
              console.log(err);
            });
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  created() {
    // const auth = "Bearer " + this.$cookies.get("token");
    // console.log(auth)
    getEmp()
      .then((res) => {
        console.log(res);
        this.memberList = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
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
  margin: auto;
  overflow: auto;
  width: 50%;
}
.logtable {
  margin: auto;
  text-align: center;
  width: 100%;
}
.visitor {
  text-align: center;
  height: 100%;
}

td {
  width: 20%;
  padding: 10px;
}
th {
  width: 20%;
}
.remove {
  cursor: pointer;
}
.train {
  font-size: 15px;
  outline: none;
  border: none;
  background-color: rgb(0, 161, 224);
  color: white;
  height: 40px;
  margin: auto;
  margin-left: 0;
  margin-right: 0;
  border-radius: 10px;
  cursor: pointer;
  padding: 5px 10px 5px 10px;
}
.list::-webkit-scrollbar {
  width: 5px;
}
.list::-webkit-scrollbar-track {
  background-color: transparent;
}
.list::-webkit-scrollbar-thumb {
  border-radius: 3px;
  background-color: rgba(255, 255, 255, 0.3);
}
.list::-webkit-scrollbar-button {
  width: 0;
  height: 0;
}
</style>