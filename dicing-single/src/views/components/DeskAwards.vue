<template>
  <!--本桌成绩-->
  <div class="boCake-rink-wrap">
    <div class="boCake-rink-content">
      <div class="boCake-rink-list" v-for="(item, index) in deskAwards" :key="index">
        <div class="boCake-rink-index">{{index + 1}}</div>
        <div class="boCake-rink-right">
          <template v-if="item.awardId === 7">
            <div class="title">{{awardNameMap[item.awardId]}}<span>(总：{{item.counter}} 个， 可以抢)</span></div>
            <div class="person-award-wrap">
              <template v-if="deskAwardMap[item.awardId]">
                  <div class="person-zy-item"
                       v-for="(awrad, awradIndex) in deskAwardMap[item.awardId]"
                       :key="'avatar_' + item.awardId + '_'+ awradIndex" :class="'avatar_' + awradIndex">
                    <div class="avatar"><img :onerror="defaultImg" :src="awrad.avatar"/><i></i></div>
                    <div class="userName">{{awrad.userName}}</div>
                    <div class="dices">
                      <img v-for="(dice, diceIndex) in awrad.dices"
                           :key="diceIndex" :src="require('@/assets/img/boCake/detail/small_' + dice + '.png')">
                    </div>
                  </div>
              </template>
            </div>
          </template>
          <template v-else>
            <div class="title">{{awardNameMap[item.awardId]}}<span>(总：{{item.counter}} 个， 剩：{{item.remaining}}个)</span></div>
            <div class="person-award-wrap">
              <template v-if="deskAwardMap[item.awardId]">
                  <div class="person-award-item" v-for="(awrad, awradIndex) in deskAwardMap[item.awardId]"
                       :key="'avatar_' + item.awardId + '_'+ awradIndex">
                    {{awrad.userName}}<span>x{{awrad.count}}</span>
                  </div>
              </template>
            </div>
          </template>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'DeskAwards',
  data () {
    return {
      deskAwardMap: {}
    }
  },
  props: {
    // 默认头像
    defaultImg: {
      type: String,
      default: ''
    },
    awards: {
      type: Array,
      default: () => []
    },
    deskUsers: {
      type: Array,
      default: () => []
    },
    deskAwards: {
      type: Array,
      default: () => []
    },
    awardDicingLogs: {
      type: Array,
      default: () => []
    }
  },
  watch: {
    awardDicingLogs: {
      handler (newVal) {
        if (newVal.length > 0) {
          this.initAwardMap()
        }
      },
      deep: true,
      immediate: true
    },
    deskUsers: {
      handler (newVal) {
        if (newVal.length > 0) {
          this.initAwardMap()
        }
      },
      deep: true,
      immediate: true
    }
  },
  computed: {
    awardNameMap () {
      let map = {}
      this.awards.forEach((item) => {
        map[item.awardId] = item.awardName
      })
      return map
    }
  },
  methods: {
    initAwardMap () {
      this.deskAwardMap = {}
      this.deskAwards.forEach((desk) => {
        let awardArr = []
        if (desk.awardId === 7) {
          let maxAwardArr = this.awardDicingLogs.filter(log => log.awardId >= desk.awardId)
          if (maxAwardArr.length > 0) {
            maxAwardArr.forEach((award) => {
              let deskUser = this.deskUsers.find(user => user.userId === award.userId)
              if (deskUser) {
                awardArr.push({avatar: deskUser.avator, userName: deskUser.userName, count: 1, dices: award.dices})
              }
            })
          }
        } else {
          this.deskUsers.forEach((user) => {
            let userAwardList = this.awardDicingLogs.filter(log => log.awardId === desk.awardId &&
                  log.userId === user.userId)
            if (userAwardList.length > 0) {
              awardArr.push({avatar: user.avator, userName: user.userName, count: userAwardList.length})
            }
          })
        }

        if (awardArr.length > 0) {
          this.deskAwardMap[desk.awardId] = awardArr
        }
      })
    }
  }
}
</script>

<style lang="less" scoped>
@import '~@/assets/css/boCake';
  .boCake-rink-wrap{
    background-image: url(~@/assets/img/boCake/bj2.png);
    background-repeat: repeat;
    border-radius: 0px 0px 3px 3px;
    padding: 8px;
    .boCake-rink-content{
      min-height: 360px;
      /*overflow-x: hidden;*/
      /*overflow-y: auto;*/
      padding: 12px;
      border:1px solid #DCC6CA;
      border-radius: 3px;
      -webkit-overflow-scrolling:touch;
      .boCake-rink-list{
        display: flex;
        margin-bottom: 15px;
        .boCake-rink-index{
          display: block;
          width: 20px;
          height: 20px;
          text-align: center;
          line-height: 20px;
          background-size: 100% 100%;
          background-position: center;
          background-image: url(~@/assets/img/boCake/index.png);
          color:#fff;
          flex-grow: 0;
          flex-shrink: 0;
        }

        .boCake-rink-right{
          margin-left: 15px;
          flex-grow: 1;
          .title{
            font-size: 15px;
            color: #8D1B2F;
            span{
              font-size: 12px;
              margin-left: 12px;
              color: #8D1B2F;
            }
          }

          .person-award-wrap{
            margin-top:12px;
            display: flex;
            flex-wrap: wrap;
            font-size: 14px;
            color: #8D1B2F;
            line-height: 1.8;
            .person-award-item{
              width: 33.3%;
              overflow: hidden;
              white-space: nowrap;
              text-overflow: ellipsis;
              span{
                margin-left: 8px;
              }
            }

            .person-zy-item{
              width: 100%;
              display: flex;
              align-items: center;
              vertical-align: middle;
              margin-bottom:4px;
              .avatar{
                margin-right: 15px;
                img{
                  border:1px solid #C7AEAE;
                  border-radius: 18px;
                  display: inline-block;
                  vertical-align: middle;
                  width: 18px;
                  height: 18px;
                  margin: 0 2px;
                }
              }

              .userName{
                text-align: left;
                width: 30%;
                overflow: hidden;
                white-space: nowrap;
                text-overflow: ellipsis;
                flex-grow: 1;
                flex-shrink: 0;
                color:#C7AEAE;
              }

              .dices{
                flex-grow: 0;
                img{
                  display: inline-block;
                  vertical-align: middle;
                  width: 18px;
                  height: 18px;
                  margin: 0 2px;
                }
              }
            }

            .avatar_0{
              .avatar{
                position: relative;
                i{
                  position: absolute;
                  left:-9px;
                  top:-4px;
                  display: block;
                  width: 40px;
                  height: 13px;
                  background-size: 100% 100%;
                  background-position: center;
                  background-image: url(~@/assets/img/boCake/champion.png);
                }
              }
              .userName{
                color:#8D1B2F;
              }
            }
          }
        }
      }

      .boCake-rink-list:last-child{
        margin-bottom: 0px;
      }
    }
  }
</style>
