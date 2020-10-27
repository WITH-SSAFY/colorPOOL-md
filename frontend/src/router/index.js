import Vue from 'vue'
import VueRouter from 'vue-router'
// import Home from '../views/Home.vue'
import Landing from '../views/Landing.vue'
import Edit from '../views/Edit.vue'
import SelectPalettes from '../views/SelectPalettes'

Vue.use(VueRouter)

const routes = [
  // {
  //   path: '/',
  //   name: 'Home',
  //   component: Home
  // },
  {
    path: '/',
    name: 'Landing',
    component: Landing
  },
  {
    path: '/edit',
    name: 'Edit',
    component: Edit
  },
  {
    path: '/select',
    name: 'Select',
    component: SelectPalettes
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/themeTest',
    name: 'ThemeTest',
    component: () => import('../views/getThemeTest.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
