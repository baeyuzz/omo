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
							<div class="cell">
								#
							</div>
							<div class="cell">
								제목
							</div>
							<div class="cell">
								등록일
							</div>
						</div>

						<div class="row"  v-for="(article, idx) in article_list" :key="idx" >
							<div class="cell" data-title="Full Name">
								{{ idx +1 }}
							</div>
							<div class="cell" data-title="Age">
                <div class="collapsible">{{ article }}</div>
                <div class="content">
                  첫 공지사항 입니다
                </div>

							</div>
							<div class="cell" data-title="Job Title">
								2020-09-16
							</div>
						</div>
					</div>
			</div>
		</div>
  </div>

</template>

<script>
import Nav from '@/components/Nav.vue'

export default {
  name: 'Notice',
  components: {
    Nav
  },
  data() {
    return {
      article_list: [
        '공지사항',
      ]
    }
  },
   methods: {
     attachCollapse(){
        var coll = document.getElementsByClassName("collapsible");
        var i;

        for (i = 0; i < coll.length; i++) {
          coll[i].addEventListener("click", function() {
            this.classList.toggle("active");
            var content = this.nextElementSibling;
            if (content.style.display === "block") {
              content.style.display = "none";
            } else {
              content.style.display = "block";
            }
          });
        }
     }
   },
   mounted() {
      try {
        this.attachCollapse();
      } catch (e) {
        return;
      }
   }
}
</script>
<style scoped>
.intro {
  padding-left : 7%;
}

.content-box { 
  /* width: 100%; */
  height: 80%;
  margin-left : 7%;
  margin-right : 6.5%;
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
  background: #fff;
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
  width: 100px;
  padding-left: 40px;
}

.row .cell:nth-child(2) {
  width: 470px;
}

.row .cell:nth-child(3) {
  width: 200px;
}

.row .cell:nth-child(4) {
  width: 190px;
}


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