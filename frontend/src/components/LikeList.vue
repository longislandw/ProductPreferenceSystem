<template>
  <div>
    <h2>喜好金融商品清單</h2>
    <table>
      <thead>
        <tr>
          <th>產品名稱</th>
          <th>數量</th>
          <th>總金額</th>
          <th>總手續費</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in likeList" :key="item.productNo">
          <template v-if="editingItem === item.productNo">
            <!-- 編輯模式 -->
            <td>{{ item.productName }}</td>
            <td>
              <input type="number" v-model.number="editForm.orderQuantity" min="1" />
            </td>
            <td>{{ (editForm.orderQuantity * item.price * (1+item.feeRate)).toFixed(2) }}</td>
            <td>{{ (editForm.orderQuantity * item.price * item.feeRate).toFixed(2) }}</td>
            <td>{{ item.email }}</td>
            <td>
              <button @click="updateItem(item)">儲存</button>
              <button @click="cancelEdit">取消</button>
            </td>
          </template>
          <template v-else>
            <!-- 顯示模式 -->
            <td>{{ item.productName }}</td>
            <td>{{ item.orderQuantity }}</td>
            <td>{{ item.totalPrice }}</td>
            <td>{{ item.totalFee }}</td>
            <td>
              <button @click="deleteItem(item)">刪除</button>
              <button @click="startEdit(item)">修改</button>
            </td>
          </template>
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
    const editingItem = ref(null)
    const editForm = ref({
      totalAmount: 1
    })

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

    const startEdit = (item) => {
      editingItem.value = item.productNo
      editForm.value = {
        totalAmount: item.totalAmount
      }
    }

    const cancelEdit = () => {
      editingItem.value = null
    }

    const updateItem = async (item) => {
      const dto = {
        userId: userId.value,
        productNo: item.productNo,
        orderName: editForm.value.orderQuantity
      }
      await axios.put('http://localhost:8081/api/like-list', dto)
      editingItem.value = null
      loadList()
    }

    onMounted(loadList)

    // 如果路由參數變了（使用者換了），重新載入
    watch(() => route.params.userid, (newId) => {
      userId.value = newId
      loadList()
    })

    return {
      likeList,
      userId,
      editingItem,
      editForm,
      deleteItem,
      startEdit,
      cancelEdit,
      updateItem
    }
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
