<template>
  <div class="self-table-detail">
    <div class="self-table-main">
  <van-list
    v-model="config.loading"
    :finished="config.finished"
    :finished-text="config.finishedText"
    @load="onLoad"
  >
    <div class="self-item-list">
      <div class="roundNo">轮次</div>
      <div class="userName">姓名</div>
      <div class="score">成绩</div>
    </div>
    <template v-if="list && list.length >0">
      <div class="self-item-list" v-for="(item, index) in list" :key="index">
        <div class="roundNo">{{item.roundNo}}</div>
        <div class="userName text-left">
          <img class="avatar" :onerror="defaultImg" :src="deskUserMap[item.userId].avator">
          {{deskUserMap[item.userId].userName}}
        </div>
        <div class="score text-right">
          <span :class="{'noValid' : !item.valid}">{{awardsMap[item.awardId].awardName}}</span>
          <span v-for="(dice, index) in item.dices" :key="index">
            <template v-if="item.dicesOut[index] === 0">
              <img :src="require('@/assets/img/boCake/detail/small_0.png')">
            </template>
            <template v-else>
              <img :src="require('@/assets/img/boCake/detail/small_' + dice + '.png')">
            </template>
          </span>
        </div>
      </div>
    </template>
    <template v-else>
      <no-data msg="暂无数据" v-if="!config.loading"></no-data>
    </template>
  </van-list>
    </div>
  </div>
</template>
<script>
import NoData from './NoData'
export default {
  name: 'SelfTableDetail',
  data () {
    return {
      url: {
        dicingLogs: '/dicingLogs'
      },
      startDicingLogId: '',
      config: { // 翻页配置
        pageIndex: 1,
        loading: false,
        finished: false,
        pageSize: 20,
        offset: 50,
        finishedText: '没有更多了'
      },
      list: []
    }
  },
  props: {
    // 当前桌号
    deskNo: {
      type: Number,
      default: 0
    },
    // 默认头像
    defaultImg: {
      type: String,
      default: ''
    },
    // 奖品信息
    awards: {
      type: Array,
      default: () => []
    },
    // 用户信息
    deskUsers: {
      type: Array,
      default: () => []
    }
  },
  components: {
    NoData
  },
  computed: {
    deskUserMap () {
      let map = {}
      if (this.deskUsers) {
        this.deskUsers.forEach(item => {
          map[item.userId] = item
        })
      }
      return map
    },
    awardsMap () {
      let map = {}
      if (this.awards) {
        this.awards.forEach(item => {
          map[item.awardId] = item
        })
      }
      return map
    }
  },
  methods: {
    unshiftList (item) {
      if (this.list.length > 0) {
        // 防止数据重复的情况
        if (this.list[0].userId !== item.userId || this.list[0].roundNo !== item.roundNo) {
          this.list.unshift(item)
        }
      } else {
        this.list.unshift(item)
      }
    },
    onLoad () {
      this.$http.post(this.url.dicingLogs, {
        pageSize: this.config.pageSize,
        startDicingLogId: this.startDicingLogId,
        pageNo: this.config.pageIndex,
        deskNo: this.deskNo
      }).then(({data}) => {
        this.dataResultData(data)
      })
    },
    dataResultData (data) {
      if (this.config.pageIndex === 1) {
        this.list = []
        this.config.finishedText = ''
      } else {
        this.config.finishedText = this.GLOBAL.comvar.nomoreGB
      }

      this.list = this.list.concat(data)

      if (data) {
        if (data.length > 0) {
          this.startDicingLogId = this.list[this.list.length - 1].dicingLogId
        }

        if (data.length < this.config.pageSize) {
          this.config.finished = true
        }
      }

      this.config.loading = false
      this.config.pageIndex++
    }
  }
}
</script>

<style lang="less" scoped>
.self-table-detail{
  background-image: url(~@/assets/img/boCake/bj2.png);
  background-repeat: repeat;
  border-radius: 0px 0px 3px 3px;
  padding: 8px;
  .self-table-main{
    padding: 12px;
    border:1px solid #DCC6CA;
    height: 360px;
    overflow: auto;
    border-radius: 3px;
    .self-item-list{
      display: flex;
      align-items: center;
      line-height: 2;
      text-align: center;
      .text-left{
        text-align: left;
      }
      .text-right{
        text-align: right;
        .noValid{
          color:#999;
        }
      }
      .roundNo{
        width: 15%;
        flex-grow: 0;
      }
      .userName{
        width: 30%;
        vertical-align: middle;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
        .avatar{
          border:1px solid #C7AEAE;
          display: inline-block;
          vertical-align: middle;
          width: 18px;
          height: 18px;
          border-radius: 18px;
          margin-right: 8px;
        }
      }
      .score{
        width: 55%;
        img{
          display: inline-block;
          vertical-align: middle;
          width: 18px;
          height: 18px;
          margin: 0 2px;
        }
      }
    }
  }

}
</style>
