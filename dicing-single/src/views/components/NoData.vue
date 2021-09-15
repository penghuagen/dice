<template>
  <div class="no-more flex align-middle justify-center">
    <div><i></i><p>{{msg}}</p></div>
  </div>
</template>

<script>
export default {
  name: 'noMore',
  data () {
    return {
    }
  },
  props: {
    msg: { // 提示文字
      type: String,
      default: ''
    }
  }
}
</script>

<style lang="less" scoped>
@import '~@/assets/css/base';
.no-more{
    padding:20px 0;
    i{
        width:92px;
        height:120px;
        display:block;
        margin:20px auto 10px;
        background-image:url('~@/assets/img/nothing.png');
        background-size:100% 100%
    }
    p{
        text-align:center;
        color:@fontGrey;
        margin-bottom:10px;
    }
}
</style>
