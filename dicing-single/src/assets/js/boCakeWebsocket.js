/**
 * 博饼socket
 */
import { Toast } from 'vant'
let boCakeWebsocket = {
  cache: {
    wsUri: null,
    webSock: null,
    autoCheckTime: 30, // 连接验证定时器时间差/秒
    checkTime: 30, // 心跳间隔时间
    timerId: null // 定时器标识
  },
  config: {
    reStatus: 1,
    socketState: 3,
    stateType: {
      CONNECTING: 0,
      OPEN: 1,
      CLOSING: 2,
      CLOSED: 3
    }
  },
  createSocket (wsUri, callback) {
    let _this = this
    if (wsUri) {
      _this.cache.wsUri = wsUri
    }
    _this.config.socketState = _this.config.stateType.CONNECTING
    _this.cache.webSock = new WebSocket(_this.cache.wsUri)

    // 接收回调
    _this.cache.webSock.onmessage = function (e) {
      if (e.data && e.data !== 'Server:connected OK!') {
        _this.cache.autoCheckTime = _this.cache.checkTime
        typeof callback === 'function' && callback(JSON.parse(e.data))
      }
    }

    // 连接建立之后执行send方法发送数据
    _this.cache.webSock.onopen = function () {
      _this.config.socketState = _this.config.stateType.OPEN
      _this.autoCheckTimeFunc()
    }

    // 连接建立失败重连
    _this.cache.webSock.onerror = function () {
      _this.config.socketState = _this.config.stateType.CLOSING
      _this.reConnectSocket(callback)
    }

    // 关闭
    _this.cache.webSock.onclose = function () {
      _this.config.socketState = _this.config.stateType.CLOSED
      setTimeout(function () {
        _this.reConnectSocket(callback)
      }, 3000)
    }
  },

  // 发送消息
  websocketSend (msgType, content) { // 数据发送
    let sendParam =
      '{"msgType":"' + msgType + '","content":"' + content + '"}'
    if (this.config.socketState === this.config.stateType.OPEN) {
      this.cache.autoCheckTime = this.cache.checkTime
      this.cache.webSock.send(sendParam)
      return true
    }
    return false
  },

  // 重新连接
  reConnectSocket (callback) {
    if (this.config.socketState === this.config.stateType.CLOSED &&
      this.config.reStatus === 1) {
      Toast({
        message: '网络不稳定，正在重连中~',
        className: 'toast',
        duration: 2000
      })
      this.createSocket(this.cache.wsUri, callback)
    }
  },
  // 自动重连
  autoCheckTimeFunc () {
    if (this.cache.timerId) {
      clearInterval(this.cache.timerId)
      this.cache.autoCheckTime = this.cache.checkTime
    }
    this.cache.timerId = setInterval(() => {
      this.cache.autoCheckTime--
      if (this.cache.autoCheckTime === 0) {
        // 保持心跳
        this.websocketSend('HEARTBEAT', '')
        this.cache.autoCheckTime = this.cache.checkTime
      }
    }, 1000)
  },

  destroyedSocket () {
    let _this = this
    _this.config.reStatus = 0
    if (_this.cache.webSock) {
      _this.cache.webSock.close()
    }
  }

}

export default boCakeWebsocket
