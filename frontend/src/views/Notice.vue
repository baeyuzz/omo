<template>
  <div class="notice">
    <Nav/>

    <div class="intro">
      <h2>OMO 서비스 공지사항 </h2>
    </div> 

    <!-- <div class="content-box">
      
    </div> -->
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
								{{ idx }}
							</div>
							<div class="cell" data-title="Age">
								<router-link to="/notice">{{ article }}</router-link>
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
        '개인정보 처리방침 개정안내',
        'OMO 운영정책 변경 안내'
      ]
    }
  },
   methods: {
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

@media screen and (max-width: 768px) {
  .table {
    display: block;
  }
}

.row {
  display: table-row;
  background: #fff;
}

.row.header {
  color: #ffffff;
  background: #6c7ae0;
}

@media screen and (max-width: 768px) {
  .row {
    display: block;
  }

  .row.header {
    padding: 0;
    height: 0px;
  }

  .row.header .cell {
    display: none;
    align-content: center;
  }

  .row .cell:before {
    font-family: Poppins-Bold;
    font-size: 12px;
    color: #808080;
    line-height: 1.2;
    text-transform: uppercase;
    font-weight: unset !important;

    margin-bottom: 13px;
    content: attr(data-title);
    min-width: 98px;
    display: block;
  }
}

.cell {
  display: table-cell;
  text-align: center;
}

@media screen and (max-width: 768px) {
  .cell {
    display: block;
  }
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

/* .row:hover {
  background-color: #ececff;
  background-color: rgba(0, 0, 0, 0.1);
  cursor: pointer;
}
.row.header :hover {
  background-color: #ececff;
  background-color: rgba(0, 0, 0, 0.1);
  background-color: #6c7ae0;
  cursor: default;
} */
@media (max-width: 768px) {
  .row {
    border-bottom: 1px solid #f2f2f2;
    padding-bottom: 18px;
    padding-top: 30px;
    padding-right: 15px;
    margin: 0;
  }
  
  .row .cell {
    border: none;
    padding-left: 30px;
    padding-top: 16px;
    padding-bottom: 16px;
  }
  .row .cell:nth-child(1) {
    padding-left: 30px;
  }
  
  .row .cell {
    font-family: Poppins-Regular;
    font-size: 18px;
    color: #555555;
    line-height: 1.2;
    font-weight: unset !important;
  }

  .table, .row, .cell {
    width: 100% !important;
  }
}
</style>