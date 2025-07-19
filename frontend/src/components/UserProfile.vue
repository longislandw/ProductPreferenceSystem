<!-- UserProfile.vue -->
<template>
  <div>
    <h2>登入使用者</h2>
    <input v-model="userId" placeholder="使用者 ID" />
    <input v-model="userName" placeholder="使用者名稱" />
    <button @click="login">登入</button>
  </div>
</template>

<script>
export default {
  props: ['user'],
  data() {
    return {
      userId: '',
      userName: ''
    }
  },
  methods: {
    login() {
      this.$emit('update-user', {
        id: this.userId,
        name: this.userName
      })
      this.$router.push('/') // 回首頁
    }
  }
}
</script>