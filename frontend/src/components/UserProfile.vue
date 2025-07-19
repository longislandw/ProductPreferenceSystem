<!-- UserProfile.vue -->
<template>
  <div>
    <h2>登入使用者</h2>
    <input v-model="account" placeholder="帳號" />
    <input v-model="password" placeholder="密碼" type="password" />
    <button @click="login">登入</button>
    <p v-if="errorMessage" style="color: red">{{ errorMessage }}</p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      account: '',
      password: '',
      errorMessage: ''
    }
  },
  methods: {
    async login() {
      try {
        const response = await fetch('http://localhost:8081/api/auth/login', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
            account: this.account,
            password: this.password
          })
        });

        if (!response.ok) {
          const errorText = await response.text();
          throw new Error(errorText || '登入失敗');
        }

        const data = await response.json();
        const token = data.token;

        // 儲存 token 到 localStorage
        localStorage.setItem('jwt', token);

        // 儲存使用者資訊
        this.$emit('update-user', {
          account: this.account,
          token: token
        });

        // 導向首頁
        this.$router.push('/');
      } catch (err) {
        console.error('登入錯誤:', err);
        this.errorMessage = '登入失敗：帳號或密碼錯誤';
      }
    }
  }
}
</script>
