<template>
  <div class="boCake-rule-wrap">
    <div class="boCake-rule-title">
      <img :src="require('@/assets/img/boCake/rule.png')" width="148px" height="31px"/>
    </div>
    <div class="rule-detail">
      <div>注意事项：</div>
      <div>1、只有状元可以抢，其它奖品不能抢。同一个人如果博到多次状元，以最后一次的为准(有可能会出现状元变小的情况)，
        活动结束时，由博到状元最大的人获得;</div>
      <div>2、座位顺序由系统随机生成，博饼只能依次进行;</div>

    </div>
    <div class="rule-detail">
      <div>奖品发放：</div>
      <div>1、发放时间：博饼后统计发放邮寄给大家;</div>
      <div>2、发放方式：获得奖项的人可以选择想要奖品(点击奖品区域可以查看奖品图片)还是兑换成京东自营购物卡，
        比如一秀一个兑换10元购物卡，二举一个奖品兑换20元购物卡，每桌奖品数量以及兑换金额如下表所示;</div>
    </div>
    <table class="rule-table" width="100%" cellspacing="0" cellpadding="0s">
      <thead>
        <tr>
          <td width="20%">奖项</td>
          <td width="40%">图解</td>
          <td width="40%">奖品</td>
        </tr>
      </thead>
      <tbody>
      <tr>
        <td>{{king.name}}</td>
        <td>
          <div class="dice-desc-wrap">
            <div class="text">{{king.desc}}</div>
          </div>
        </td>
        <td @click="listImgLook(king.images)" class="text">
          <div v-for="(name, index) in king.award" :key="'king_' + index" class="text-description">{{name}}</div>
        </td>
      </tr>
        <tr>
          <td rowspan="7">{{champion.name}}</td>
          <td>
            <div class="dice-desc-wrap">
              <img v-for="(item, diceIndex) in rule[12].dices" :key="'champion_dice1_' + diceIndex"
                   :src="require('@/assets/img/boCake/rule/' + item + '.png')">
              <div class="text">{{rule[12].desc}}</div>
            </div>
          </td>
          <td rowspan="7" @click="listImgLook(champion.images)" class="text">
            <div v-for="(name, index) in champion.award" :key="'champion_' + index" class="text-description">{{name}}</div>
          </td>
        </tr>
        <tr v-for="(item, index) in champion.graph" :key="'champion_graph_' + index">
          <td>
            <div class="dice-desc-wrap">
              <img v-for="(dice, diceIndex) in rule[item].dices" :key="'champion_dice_' + diceIndex"
                   :src="require('@/assets/img/boCake/rule/' + dice + '.png')">
              <div class="text">{{rule[item].desc}}</div>
            </div>
          </td>
        </tr>
        <tr v-for="(item, index) in awards" :key="'awards_' + index">
          <td>{{item.name}}</td>
          <td>
            <div class="dice-desc-wrap">
              <img v-for="(dice, diceIndex) in item.graph.dices" :key="'award_dice_' + diceIndex"
                   :src="require('@/assets/img/boCake/rule/' + dice + '.png')">
              <div class="text">{{item.graph.desc}}</div>
            </div>
          </td>
          <td @click="listImgLook(item.images)">
            <div v-for="(name, index) in item.award" :key="'award_' + index" class="text-description">{{name}}</div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<script>
