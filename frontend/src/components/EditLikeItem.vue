<!-- frontend/src/components/EditLike.vue -->
<template>
  <div>
    <h2>修改喜好商品</h2>
    <div v-if="likeItem">
      <p><strong>產品名稱：</strong>{{ likeItem.productName }}</p>
      <p>
        <label>
          數量：
          <input type="number" v-model.number="likeItem.totalAmount" min="1" />
        </label>
      </p>
      <button @click="updateItem">儲存修改</button>
    </div>
    <p v-else>載入中...</p>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import axios from 'axios'

export default {
  setup() {
    const route = useRoute()
    const router = useRouter()
    const userid = route.params.userid
    const productNo = route.params.productNo
    const likeItem = ref(null)

    const loadLikeItem = async () => {
      const res = await axios.get(`http://localhost:8081/api/like-list/${userid}`)
      likeItem.value = res.data.find(item => item.productNo === productNo)
    }

    const updateItem = async () => {
      const dto = {
        userId: userid,
        productNo: parseInt(productNo), // 因為 productNo 是 Long
        totalAmount: likeItem.value.totalAmount
      }
      try {
        await axios.put('http://localhost:8081/api/like-list', dto)
        alert('更新成功')
        router.push(`/${userid}/like-list`)
      } catch (err) {
        console.error('更新失敗', err)
        alert('更新失敗')
      }
    }

    onMounted(loadLikeItem)

    return {
      likeItem,
      updateItem
    }
  }
}
</script>