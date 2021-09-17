import Vue from 'vue'
import App from './App'
import router from '@/router/index'

import '@/assets/css/common.less'
// 引入vant样式
import 'vant/lib/index.css'
// 全局样式
import 'babel-polyfill'
// px转rem
import 'lib-flexible/flexible'
// 接口请求
import http from '@/assets/js/axios'
// 全局方法
import common from '@/assets/js/common'
import { vueBaberrage } from 'vue-baberrage'

// vant按需引入
import {ImagePreview, List, Image, Picker, Popup, Toast} from 'vant'
Vue.use(vueBaberrage)
Vue.prototype.$http = http

Vue.config.productionTip = false
Vue.use(common)

Vue.use(ImagePreview).use(List).use(Image).use(Picker).use(Popup).use(Toast)

// eslint-disable-next-line no-new
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
