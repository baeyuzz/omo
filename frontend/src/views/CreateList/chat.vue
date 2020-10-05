<template>
  <div class="default">
    What chat room would you like to enter?<br/>
    <input v-model="roomName" @keyup.enter="enter" type="text" size="40"  id="room-name-input"><br/>
    <input @click="enter" type="button" value="Enter"/>
  </div>
</template>

<script>
export default {
  data() {
    return {
      roomName: null
    }
  },
  mounted: {

  },
  methods: {
    enter() {
      this.$router.push('/chat/' + this.roomName)
    }
  }
}
</script>

<style>
 .default {
    position : fixed;
    top : 42%;
    width: 30%;
    text-align: left;
    padding: 0 35% 0 35%;
    /* display: flex; */
    /* z-index: 2;
    justify-items: center;
    justify-content: space-between; */
  }
</style>