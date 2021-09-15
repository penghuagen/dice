import { Toast } from 'vant'

export default {
  toast (options) {
    const param = {
      type: 'success',
      message: '保存成功',
      duration: 1000,
      forbidClick: true
    }
    Object.assign(param, options)
    return Toast(param)
  }
}
