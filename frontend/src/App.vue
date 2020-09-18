<template>
  <div id="app">
    <router-view />
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
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
