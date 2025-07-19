<template>
  <div id="app">
    <header style="text-align: right; padding: 10px;">
      <span v-if="user.userId">您好，{{ user.userName  }} ({{ user.userId  }})</span>
    </header>
    <nav>
      <router-link :to="{ name: 'ProductList', params: { userid: user.userId } }">金融商品清單</router-link> |
      <router-link :to="{ name: 'LikeList', params: { userid: user.userId } }">喜好清單</router-link>
    </nav>
    
    <h1>金融商品系統</h1>

    <section v-if="user" style="margin-bottom: 20px;">
      <p><strong>使用者帳號：</strong>{{ user.account }}</p>
      <p><strong>Email：</strong>{{ user.email }}</p>
    </section>

    <router-view></router-view>
  </div>
</template>

<script setup>
import { ref, watch, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import axios from 'axios'

const route = useRoute()
const user = ref(null)
const userid = ref(route.params.userid)

async function fetchUserInfo(id) {
  try {
    const res = await axios.get(`http://localhost:8081/api/user/${id}`)
    user.value = res.data
  } catch (err) {
    console.error('取得使用者資料失敗:', err)
  }
}

onMounted(() => {
  if (userid.value) {
    fetchUserInfo(userid.value)
  }
})

// 如果使用者切換了網址 /userid2，重新撈資料
watch(() => route.params.userid, (newId) => {
  if (newId) {
    userid.value = newId
    fetchUserInfo(newId)
  }
})
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
