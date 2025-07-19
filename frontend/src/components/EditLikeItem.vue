<!-- frontend/src/components/EditLikeItem.vue -->
<template>
  <div v-if="item">
    <h2>修改喜好商品</h2>
    <form @submit.prevent="submitEdit">
      <p>產品名稱：{{ item.productName }}</p>
      <p>原數量：{{ item.totalAmount }}</p>
      <label>新數量：
        <input type="number" v-model.number="item.totalAmount" />
      </label>
      <br />
      <button type="submit">修改</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  props: ['userId', 'productNo'],
  data() {
    return {
      item: null
    }
  },
  async mounted() {
    const res = await axios.get(`http://localhost:8081/api/like-list/user01`)
    this.item = res.data.find(x => x.productNo == this.productNo)
  },
  methods: {
    async submitEdit() {
      const dto = {
        userId: 'user01',
        productNo: this.productNo,
        orderName: this.item.totalAmount
      }
      await axios.put('http://localhost:8081/api/like-list', dto)
      alert('修改成功')
      this.$router.push('/like-list')
    }
  }
}
</script>