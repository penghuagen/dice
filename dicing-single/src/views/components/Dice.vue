<template>
<div class="boCake-dice-wrap">
  <div class="dice-box" :class="{'start': pageInit}">
     <!--骰子-->
      <template v-for="(item, index) in dicingLog.dices">
        <span class="dice" :class="classObject(index, dicingLog.dicesOut)" :key="index">
          <img :src="require('@/assets/img/boCake/' + item + '.png')">
        </span>
      </template>
  </div>

  <!-- 声音-->
  <audio @timeupdate="timeupdate" preload="auto" hidden="true" ref="xmsAudioRef">
    <source :src="require('@/assets/mp3/dice.mp3')" type="audio/mpeg" />
  </audio>

  <!-- 背景声音-->
  <audio preload="auto" hidden="true" ref="xmsBgAudio" loop="true">
    <source :src="require('@/assets/mp3/bg.mp3')" type="audio/mpeg" />
  </audio>
</div>
</template>
<script>
import diceMixin from '@/assets/js/diceMixin'
export default {
  mixins: [diceMixin],
  name: 'Dice',
  data () {
    return {
      index: 1,
      noAwardVoice: false,
      xmsAudio: null
    }
  },
  props: {
    pageInit: { // 界面初始化时,不需要动画效果
      type: Boolean,
      default: false
    },
    hasAward: { // 是否中奖
      type: Boolean,
      default: false
    },
    dicingState: { // 博饼状态
      type: Number,
      default: 1
    },
    dicingLog: {
      type: Object,
      default: () => {}
    }
  },
  watch: {
    dicingLog: {
      handler (newVal) {
        if (newVal.dices.length > 0 && this.pageInit) {
          this.noAwardVoice = true
          this.index = 1
          this.play()
        }
      },
      deep: true,
      immediate: true
    },
    dicingState: {
      handler (newVal) {
        if (newVal === 4) {
          this.playVoice(12)
        } else if (newVal === 1) {
          this.playVoice(11)
        }
      },
      deep: true,
      immediate: true
    }
  },
  methods: {
    classObject (index, diceOut) {
      let classArray = []
      classArray.push('dice' + (index + 1))
      if (diceOut[index] === 0) {
        classArray.push('diceOut' + (index + 1))
      }
      return classArray
    },
    // 时间更新回调
    timeupdate () {
      let currentTime = this.xmsAudio.currentTime
      let currentSpriteItem = this.spriteItem(this.index)
      if (currentTime > currentSpriteItem.start + currentSpriteItem.length) {
        // 重置
        let diceReset = true
        this.pause()

        // 点击开始博饼时调用,不用重置页面
        if (this.index === 0) {
          diceReset = false
        } else if (this.index === 1) {
          this.index = this.dicingLog.awardId
          // 没有博中
          if (this.dicingLog.awardId === 1) {
            this.index = 13
            // awardId大于7都是状元
          } else if (this.dicingLog.awardId >= 7) {
            this.index = 7
            // 筛子飞出去了
          } else if (this.dicingLog.awardId === -1) {
            this.index = 14
          }

          if (this.spriteItem(this.index).name) {
            this.alert(this.spriteItem(this.index).name)
          }
          diceReset = false
          this.play()
        } else {
          // 试博与没有博到没奖品了不提示(只有正式博才提示)
          if (this.dicingLog.awardId > 1 && this.dicingState > 0 &&
              this.dicingState !== 6 && this.noAwardVoice) {
            if (this.hasAward) {
              if (this.dicingLog.awardId === 2) {
                this.index = 15
              } else if (this.dicingLog.awardId === 3) {
                this.index = 16
              } else if (this.dicingLog.awardId === 4) {
                this.index = 17
              } else if (this.dicingLog.awardId === 5) {
                this.index = 18
              } else if (this.dicingLog.awardId === 6) {
                this.index = 19
              } else {
                this.index = 20
              }
              this.noAwardVoice = false
              if (this.spriteItem(this.index).name) {
                this.alert(this.spriteItem(this.index).name)
              }
              diceReset = false
              this.play()
            } else {
              if (this.dicingLog.awardId < 7) {
                this.index = 10
                this.noAwardVoice = false
                if (this.spriteItem(this.index).name) {
                  this.alert(this.spriteItem(this.index).name)
                }
                diceReset = false
                this.play()
              }
            }
          }
        }

        if (diceReset) {
          this.$emit('diceReset', this.index)
        }
      }
    },
    playVoice (index) {
      this.index = index
      this.play()

      if (index === 0) {
        this.playBgAudio()
      }
    },
    playBgAudio () {
      if (this.$refs.xmsBgAudio) {
        this.$refs.xmsBgAudio.volume = 0.02
        this.$refs.xmsBgAudio.play()
      }
    },
    play () {
      try {
        if (this.xmsAudio) {
          // 音频加载状态
          this.xmsAudio.currentTime = this.spriteItem(this.index).start
          // 如果音频不是停止状态,先停止播放
          if (!this.xmsAudio.paused) {
            this.pause()
          }
          this.xmsAudio.play()
        }
      } catch (e) {
        console.log('音频播放失败')
      }
    },
    spriteItem (index) {
      let length = this.spriteData[index].length
      if (length === 1) {
        return this.spriteData[index][0]
      } else {
        let voiceIndex = this.dicingLog.voiceIndex
        // 默认播放第一个
        let currentIndex = 0
        if (voiceIndex > 0) {
          currentIndex = this.dicingLog.voiceIndex % length
        }
        return this.spriteData[index][currentIndex]
      }
    },
    pause () {
      if (this.xmsAudio && !this.xmsAudio.paused) {
        this.xmsAudio.pause()
      }
    }
  },
  mounted () {
    this.xmsAudio = this.$refs.xmsAudioRef
  }
}
</script>

<style lang="less" scoped>
  @import '~@/assets/css/dice';
  .boCake-dice-wrap {
    position: relative;
    .dice-box {
      position: relative;
      width: 304px;
      height: 250px;
      display: block;
      background: url(~@/assets/img/boCake/bb_bowl.png) no-repeat center center;
      background-size: 100% 100%;
      margin: 190px auto 0;

      .dice {
        position: absolute;
        width: 35px;
        height: 38px;
        overflow: hidden;

        img {
          width: 100%;
          height: 100%;
        }
      }

      .dice1 {
        top: 36%;
        left: 29%;
      }

      .dice2 {
        top: 38%;
        left: 45%;
      }

      .dice3 {
        top: 40%;
        left: 61%;
      }

      .dice4 {
        top: 55%;
        left: 26%;
      }

      .dice5 {
        top: 57%;
        left: 42%;
      }

      .dice6 {
        top: 58%;
        left: 58%;
      }

      .diceOut1{
        top: 68%;
        left: -6%;
        transform:scale(0.8);
        -webkit-transform:scale(0.8);
      }

      .diceOut2{
        top: 54%;
        left: 99%;
      }

      .diceOut3{
        top: 42%;
        left: 109%;
      }

      .diceOut4{
        top: 98%;
        left: 87%;
        z-index: 999;
      }

      .diceOut5{
        top: 98%;
        left: 12%;
        z-index: 999;
      }

      .diceOut6{
        top: 50%;
        left: -12%;
        z-index: 999;
      }
    }

    .award-info{
      width: 100%;
      position: absolute;
      bottom: 0px;
      left:0px;
      text-align: center;
      span{
        background-color:rgba(0,0,0,0.5);
        color: #FFF;
        padding:8px;
      }
    }
  }

</style>