import {ImagePreview} from 'vant'
export default {
  name: 'DiceRule',
  data () {
    return {
      king: {
        name: '王中王',
        desc: '采用钉钉群抢红包的方式，手气最佳的人获得王中王的奖项',
        award: ['华凌美的洗碗机'],
        images: ['http://static.xms.4846.com/奖品/王中王/华凌美的洗碗机.jpg']
      },
      champion: {
        name: '状元',
        graph: [11, 10, 9, 8, 7, 6],
        award: ['美的微波炉', '摩飞分类菜板', '苏泊尔破壁机', '小熊餐具消毒柜', '蒸汽足浴桶'],
        images: ['http://static.xms.4846.com/奖品/状元/美的微波炉.png',
          'http://static.xms.4846.com/奖品/状元/摩飞分类菜板.png',
          'http://static.xms.4846.com/奖品/状元/苏泊尔破壁机.png',
          'http://static.xms.4846.com/奖品/状元/小熊餐具消毒柜.png',
          'http://static.xms.4846.com/奖品/状元/蒸汽足浴桶.png']
      },
      rule: {
        12: {
          dices: [4, 4, 4, 4, 1, 1],
          desc: '状元插金花：四个4点 两个1点'
        },
        11: {
          dices: [4, 4, 4, 4, 4, 4],
          desc: '六勃红：六个面全是4点'
        },
        10: {
          dices: [1, 1, 1, 1, 1, 1],
          desc: '遍地锦：六个面全是1点'
        },
        9: {
          dices: [2, 2, 2, 2, 2, 2],
          desc: '六勃黑：六个面全是黑点'
        },
        8: {
          dices: [4, 4, 4, 4, 4, 0],
          desc: '五红：五个4点，带6最大带1最小'
        },
        7: {
          dices: [3, 3, 3, 3, 3, 0],
          desc: '五子：五个1 2 3 5 6都是， 带点最大的赢'
        },
        6: {
          dices: [4, 4, 4, 4, 0, 0],
          desc: '四红：四个4,带12最大三点最小'
        }
      },
      awards: [
        {
          name: '对堂',
          graph: {
            dices: [1, 2, 3, 4, 5, 6],
            desc: '六个面分别是1,2,3,4,5,6点'
          },
          award: ['飞利浦电动牙刷', '美的电炖锅', '美的小小白电饭煲', '全自动电饼铛', '水星家纺春秋被'],
          images: ['http://static.xms.4846.com/奖品/对堂/飞利浦电动牙刷.png',
            'http://static.xms.4846.com/奖品/对堂/美的电炖锅.png',
            'http://static.xms.4846.com/奖品/对堂/美的小小白电饭煲.png',
            'http://static.xms.4846.com/奖品/对堂/全自动电饼铛.png',
            'http://static.xms.4846.com/奖品/对堂/水星家纺春秋被.png']
        },
        {
          name: '三红',
          graph: {
            dices: [4, 4, 4, 0, 0, 0],
            desc: '只有三个面是4点'
          },
          award: ['餐具套装', '飞科电吹风', '米家自动洗手机套装', '水星家纺毛毯', '小米商务双肩包'],
          images: ['http://static.xms.4846.com/奖品/三红/餐具套装.jpg',
            'http://static.xms.4846.com/奖品/三红/飞科电吹风.jpg',
            'http://static.xms.4846.com/奖品/三红/米家自动洗手机套装.jpg',
            'http://static.xms.4846.com/奖品/三红/水星家纺毛毯.jpg',
            'http://static.xms.4846.com/奖品/三红/小米商务双肩包.jpg']
        },
        {
          name: '四进',
          graph: {
            dices: [2, 2, 2, 2, 0, 0],
            desc: '有四个面点数相同'
          },
          award: ['德尔玛喷水拖把', '洁丽雅浴巾', '洁柔纸面巾', '蓝月亮洗衣液', '澎澎沐浴露'],
          images: ['http://static.xms.4846.com/奖品/四进/德尔玛喷水拖把.jpg',
            'http://static.xms.4846.com/奖品/四进/洁丽雅浴巾.jpg',
            'http://static.xms.4846.com/奖品/四进/洁柔纸面巾.jpg',
            'http://static.xms.4846.com/奖品/四进/蓝月亮洗衣液.jpg',
            'http://static.xms.4846.com/奖品/四进/澎澎沐浴露.jpg']
        },
        {
          name: '二举',
          graph: {
            dices: [4, 4, 0, 0, 0, 0],
            desc: '只有两面是4点'
          },
          award: ['洁柔纸面巾', '棉麻抱枕', '欧乐B漱口水', '舒克牙刷', '土蜂蜜'],
          images: ['http://static.xms.4846.com/奖品/二举/洁柔纸面巾一提.jpg',
            'http://static.xms.4846.com/奖品/二举/棉麻抱枕.jpg',
            'http://static.xms.4846.com/奖品/二举/欧乐B漱口水.jpg',
            'http://static.xms.4846.com/奖品/二举/舒克牙刷.jpg',
            'http://static.xms.4846.com/奖品/二举/土蜂蜜.jpg']
        },
        {
          name: '一秀',
          graph: {
            dices: [4, 0, 0, 0, 0, 0],
            desc: '只有一面是4点'
          },
          award: ['AXE洗洁精', '黑人牙膏', '洁丽雅纯棉毛巾', '维达卷纸'],
          images: ['http://static.xms.4846.com/奖品/一秀/AXE洗洁精.jpg',
            'http://static.xms.4846.com/奖品/一秀/黑人牙膏.jpg',
            'http://static.xms.4846.com/奖品/一秀/洁丽雅纯棉毛巾.jpg',
            'http://static.xms.4846.com/奖品/一秀/维达卷纸.jpg']
        }
      ]
    }
  },
  methods: {
    listImgLook (images) {
      if (images && images.length > 0) {
        ImagePreview({
          images: images,
          startPosition: 0
        })
      } else {
        this.alert('暂无图片')
      }
    }
  }
}
</script>

<style lang="less" scoped>
  //规则说明
  .boCake-rule-wrap{
    margin-top: 20px;
    .boCake-rule-title{
      text-align: center;
    }
    .rule-detail{
      margin: 15px 0;
      line-height: 1.8;
      font-size: 11px;
      color: #F0908F;
    }

    .rule-table{
      text-align: center;
      font-size: 14px;
      border-left:5px solid #720504;
      border-top:5px solid #720504;
      color: #FFBABA;
      td{
        border-bottom:5px solid #720504;
        border-right:5px solid #720504;
        padding:8px;
        background-color: #640200;
        .dice-desc-wrap{
          img{
            width: 15px;
            height: 15px;
            margin: 0 2px;
          }
          .text{
            font-size: 10px;
          }
        }
      }
      .text-description{
        font-size: 12px;
        text-decoration:underline
      }
    }
  }
</style>
