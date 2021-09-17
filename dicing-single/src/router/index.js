import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

export default new Router({
  mode: 'history',
  base: '/',
  routes: [
    {
      path: '/index',
      name: 'Dicing',
      component: () => import('@/views/Index'),
      meta: {
        title: '博饼小游戏'
      }
    }
  ]
})
