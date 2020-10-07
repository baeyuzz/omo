<template>
  <div class="footer"
    style="text-align : center; font-size: 13px; width: 95%;margin-left:2.5%"
  >
    <hr style="border : 1px solid white;" />
    <div style="margin-bottom : 10px; padding-top : 10px;">
      SSAFY 3기 서울 5반 9조 | 김강현  배유진  배재원  정용우  최문경
      <!-- <a style="cursor : pointer" @click="goPrivacyPolicy">개인정보처리방침 </a>
      <a style="cursor : pointer" @click="goTermsOfUse">이용약관 </a>
      문의사항 : twlssafy@gmail.com -->
      <!-- <span
        v-if="isLoggedIn"
        style="margin:10px; cursor:pointer"
        @click="delAcc"
      >
        회원탈퇴
      </span> -->
    </div>
  </div>
</template>
<script>
// import { mapGetters } from 'vuex';
// import { delAccount } from '../../api';
// import Swal from 'sweetalert2/dist/sweetalert2.js'
// import 'sweetalert2/src/sweetalert2.scss'

export default {
//   computed: {
//     ...mapGetters(['isLoggedIn']),
//   },
//   methods: {
//     goPrivacyPolicy(){
//       this.$router.push({ name: "PrivacyPolicy"})
//     },
//     goTermsOfUse(){
//       this.$router.push({ name: "TermsOfUse"})
//     },
//     delAcc() {
//       Swal.fire({
//         text:"탈퇴 하시겠습니까?",
//         icon:"question",
//         closeOnClickOutside:true,
//         showCancelButton:true,
//         confirmButtonText:"<span style='text-align:center; margin-left:-12px; position:relative; top:-8px;'>OK</span>",
//         confirmButtonColor:"#e6837a",
//         cancelButtonText:"<span style='text-align:center; margin-left:-12px; position:relative; top:-8px;'>NO</span>",
//         cancelButtonColor:"#e6837a",
//         }).then((result) => {
//           if(result.value){
//             delAccount(this.$store.state.id_token).then((res) => {
//               if (res.data.status) {
//                 this.$router.push({ name: 'Logout' });
//                 Swal.fire({
//                   text:"탈퇴 되었습니다.",
//                   icon:"success",
//                   closeOnClickOutside:true,
//                   confirmButtonText:"<span style='text-align:center; margin-left:-12px; position:relative; top:-8px;'>OK</span>",
//                   confirmButtonColor:"#e6837a",
//                 });
//               } else {
//                 Swal.fire({
//                   text:"회원탈퇴 실패 ! 관리자에게 문의하세요.",
//                   icon:"error",
//                   closeOnClickOutside:true,
//                   confirmButtonText:"<span style='text-align:center; margin-left:-12px; position:relative; top:-8px;'>OK</span>",
//                   confirmButtonColor:"#e6837a",
//                 });
//               }
//             });
//           } else {
//             Swal.close();
//           }
//         });
      
//     },
//   },
};
</script>
<style scoped>

</style>