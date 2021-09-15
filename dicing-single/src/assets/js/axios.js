
import axios from 'axios'
// import qs from 'qs'
import { Toast } from 'vant'
import Global from '@/assets/js/global'

let loadingInstance = null
let baseUrls = {}
let apiHostKey = 'is_url'

// 默认域名对象
if (process.env.NODE_ENV === 'production') {
  baseUrls = Global.env.prodUrlGB
} else if (process.env.NODE_ENV === 'development') { // 开发环境
  baseUrls = Global.env.devUrlGB
} else if (process.env.NODE_ENV === 'test') { // 测试环境
  baseUrls = Global.env.testUrlGB
}
// 手动控制环境
baseUrls = Global.env[Global.env.type + 'UrlGB']

// 当访问正式或测试环境时,强制调用对应环境的接口
if (window.location.href.indexOf('xms.4846.com') > -1) {
  baseUrls = Global.env.prodUrlGB
  Global.env.type = 'prod'
} else if (window.location.href.indexOf('xms3.4846.com') > -1) {
  baseUrls = Global.env.testUrlGB
  Global.env.type = 'test'
}

axios.defaults.baseURL = baseUrls[apiHostKey]

axios.defaults.timeout = 20000
axios.defaults.withCredentials = true
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'
axios.interceptors.request.use(config => {
  let showLoading = null
  if (config.method === 'post' && config.data && config.data !== '') {
    let params = config.data
    if (params.apiHostKey) {
      config.baseURL = baseUrls[params.apiHostKey]
      delete params.apiHostKey
    }
    params.reqFrom = 'mobile'
    if (params.showLoading) {
      showLoading = 1
      delete params.showLoading
    }
    let array = []
    for (let key in params) {
      array.push(key + '=' + params[key])
    }
    config.data = array.join('&')
  } else if (config.method === 'get' && config.params) {
    config.params.reqFrom = 'mobile'
    if (config.params.apiHostKey) {
      config.baseURL = baseUrls[config.params.apiHostKey]
      delete config.params.apiHostKey
    }
    if (config.params.showLoading) {
      showLoading = 1
      delete config.params.showLoading
    }
  }
  if (showLoading) {
    loadingInstance = Toast.loading({
      message: '加载中...',
      className: 'toast-loading',
      forbidClick: true,
      duration: 0
    })
  }
  return config
}, error => {
  return Promise.error(error)
})

axios.interceptors.response.use(response => {
  if (loadingInstance) loadingInstance.clear()
  return response
}, error => {
  let message = ''
  let duration = 2000
  if (loadingInstance) loadingInstance.clear()
  if (error.response && error.response.status) {
    switch (error.response.status) {
      case 404:
        message = '请求不存在'
        break
      case 400:
        message = error.response.data.message || error.response.data.msg
        break
      default:
        message = '系统错误'
    }
  } else {
    // message = error.toString()
    message = '网络连接错误'
    duration = 5000
  }
  if (message) {
    Toast({
      message,
      duration,
      className: 'toast',
      forbidClick: true
    })
  }

  return Promise.reject(error)
})

export default {
  post (url, data) {
    return axios.post(url, data)
  },
  get (url, params) {
    return axios.get(url, {params})
  }
}
