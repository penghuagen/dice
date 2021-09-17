<template>
  <div class="xms-flex-wrap boCake-wrap">
    <div class="xms-flex-middle boCake-middle-main" @click="closeReplies">
      <!--弹幕-->
       <desk-baber-rage ref="deskBaberRageRef" :barrageIsShow="!barrageIsShow"></desk-baber-rage>

        <div class="boCake-header">
          <!--桌号及轮次-->
          <div class="boCake-table">
            <div class="boCake-table-font">
                <div class="number">{{desk.deskNo}}</div> 桌
                <div class="number">{{desk.roundNo}}</div> 轮
            </div>
          </div>

          <van-image width="65%" :src="require('@/assets/img/boCake/boCake-font.png')" class="boCake-font"></van-image>

          <div class="boCake-rule" @click="scrollIntoView('boCake-rule', true)">查看规则</div>

          <!--博饼-->
          <dice :dicingLog="dicingLog" :pageInit="pageInit" :hasAward="hasAward"
                :dicingState="dicingState" @diceReset="diceReset" ref="diceRef"></dice>

          <!--博饼按钮-->
          <div class="boCake-button-wrap">
            <i class="rabbit"></i>
            <div class="boCake-button" :class="{'disabeld':dicingState > 1}" @click="dicingSocket(0)">{{dicingFont}}</div>
            <i class="logo"></i>
          </div>
          <div class="boCake-time">博饼开始时间：{{startTimeFont}}</div>
        </div>

      <!--内容区域-->
      <div class="boCake-main">
        <!--当前博饼信息-->
        <div class="boCake-current-wrap">
          <div class="person">当前人：<span>{{nextDicingUser.userName}}</span></div>
          <div class="person">下一个：<span>{{preparedDicingUser.userName}}</span></div>
          <div class="table-detail" @click="seeDeskUser">本桌人员</div>
        </div>

        <div class="boCake-avator-wrap">
          <div v-for="(item, index) in deskUsers" :key="index" class="boCake-avator-item"
               :class="{'boCake-avator-active' : nextDicingUser && nextDicingUser.userId === item.userId}">
            <img class="avatar" :onerror="defaultImg" :src="item.avator">
            <i></i>
          </div>
        </div>

        <!--我的博饼成绩-->
        <my-awards :awards="awards" :myAwardList="myAwardList"></my-awards>

        <!--博饼详情-->
        <div class="boCake-detail-wrap">
          <div class="boCake-tab">
            <div class="boCake-tab-item" :class="{'boCake-tab-item-active': tabIndex === 1}" @click="changeTabIndex(1)">本桌成绩</div>
            <div class="boCake-tab-item boCake-tab-center" :class="{'boCake-tab-item-active': tabIndex === 2}" @click="changeTabIndex(2)">本桌详情</div>
            <div class="boCake-tab-item" :class="{'boCake-tab-item-active': tabIndex === 3}" @click="changeTabIndex(3)">他桌成绩
              <span v-if="otherDeskNo > 0">({{otherDeskNo}})</span>
            </div>
          </div>
          <template v-if="tabIndex === 1">
          <!--成绩单-->
          <desk-awards :awards="awards" :deskAwards="deskAwards"
                       :awardDicingLogs="awardDicingLogs" :deskUsers="deskUsers"
                       :defaultImg="defaultImg"></desk-awards>
         </template>
          <template v-else-if="tabIndex === 2">
              <self-table-detail :deskNo="desk.deskNo" :deskUsers="deskUsers"
                                 :awards="awards" :defaultImg="defaultImg" ref="selfTableDetail"></self-table-detail>
          </template>
          <template v-else>
            <!--成绩单-->
            <more-desk-awards :awards="awards" :deskNo="otherDeskNo" :defaultImg="defaultImg"></more-desk-awards>
          </template>
        </div>

        <div id="boCake-rule">
          <dice-rule></dice-rule>
        </div>

      </div>
    </div>

    <!-- 弹幕发送-->
    <div class="xms-flex-headerOrBottom baber-bottom-wrap">
      <div class="baber-wrap">
        <div class="baber-input-wrap">
            <input type="text" class="baber-input" placeholder="发个祝福的弹幕见证下" v-model="baberText">
        </div>
        <div class="replies-icon" @click="toggleReplies"></div>
        <div class="baber-button" @click="sendBaberText">发送</div>
      </div>
      <div class="replies-wrap" v-if="repliesShow">
        <div class="replies-title">
          <div>热门弹幕</div>
          <van-checkbox v-model="barrageIsShow" checked-color="#44C85A">关闭弹幕</van-checkbox>
        </div>
        <div class="replies-text-wrap">
          <div class="text" v-for="(item,index) in repliesArr" @click="repliesSelect(item)"
               :key="'replies_' + index">{{item}}</div>
        </div>
      </div>
    </div>

    <!--本桌人员详情-->
    <desk-user :deskUsers="deskUsers" :defaultImg="defaultImg" ref="deskUser"></desk-user>

    <!-- 请选择桌号-->
    <van-popup v-model="showDeskPopup" position="bottom">
      <van-picker
        title="请选择桌号"
        show-toolbar
        value-key="deskName"
        :columns="deskColumns"
        @confirm="deskConfirm"
        @cancel="deskCancel"
      />
    </van-popup>

    <!--欢迎-->
    <van-popup v-model="welcome" @click-overlay="closeWelcome">
      <div class="welcome-wrap" @click="closeWelcome">
        <img :src="require('@/assets/img/boCake/start.png')" width="100%"/>
      </div>
    </van-popup>
  </div>
