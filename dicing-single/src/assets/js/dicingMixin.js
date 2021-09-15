// 博饼
import boCakeWebsocket from '@/assets/js/boCakeWebsocket'
export default {
  data () {
    return {
      barrageIsShow: false, // 默认查看弹幕
      baberText: '',
      baberAvatar: require('@/assets/img/boCake/agentAvatar_small.png'),
      repliesArr: ['祝大家中秋快乐~', '这是秋天第一个大奖~~', '开始博饼前，一定要结束掉所有APP再重启手机，你懂的',
        '手气真好啊', '哎,就差一点点', '您真是太棒了', '我要爆发了', '优秀', '充电影响了我的中奖率',
        '啊啊啊，我中状元了~~', '竟然被抢了，想哭^^', '这波操作666', '据说关掉蓝牙能提高中奖率',
        '如果一直没博中，换个头像试试，记得微笑~', '去洗下手吧'],
      repliesShow: false
    }
  },
  methods: {
    toggleReplies () {
      this.repliesShow = !this.repliesShow
    },
    closeReplies () {
      this.repliesShow = false
    },
    repliesSelect (text) {
      this.baberText = text
    },
    // 发送弹幕
    sendBaberText () {
      if (this.baberText === '') {
        this.alert('请先输入您想要说的话~')
        return false
      }
      let success = boCakeWebsocket.websocketSend('BARRAGE_R', this.baberText)
      if (success) {
        this.baberText = ''
        // this.repliesShow = false
      } else {
        this.alert('不好意思，刚才服务器在忙，发送失败了^^')
      }
    }
  }
}
