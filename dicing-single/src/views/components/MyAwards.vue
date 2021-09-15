<template>
  <!--我的博饼成绩-->
  <div class="boCake-my-wrap">
    <div class="boCake-my-title"><i></i></div>
    <div class="boCake-my-content">
      <template v-if="myAwardArr.length === 0">
          <div class="no-award">暂无获奖信息</div>
      </template>
      <template v-else>
          <div class="my-item" v-for="(item, index) in myAwardArr" :key="index">
            {{item.name}}
            <span>x{{item.count}}</span>
          </div>
      </template>
    </div>
  </div>
</template>
<script>
export default {
  name: 'MyAwards',
  data () {
    return {
      myAwardArr: []
    }
  },
  props: {
    awards: {
      type: Array,
      default: () => []
    },
    myAwardList: {
      type: Array,
      default: () => []
    }
  },
  watch: {
    myAwardList: {
      handler (newVal) {
        if (newVal.length > 0) {
          this.myAwardMap()
        }
      },
      deep: true,
      immediate: true
    }
  },

  methods: {
    myAwardMap () {
      this.myAwardArr = []
      this.awards.forEach((item) => {
        let count = 0
        this.myAwardList.forEach((myItem) => {
          if (myItem.awardId === item.awardId) {
            count++
          }
        })
        if (count > 0) {
          this.myAwardArr.push({name: item.awardName, count: count, awardId: item.awardId})
        }
      })
    }
  }
}
</script>

<style lang="less" scoped>
@import '~@/assets/css/boCake';
//我的博饼成绩
.boCake-my-wrap{
  background-image: url(~@/assets/img/boCake/bj2.png);
  background-repeat: repeat;
  border-radius: 3px;
  padding: 8px;
  position: relative;
  .boCake-my-title{
    position: absolute;
    top:-8px;
    left:0px;
    width: 100%;
    i{
      display: block;
      width: 184px;
      height: 37px;
      margin: 0 auto;
      background-image: url(~@/assets/img/boCake/myAward.png);
      background-repeat: no-repeat;
      background-size: 100% 100%;
    }
  }

  .boCake-my-content{
    line-height: 2;
    padding:30px 12px 12px;
    display: flex;
    min-height: 100px;
    flex-wrap: wrap;
    color: #9C001B;
    font-size: 14px;
    border:1px solid #DCC6CA;
    border-radius: 3px;
    .my-item{
      width: 25%;
      overflow: hidden;
      white-space: nowrap;
      text-overflow: ellipsis;
      span{
        margin-left:8px;
      }
    }
  }
}

.no-award{
  width: 100%;
  text-align: center;
  margin:15px 0;
}
</style>
