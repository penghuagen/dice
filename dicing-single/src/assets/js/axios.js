import axios from 'axios'
axios.defaults.baseURL = '//local.4846.com:8080'

axios.defaults.timeout = 20000
axios.defaults.withCredentials = true
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'

export default {
  post (url, data) {
    return axios.post(url, data)
  },
  get (url, params) {
    return axios.get(url, {params})
  }
}
