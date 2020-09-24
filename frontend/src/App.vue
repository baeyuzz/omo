<template>
  <div id="app">
    <head>
      <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
    </head>
    <router-view />
  </div>
</template>

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
</style>
