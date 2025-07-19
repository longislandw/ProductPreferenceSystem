<template>
  <div id="app">
    <nav>
      <router-link to="/">金融商品清單</router-link> |
      <router-link to="/user">個人資料</router-link>
    </nav>
    <header style="text-align: right; padding: 10px;">
      <span v-if="user.id">您好，{{ user.name }} ({{ user.id }})</span>
    </header>
    <h1>金融商品系統</h1>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
      user: {
        id: 'A1236456789',
        name: '王O明'
      }
    }
  },
  methods: {
    updateUser(userInfo) {
      this.user = userInfo
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
