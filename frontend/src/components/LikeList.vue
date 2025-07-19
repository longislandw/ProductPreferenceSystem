<!-- frontend/src/components/LikeList.vue -->
<template>
  <div>
    <h2>喜好金融商品清單</h2>
    <table>
      <thead>
        <tr>
          <th>產品名稱</th>
          <th>帳號</th>
          <th>數量</th>
          <th>總金額</th>
          <th>總手續費</th>
          <th>電子信箱</th>
          <th>操作</th>
        </tr>
      </thead>
     
      <tr v-for="item in likeList" :key="item.productName">
        <td>{{ item.productName }}</td>
        <td>{{ item.account }}</td>
        <td>{{ item.totalAmount }}</td>
        <td>{{ item.totalFee }}</td>
        <td>{{ item.email }}</td>
        <td>
          <button @click="deleteItem(item)">刪除</button>
          <router-link :to="`/edit-like/user01/${item.productNo}`">修改</router-link>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data() {
    return {
      likeList: []
    }
  },
  methods: {
    async deleteItem(item) {
      await axios.delete(`http://localhost:8081/api/like-list?userId=user01&productNo=${item.productNo}`)
      this.loadList()
    },
    async loadList() {
      const res = await axios.get('http://localhost:8081/api/like-list/user01')
      this.likeList = res.data
    }
  },
  mounted() {
    this.loadList()
  }
}
</script>