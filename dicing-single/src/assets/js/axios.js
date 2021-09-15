import axios from 'axios'
import qs from 'qs'

axios.defaults.baseURL = 'http://dicing.xms.4846.com'

axios.defaults.timeout = 20000
axios.defaults.withCredentials = true
axios.defaults.crossDomain = true

axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'
axios.interceptors.request.use(config => {
  if (config.data && config.data.constructor.name === 'FormData') return config

  if (config.method === 'post' && config.data) {
    config.data = qs.parse(config.data)

    config.data = qs.stringify(config.data, { indices: false })
  } else if (config.method === 'get' && config.params) {

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
