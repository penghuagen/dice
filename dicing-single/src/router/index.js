import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/boCake/index',
      name: 'BoCake',
      component: () => import('@/views/Index'),
      meta: {
        title: '博饼小游戏'
      }
    }
  ]
})
