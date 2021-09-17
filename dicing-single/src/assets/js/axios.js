import axios from 'axios'
import qs from 'qs'
let baseUrls = {}
axios.defaults.baseURL = 'http://local.4846.com:8080'
axios.defaults.withCredentials = true
axios.defaults.crossDomain = true

axios.defaults.timeout = 20000
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'
axios.interceptors.request.use(config => {
  if (config.data && config.data.constructor.name === 'FormData') return config

  if (config.method === 'post' && config.data) {
    config.data = qs.parse(config.data)
    if (config.data.apiHostKey) {
      config.baseURL = baseUrls[config.data.apiHostKey]
      delete config.data.apiHostKey
    }
    config.data = qs.stringify(config.data, { indices: false })
  } else if (config.method === 'get' && config.params) {
    config.params.appLogin = 1
    if (config.params.apiHostKey) {
      config.baseURL = baseUrls[config.params.apiHostKey]
      delete config.params.apiHostKey
    }
  }
  return config
}, error => {
  return Promise.error(error)
})

axios.interceptors.response.use(response => {
  return response
}, error => {
  return Promise.reject(error.response)
})

export default {
  post (url, data) {
    return axios.post(url, data)
  },
  get (url, params) {
    return axios.get(url, {params})
  }
}
