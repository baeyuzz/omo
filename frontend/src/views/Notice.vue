<template>
  <div class="notice">
    <Nav/>

    <div class="intro">
      <h2>OMO 서비스 공지사항 </h2>
    </div> 

  <div class="content-box">
			<div class="wrap-table100">
					<div class="table">
						<div class="row header">
							<!-- <div class="cell">
								#
							</div> -->
							<div class="cell">
								제목
							</div>
							<div class="cell">
								등록일
							</div>
						</div>

						<div class="row"  v-for="article in article_list" :key="article.id" >
							<!-- <div class="cell" data-title="Full Name">
								{{ article.id }}
							</div> -->
							<div class="cell" data-title="Age">
                <collapse :selected="false  ">
                  <div slot="collapse-header">
                    {{ article. title }}
                  </div>
                  <div slot="collapse-body">
                    {{ article. contents }}
                  </div>
                </collapse>

							</div>
							<div class="cell" data-title="Job Title">
								{{ article.createdAt.substring(0,article.createdAt.indexOf('T')) }}
							</div>
						</div>
					</div>
			</div>
		</div>
  </div>

</template>

<script>
import Nav from '@/components/Nav.vue'
import http from '@/http-common.js'
import Collapse from 'vue-collapse'

export default {
  name: 'Notice',
  components: {
    Nav,
    Collapse
  },
  data() {
    return {
      article_list: []
    }
  },
   methods: {
    //  attachCollapse(){
    //     var coll = document.getElementsByClassName("collapsible");
    //     console.log(coll)

    //     for (var i = 0; i < coll.length; i++) {
    //       console.log('before')
    //       coll[i].addEventListener("click", function() {
    //                   console.log('before')

    //         this.classList.toggle("active");
    //         var content = this.nextElementSibling;
    //         if (content.style.display === "block") {
    //           content.style.display = "none";
    //         } else {
    //           content.style.display = "block";
    //         }
    //       });
    //     }
    //  }
   },
   mounted() {
   },
   created(){
       http.get('/api/notice').then(res => {
       this.article_list= (res.data);
      //  console.log(this.article_list)
     })
   }
}
</script>
<style scoped>
.intro {
  margin : auto;
  text-align: center;
}

.content-box { 
  width: 60%;
  height: 80%;
  margin : auto;
  background: white;
  border-radius: 12px;
  color: black;
}

a:link { color: #666666; text-decoration: none;}
a:visited { color: #666666; text-decoration: none;}
a:hover { color: black; text-decoration: underline;}

/* [ Table ]*/
.limiter {
  width: 100%;
  margin: 0 auto;
}

.container-table100 {
  width: 100%;
  min-height: 100vh;
  background: #c4d3f6;

  display: -webkit-box;
  display: -webkit-flex;
  display: -moz-box;
  display: -ms-flexbox;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-wrap: wrap;
  padding: 33px 30px;
}

.wrap-table100 {
  width: 100%;
  border-radius: 10px;
  overflow: hidden;
}

.table {
  width: 100%;
  display: table;
  margin: 0;

}

.row {
  display: table-row;
  background: #f7f7f7;
}

.row.header {
  color: #ffffff;
  background: #6c7ae0;
}

.cell {
  display: table-cell;
  text-align: center;
}

.row .cell {
  font-family: Poppins-Regular;
  font-size: 15px;
  color: #666666;
  line-height: 1.2;
  font-weight: unset !important;

  padding-top: 20px;
  padding-bottom: 20px;
  border-bottom: 1px solid #f2f2f2;
}

.row.header .cell {
  font-family: Poppins-Regular;
  font-size: 18px;
  color: #fff;
  line-height: 1.2;
  font-weight: unset !important;

  padding-top: 19px;
  padding-bottom: 19px;
}

.row .cell:nth-child(1) {
  width: 470px;
  padding-left: 40px;
}

.row .cell:nth-child(2) {
  width: 160px;
  padding-right: 40px;

}

/* .row .cell:nth-child(3) {
  width: 200px;
}

.row .cell:nth-child(4) {
  width: 190px;
} */


.table, .row {
  width: 100% !important;
}

.collapsible {
  cursor: pointer;
  padding: 18px;
  width: 100%;
  border: none;
  text-align: left;
  outline: none;
  font-size: 15px;
}

.active, .collapsible:hover {
  color: rgb(180, 180, 180);
}

.content {
  text-align: left;
  padding: 30px;
  display: none;
  overflow: hidden;
  background-color: #f7f7f7;
}
</style>