</template>

<script>
import websocket from '@/assets/js/websocket'
import dicingMixin from '@/assets/js/dicingMixin'
import Dice from './components/Dice'
import MyAwards from './components/MyAwards'
import DeskAwards from './components/DeskAwards'
import DiceRule from './components/DiceRule'
import DeskUser from './components/DeskUser'
import SelfTableDetail from './components/SelfTableDetail'
import MoreDeskAwards from './components/MoreDeskAwards'
import DeskBaberRage from './components/DeskBaberRage'
export default {
  mixins: [dicingMixin],
  name: 'Index',
  data () {
    return {
      defaultImg: 'this.src="' + require('@/assets/img/boCake/agentAvatar_small.png') + '"',
      url: {
        wsUri: 'ws://local.4846.com:8080/ws',
        init: '/init', // 数据初始化
        desks: '/desks' // 获取其他桌号
      },
      tabIndex: 1, // 切换索引值
      welcome: false, // 欢迎
      pageInit: false, // 界面初始化
      hasAward: false, // 当前博饼是否有奖
      countDownSec: 0, // 0:已开始,大于0未开始
      currentUserId: 0, // 当前用户ID
      desk: { // 本桌信息
        deskNo: 1,
        state: 1,
        roundNo: 1,
        noRemainingRoundNo: -1
      },
      dicingUser: { // 当前人
        userName: '-',
        userId: 0
      },
      nextDicingUser: { // 下一个
        userName: '-',
        userId: 0
      },
      preparedDicingUser: { // 预备人
        userName: '-',
        userId: 0
      },
      deskUsers: [], // 本桌人员
      deskAwards: [], // 本桌奖品
      awardDicingLogs: [], // 本桌获奖记录
      dicingLog: {
        dices: [4, 1, 4, 4, 1, 4],
        dicesOut: [1, 1, 1, 1, 1, 1],
        awardId: 1,
        valid: true
      }, // 当前博饼数据
      awards: [], // 奖励数据
      ready: false, // 准备好了
      dicingStateMap: {
        READYCOUNTDOWN: -1, // 准备好了
        TRY: 0, // 试博
        READY: 1, // 待博
        DOING: 2, // 已博,等结果
        WAITING: 3, // 等待
        FINISHED: 4, // 已结束
        COUNTDOWN: 5, // 倒计时,不能博
        TRYDOING: 6 // 试博中
      },
      dicSocketIng: false, // 是否正在博
      content: null, // 每次博饼返回的数据
      countdownTime: 60, // 博饼开始倒计时时间
      diceDelayTime: 10, // 博饼倒计时
      timer: null, // 定时器
      initSecondTimer: null, // 定时器
      lastResetTime: '', // 记录上次重置时间,防止短时间内多次重置
      deskColumns: [], // 桌号信息
      showDeskPopup: false, // 是否显示桌号下拉
      otherDeskNo: 0 // 其他桌号
    }
  },
  components: {
    Dice,
    MyAwards,
    DeskAwards,
    DiceRule,
    DeskUser,
    SelfTableDetail,
    MoreDeskAwards,
    DeskBaberRage
  },
  computed: {
    // 我的获奖记录
    myAwardList () {
      let awardDicingLogs = []
      if (this.awardDicingLogs) {
        awardDicingLogs = this.awardDicingLogs.filter(item => item.userId === this.currentUserId && item.awardId < 7)
        // 只有当自己博中的状元最大时，才加入
        if (this.awardDicingLogs[0] && this.awardDicingLogs[0].awardId >= 7 &&
            this.awardDicingLogs[0].userId === this.currentUserId) {
          awardDicingLogs.unshift(this.awardDicingLogs[0])
        }
      }
      return awardDicingLogs
    },
    // 博饼按钮状态
    dicingState () {
      if (this.desk.state === 0) {
        if (this.countDownSec > this.countdownTime) {
          if (this.dicSocketIng) {
            return this.dicingStateMap.TRYDOING
          } else {
            return this.dicingStateMap.TRY
          }
        } else {
          if (this.ready) {
            return this.dicingStateMap.COUNTDOWN
          } else {
            return this.dicingStateMap.READYCOUNTDOWN
          }
        }
      } else if (this.desk.state === 1) {
        if (this.nextDicingUser && this.nextDicingUser.userId === this.currentUserId) {
          if (this.dicSocketIng) {
            return this.dicingStateMap.DOING
          } else {
            return this.dicingStateMap.READY
          }
        } else {
          return this.dicingStateMap.WAITING
        }
      } else {
        return this.dicingStateMap.FINISHED
      }
    },
    dicingFont () { // 博饼按钮
      if (this.dicingState === this.dicingStateMap.TRY ||
          this.dicingState === this.dicingStateMap.TRYDOING) {
        return '试博一把'
      } else if (this.dicingState === this.dicingStateMap.COUNTDOWN) {
        return `活动倒计时(${this.countDownSec})`
      } else if (this.dicingState === this.dicingStateMap.READYCOUNTDOWN) {
        return `我准备好了(${this.countDownSec})`
      } else if (this.dicingState === this.dicingStateMap.FINISHED) {
        return '博饼已结束'
      } else if (this.dicingState === this.dicingStateMap.WAITING) {
        return '等待中'
      } else if (this.dicingState === this.dicingStateMap.DOING) {
        return '博饼中'
      } else {
        return `博一把(${this.diceDelayTime})`
      }
    },
    startTimeFont () { // 开始博饼时间
      let startFont = '-'
      if (this.desk && this.desk.startTime) {
        let startTime = this.desk.startTime
        startFont = `${startTime.substring(0, 4)}年${startTime.substring(4, 6)}月${startTime.substring(6, 8)}日
         ${startTime.substring(8, 10)}:${startTime.substring(10, 12)}`
      }
      return startFont
    }
  },
  methods: {
    init () {
      this.$http.post(this.url.init, {
      }).then(({status, data}) => {
        this.initData(data)
      })
    },
    initData (data) {
      if (data.awards) {
        this.awards = data.awards.reverse()
      }
      if (data.deskUsers) {
        this.deskUsers = data.deskUsers
      }
      if (data.user && data.user.userId) {
        this.currentUserId = data.user.userId
      }

      // 活动开始前60s,倒计时
      this.countDownSec = data.countDownSec

      if (this.countDownSec > this.countdownTime) {
        let timeout = (this.countDownSec - this.countdownTime) * 1000
        setTimeout(() => {
          this.countDownSec = 60
          this.initSecond()
        }, timeout)
      } else if (this.countDownSec < this.countdownTime) {
        this.initSecond()
      }

      // 中途入场,dicingUser有可能会有数据
      if (data.dicingUser) {
        data.preparedDicingUser = data.nextDicingUser
        data.nextDicingUser = data.dicingUser
      }

      // 更新数据
      this.updateData(data)
      this.updateDicingUser(data)
    },
    desks () {
      // 如果有数据,直接显示
      if (this.deskColumns && this.deskColumns.length > 0) {
        this.showDeskPopup = true
      } else {
        this.$http.post(this.url.desks, {}).then(({data}) => {
          if (data) {
            this.deskColumns = []
            data.forEach(item => {
              // 本桌与其他桌区分
              if (item.deskNo !== this.desk.deskNo) {
                item.deskName = '第' + item.deskNo + '桌'
              } else {
                item.deskName = '第' + item.deskNo + '桌(本桌)'
              }
              this.deskColumns.push(item)
            })
            this.showDeskPopup = true
          } else {
            this.alert('桌号数据获取异常,请刷新后再试')
          }
        })
      }
    },
    // 更新桌子信息
    updateData (data) {
      // 更新本桌数据
      if (data.deskAwards) {
        this.deskAwards = data.deskAwards.reverse()
      }

      this.hasAward = data.hasAward
      // 存贮初始化的值
      this.content = data
    },
    // 更新博饼人数据
    updateDicingUser (data) {
      if (data.desk) {
        this.desk = data.desk
      }

      if (data.awardDicingLogs) {
        this.awardDicingLogs = data.awardDicingLogs
      }

      if (data.dicingUser) {
        this.dicingUser = data.dicingUser
      }

      if (data.desk.state === 0 || data.desk.state === 1) {
        if (data.nextDicingUser) {
          this.nextDicingUser = data.nextDicingUser
        }
        if (data.preparedDicingUser) {
          this.preparedDicingUser = data.preparedDicingUser
        }
      } else {
        this.nextDicingUser = {
          userName: '-',
          userId: 0
        }

        this.preparedDicingUser = {
          userName: '-',
          userId: 0
        }
      }

      this.dicSocketIng = false

      // 只有博饼中才需要
      if (this.desk.state === 1) {
        // 实时更新日志
        if (this.$refs.selfTableDetail) {
          let dicingLog = {...data.dicingLog}
          this.$refs.selfTableDetail.unshiftList(dicingLog)
        }

        // 轮到自己时，启动倒计时
        if (this.nextDicingUser.userId === this.currentUserId) {
          this.diceDelayTime = 10
          this.delayTime()
        }
      }
    },
    // 博饼界面重置
    diceReset (index) {
      // 防止执行多次
      let now = new Date().getTime()
      console.log(`时间间隔:${now - this.lastResetTime},index:${index}`)
      if ((this.lastResetTime && now - this.lastResetTime < 300) ||
          index === 12 || index === 11) {
        return false
      }

      this.lastResetTime = now

      // 延迟一秒,重置页面
      setTimeout(() => {
        this.dicingLog.dices = []
        if (this.content) {
          this.updateDicingUser(this.content)
        }
      }, 1000)
    },
    dicingSocket (systemDicing) {
      console.log(`dicingSocket,dicingState:${this.dicingState}`)
      // 博饼时,清掉定时器,防止手动博时,定时器没有停止
      if (this.timer) {
        clearTimeout(this.timer)
      }
      if (this.dicingState === this.dicingStateMap.TRYDOING) {
        this.alert('正在试博中,请稍等~')
      } else if (this.dicingState === this.dicingStateMap.DOING) {
        this.alert('正在博饼中,请稍等~')
      } else if (this.dicingState === this.dicingStateMap.WAITING) {
        this.alert('还没有轮到您呢，休息下吧~')
      } else if (this.dicingState === this.dicingStateMap.FINISHED) {
        this.alert('博饼活动已结束~')
      } else if (this.dicingState === this.dicingStateMap.COUNTDOWN) {
        this.alert('活动倒计时中,准备好了吗~')
      } else if (this.dicingState === this.dicingStateMap.READYCOUNTDOWN) {
        this.ready = true
        this.alert('愿您有个好手气~')
      } else {
        // 刚进入界面，点击博饼调用播放视频，防止首次进入界面，音频没加载完成
        if (!this.pageInit) {
          this.$refs.diceRef.playVoice(0)
        }
        this.dicSocketIng = true
        let contentSocket = '{\\"systemDicing\\":' + systemDicing + '}'
        let success = websocket.websocketSend('DICING', contentSocket)
        if (!success) {
          this.dicSocketIng = false
          this.alert('正在连接中...')
        }
      }
    },
    handlerOnMessage (data) {
      if (data.msgType === 'START') {
        // 防止试博有奖，播报有误
        this.dicingLog.awardId = 1

        // 播放开始博饼声音
        this.$refs.diceRef.playVoice(8)
        setTimeout(() => {
          this.desk.state = 1
        }, 3500)
        // 初始化
      } else if (data.msgType === 'INIT') {
        let content = JSON.parse(data.content)
        this.initData(content)
      } else if (data.msgType === 'ERROR') {
        let content = JSON.parse(data.content)
        // 博饼异常,重新再试
        if (content.code === 2002) {
          this.alert('系统异常,请重博一次')
          this.dicSocketIng = false
          // 博饼结束,修改桌子信息
        } else if (content.code === 1002) {
          this.alert(content.msg)
          this.desk.state = 2
        } else {
          this.alert(content.msg)
        }
      } else if (data.msgType === 'DICING_RESULT') {
        // 博饼结果
        let content = JSON.parse(data.content)
        this.content = content
        this.pageInit = true
        this.dicingLog.dices = []
        this.$nextTick(() => {
          setTimeout(() => {
            if (content.dicingLog) {
              this.dicingLog = {...content.dicingLog}
            }
            this.updateData(content)
          }, 100)
        })
      } else if (data.msgType === 'BARRAGE_B') {
        let content = JSON.parse(data.content)
        if (this.$refs.deskBaberRageRef) {
          this.$refs.deskBaberRageRef.addItem(content)
        }
      }
    },
    // 博饼前倒计时
    initSecond () {
      if (this.initSecondTimer) {
        clearTimeout(this.initSecondTimer)
      }
      if (this.countDownSec > 0) {
        this.countDownSec--
        this.initSecondTimer = setTimeout(() => {
          this.initSecond()
        }, 1000)
      }
    },
    // 博饼中倒计时
    delayTime () {
      if (this.timer) {
        clearTimeout(this.timer)
      }
      if (this.diceDelayTime > 0) {
        this.diceDelayTime--
        this.timer = setTimeout(() => {
          this.delayTime()
        }, 1000)
      } else {
        // 自动博
        this.dicingSocket(1)
      }
    },
    // 查看本桌人员
    seeDeskUser () {
      this.$refs.deskUser.show()
    },
    changeTabIndex (index) {
      if (index === 3) {
        this.desks()
      } else {
        this.tabIndex = index
      }
    },
    deskConfirm (value) {
      this.showDeskPopup = false
      if (value) {
        this.tabIndex = 3
        this.otherDeskNo = value.deskNo
      } else {
        this.alert('请先选择一个桌号')
      }
    },
    deskCancel () {
      this.showDeskPopup = false
    },
    closeWelcome () {
      this.welcome = false
      this.$refs.diceRef.playVoice(0)
      websocket.createSocket(this.url.wsUri, (data) => {
        this.handlerOnMessage(data)
      })
    },
    // 滚动到指定位置的方法,支持滚动到顶部或底部
    scrollIntoView (id, isTop) {
      setTimeout(function () {
        if (document.getElementById(id)) {
          document.getElementById(id).scrollIntoView(isTop)
        }
      }, 100)
    }
  },
  mounted () {
    // 欢迎
    this.welcome = true
  }
}
</script>

<style lang="less" scoped>
  @import '~@/assets/css/boCake';
</style>
