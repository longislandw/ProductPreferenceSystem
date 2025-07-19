import { defineStore } from 'pinia'

export const useUserStore = defineStore('user', {
    state: () => ({
        userId: '',
        username: '',
        email: ''
    }),
    actions: {
        setUser({ userId, username, email }) {
            this.userId = userId
            this.username = username
            this.email = email
        },
        clearUser() {
            this.userId = ''
            this.username = ''
            this.email = ''
        }
    }
})