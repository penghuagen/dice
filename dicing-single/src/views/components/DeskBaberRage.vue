<template>
    <vue-baberrage
      ref="baberrage"
      :lanesCount="8"
      :boxHeight= "stageHeight"
      :isShow= "barrageIsShow"
      :barrageList = "barrageList"
      :loop = "barrageLoop"
      :maxWordCount = "60"
      :messageHeight="messageHeight"
      :messageGap="messageGap"
      :throttleGap="throttleGap"
      :hoverLanePause = "hoverLanePause"
    >
    </vue-baberrage>
</template>
<script>
import { MESSAGE_TYPE } from 'vue-baberrage'
export default {
  name: 'DeskBaberRage',
  data () {
    return {
      msg: `Hello World!vue-baberrage`,
      position: 'normal',
      stageHeight: 200,
      boxWidth: 375,
      messageHeight: 32,
      messageGap: 5,
      throttleGap: 1000,
      placeStyle: 'normal',
      currentId: 0,
      barrageLoop: false,
      hoverLanePause: true, // 鼠标移动到上面的时候 会暂停泳道滚动
      barrageList: []
    }
  },
  props: {
    barrageIsShow: {
      type: Boolean,
      default: true
    }
  },
  methods: {
    addItem (data) {
      let userName = '系统'
      if (data.user && data.user.userName) {
        if (data.user.userName.length > 2) {
          userName = data.user.userName.substr(data.user.userName.length - 2)
        } else {
          userName = data.user.userName
        }
      }
      let item = {
        id: ++this.currentId,
        avatar: '',
        msg: userName + '：' + data.text,
        style: {
          fontSize: 13,
          color: data.color || '#FFF'
        },
        time: data.screenTime || 5,
        // time: 50,
        type: MESSAGE_TYPE.NORMAL
      }
      this.barrageList.push(item)
    }
  }
}
</script>

<style lang="less" scoped>
  /deep/ .baberrage-item{
    z-index: 9999;
    /*padding:0px;*/
    align-items: center;
    justify-content: center;
    .normal{
      padding: 4px 10px;
      color:unset;

      .baberrage-avatar{
        display: none;
      }

      .baberrage-msg{
        line-height: unset;
        padding-left:0px;
      }
    }
  }
</style>
