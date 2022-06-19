import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/SignPage.vue'

const routes = [
  {
    path: '/',
    name: 'SignPage',
    component: Home
  },
  {
    path: '/main',
    name: 'MainPage',
    component: () => import('../views/MainPage.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
