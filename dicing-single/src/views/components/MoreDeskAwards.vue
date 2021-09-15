<template>
  <div class="more-desk-awards">
  <desk-awards :awards="awards"
               :deskAwards="deskAwards"
               :awardDicingLogs="dicingLogs"
               :deskUsers="deskUsers"
               :defaultImg="defaultImg"></desk-awards>
  </div>
</template>

<script>
import DeskAwards from './DeskAwards'
export default {
  name: 'MoreDeskAwards',
  data () {
    return {
      deskUsers: [],
      dicingLogs: [],
      deskAwards: [],
      url: {
        deskGrades: '/deskGrades' // 获取其他桌详情
      }
    }
  },
  props: {
    defaultImg: {
      type: String,
      default: ''
    },
    deskNo: {
      type: Number,
      default: 0
    },
    awards: {
      type: Array,
      default: () => []
    }
  },
  watch: {
    deskNo: {
      handler () {
        this.deskGrades()
      },
      deep: true,
      immediate: true
    }
  },
  components: {
    DeskAwards
  },
  methods: {
    deskGrades () {
      this.$http.post(this.url.deskGrades, {
        deskNo: this.deskNo,
        apiHostKey: 'dicing_url'
      }).then(({data}) => {
        this.deskAwards = data.deskAwards.reverse()
        this.deskUsers = data.deskUsers
        this.dicingLogs = data.dicingLogs
      })
    }
  }
}
</script>

<style lang="less" scoped>

</style>
