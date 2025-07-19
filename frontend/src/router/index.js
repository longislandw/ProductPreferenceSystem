import { createRouter, createWebHistory } from 'vue-router'
import ProductList from '../components/ProductList.vue'
import LikeList from '../components/LikeList.vue'
import EditLikeItem from '../components/EditLikeItem.vue'
import UserProfile from '../components/UserProfile.vue'

const routes = [
    { path: '/:userid', name: 'ProductList', component: ProductList, props: true },
    { path: '/like-list/:userid', name: 'LikeList', component: LikeList, props: true },
    { path: '/edit-like/:userId/:productNo', name: 'EditLikeItem', component: EditLikeItem, props: true },
    { path: '/user', name: "UserProfile", component: UserProfile, props: true }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router