<template>
  <!--本桌人员-->
  <van-popup v-model="popupShow" position="bottom" closeable
             close-icon="close">
    <div class="desk-user-wrap">
    <div class="title">本桌人员名单</div>
      <div class="table-wrap">
      <table width="100%" cellspacing="0" cellpadding="0s">
        <thead>
          <tr><td width="20%">序号</td><td width="40%">头像</td><td width="40%">姓名</td></tr>
        </thead>
        <tbody>
            <tr v-for="(item, index) in deskUsers" :key="index">
              <td class="index">{{item.seqNo}}</td>
              <td><img class="avatar" :onerror="defaultImg" :src="item.avator"></td>
              <td class="name">{{item.userName}}</td>
            </tr>
        </tbody>
      </table>
      </div>
    </div>
  </van-popup>
</template>
<script>
export default {
  name: 'DeskUser',
  data () {
    return {
      popupShow: false
    }
  },
  props: {
    deskUsers: {
      type: Array,
      default: () => []
    },
    defaultImg: {
      type: String,
      default: ''
    }
  },
  methods: {
    show () {
      this.popupShow = true
    }
  }
}
</script>

<style lang="less" scoped>
.desk-user-wrap{
  background-color: #F9F5EE;
  padding:10px;
  .title{
    font-size: 16px;
    font-weight: bold;
    text-align: center;
    margin:5px 0 15px;
  }

  .table-wrap{
    text-align: center;
    max-height: 400px;
    overflow: auto;
    /*-webkit-overflow-scrolling:touch;*/
    background-color: #FFF;
    thead {
      tr{
        font-weight: bold;
        line-height: 2;
        font-size: 14px;
        text-align: center;
      }
    }
    tbody{
      tr {
        border-top: 12px solid #FFF;
        border-bottom: 12px solid #FFF;
        .index{
          font-weight: bold;
          font-size: 16px;
        }

        .avatar{
          display: inline-block;
          border:1px solid #C7AEAE;
          width: 30px;
          height: 30px;
          border-radius: 30px;
        }

        .name{
          font-size: 14px;
          overflow: hidden;
          white-space: nowrap;
          text-overflow: ellipsis;
          max-width: 120px;
        }
      }
    }
  }
}
</style>
