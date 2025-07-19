<!-- frontend/src/components/ProductList.vue -->
<template>
  <div>
    <h2>所有金融商品</h2>
    <table>
      <thead>
        <tr>
          <th>產品名稱</th>
          <th>價格</th>
          <th>手續費率</th>
          <th>帳號</th>
          <th>數量</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="p in products" :key="p.productName">
          <td>{{ p.productName }}</td>
          <td>{{ p.price }}</td>
          <td>{{ (p.feeRate * 100).toFixed(1) }}%</td>
          <td></td>
          <td><input type="number" v-model.number="p.amount" min="1" /></td>
          <td><button @click="addToLikeList(p)">加入喜好</button></td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data() {
    return {
      products: []
    }
  },
  methods: {
    async addToLikeList(p) {
      const dto = {
        userId: 'A1236456789', // 模擬登入帳號
        productNo: p.productNo,
        orderName: p.amount || 1
      }
      await axios.post('http://localhost:8081/api/like-list', dto)
      alert('加入成功')
    }
  },
  async mounted() {
    const res = await axios.get('http://localhost:8081/api/products')
    console.log('後端回傳資料：', res.data)
    this.products = res.data.map(p => ({ ...p, amount: 1 }))
  }
}
</script>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
}
th, td {
  padding: 8px;
  border: 1px solid #ddd;
}
</style>