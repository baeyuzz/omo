<template>
  <div id="app">
    <size-warning id="nope"/>
    <div id="yep">
      <head>
      <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
      </head>
    <router-view />
    </div>
    <div class="footer">
    <Footer/>
    </div>
  </div>
</template>

<script>
import sizeWarning from '@/components/size-warning.vue';
import Footer from '@/components/footer.vue';

export default {
  components: {
    "size-warning" : sizeWarning,
    Footer
  },
}
</script>

<style>
* {
  font-family: 'Noto Sans KR', sans-serif;
}
#app {

    font-family: 'Noto Sans KR', sans-serif;
  /* font-family: Avenir, Helvetica, Arial, sans-serif; */
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: white;
}

html {
  height: 100%;
  background-image: linear-gradient(
    -225deg,
    #473b7bdd 0%,
    #3584a7dd 51%,
    #30d2bedd 100%
  );
}

body {
  margin: 0;
}

.footer {
   position : fixed;
   bottom : 10px;
   text-align: center;
   width: 100%;
}


@media screen and (min-width: 800px) { 
  #nope { display:none } 
  #yep {display: inherit;}
}

@media screen and (max-width: 800px){ 
  #nope { display:inherit } 
  #app { 
    height: 100vh;
    display: flex; 
    align-items: center;
    justify-content: center;
    }
  #yep {display: none;}
}
@media screen and  (max-height : 750px) { 
  #nope { display:inherit } 
  #app { 
    height: 100vh;
    display: flex; 
    align-items: center;
    justify-content: center;
    }
  #yep {display: none;}
}

</style>
