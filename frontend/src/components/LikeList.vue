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
      <tbody>
        <tr v-for="item in likeList" :key="item.productNo">
          <td>{{ item.productName }}</td>
          <td>{{ item.account }}</td>
          <td>{{ item.totalAmount }}</td>
          <td>{{ item.totalFee }}</td>
          <td>{{ item.email }}</td>
          <td>
            <button @click="deleteItem(item)">刪除</button>
            <router-link :to="`/edit-like/${userId}/${item.productNo}`">修改</router-link>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios'
import { onMounted, ref, watch } from 'vue'
import { useRoute } from 'vue-router'

export default {
  setup() {
    const route = useRoute()
    const userId = ref(route.params.userid)
    const likeList = ref([])

    const loadList = async () => {
      try {
        const res = await axios.get(`http://localhost:8081/api/like-list/${userId.value}`)
        likeList.value = res.data
      } catch (err) {
        console.error('取得清單失敗', err)
      }
    }

    const deleteItem = async (item) => {
      await axios.delete(`http://localhost:8081/api/like-list?userId=${userId.value}&productNo=${item.productNo}`)
      loadList()
    }

    // 初始化
    onMounted(loadList)

    // 如果路由參數變了（使用者換了），重新載入
    watch(() => route.params.userid, (newId) => {
      userId.value = newId
      loadList()
    })

    return {
      likeList,
      userId,
      deleteItem
    }
  }
}
</script>