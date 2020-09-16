<template>
  <div class="login">
    <input class="loginInput" placeholder="email"/>
    <br>
    <input class="loginInput" type="password" placeholder="password"/>
    <br>
    <button class="loginBtn">Login</button>
  </div>
</template>

<script>
</script>


<style scoped>
.login{
  text-align: center;
}
.loginInput {
    background-color: #ffffffd8;
    font-size : 15px;
    padding : 15px 20px 15px 20px;
    margin: 5px;
    border-radius: 25px;
    border : none;
    outline: none;
    box-shadow: 1.5px 2px #292929;
    width: 200px;
}
.loginBtn {
    padding : 5px 15px 5px 15px;
    margin: 15px;
    border-radius: 25px;
    border : none;
    outline: none;
    background-color: #128db37e;
    color : white;
    box-shadow: 0.5px 1px #292929;
}
</style>
