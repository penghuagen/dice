// 博饼
export default {
  data () {
    return {
      spriteData: {
        0: [{ // 开始
          start: 0,
          length: 1,
          name: ''
        }],
        1: [{ // 博饼的声音
          start: 2.0,
          length: 1.3,
          name: ''
        }],
        2: [{// 一秀
          start: 10.0,
          length: 1.5,
          name: '一秀'
        }],
        3: [{// 二举
          start: 21.5,
          length: 1.5,
          name: '二举'
        }],
        4: [{// 四进
          start: 35.5,
          length: 1.5,
          name: '四进'
        }],
        5: [{// 三红
          start: 45.6,
          length: 1.5,
          name: '三红'
        }],
        6: [{// 对堂
          start: 48.0,
          length: 1.2,
          name: '对堂'
        }],
        7: [{// 状元
          start: 55.5,
          length: 1.8,
          name: '状元'
        }],
        8: [{// 开始博饼
          start: 3.5,
          length: 2.5,
          name: ''
        }],
        9: [{// 轮到(没用)
          start: 9.5,
          length: 2,
          name: ''
        }],
        10: [{
          start: 65.5,
          length: 2.5,
          name: '没货了，继续加油吧'
        }, {
          start: 69,
          length: 2,
          name: '唉吖，这时候才中'
        }, {
          start: 71.5,
          length: 2.5,
          name: '前面都干嘛去了，真是的'
        }, {
          start: 75,
          length: 2,
          name: '奖品怎么就没了'
        }, {
          start: 78.5,
          length: 2.5,
          name: '太遗憾了，已经没奖品了咯'
        }, {
          start: 82,
          length: 2.5,
          name: '哎，没奖品了'
        }, {
          start: 205,
          length: 2,
          name: '早知道不自动博了'
        }],
        11: [{// 轮到你了
          start: 6,
          length: 2.6,
          name: ''
        }, {// 轮到你了，早点博运气会更好一点
          start: 135,
          length: 3.8,
          name: ''
        }],
        12: [{// 博饼结束了
          start: 85.5,
          length: 7.5,
          name: '博饼结束啦~'
        }],
        13: [{// 没有博中
          start: 94.0,
          length: 2.5,
          name: '是不是换只手点点'
        }, {
          start: 98.0,
          length: 2.0,
          name: '太差了，快换个姿势'
        }, {
          start: 101.5,
          length: 2.5,
          name: '要趴着点才能中状元'
        }, {
          start: 105.3,
          length: 2.5,
          name: '唉，最近是不是得罪技术部了'
        },
        {
          start: 109.0,
          length: 3.5,
          name: '偷偷告诉你音量最大的时候中奖率最高'
        },
        {
          start: 113.5,
          length: 3.5,
          name: '要不然去钉钉群发点红包转转运吧~'
        },
        {
          start: 117.5,
          length: 2,
          name: '我去，这破手机'
        },
        {
          start: 120.3,
          length: 3.5,
          name: '这地方风水不行，去厕所博看看好了'
        },
        {
          start: 193.3,
          length: 2.2,
          name: '换个华为P80试试好了'
        },
        {
          start: 197.2,
          length: 2.5,
          name: '换个iphone20肯定能中状元'
        },
        {
          start: 201.2,
          length: 2.5,
          name: '是不是最近需求提多了'
        }],
        14: [{// 骰子飞出去了
          start: 124.5,
          length: 2.5,
          name: '这怎么就飞出去了'
        }, {
          start: 128.0,
          length: 2.8,
          name: '稳重点好吗，拿好一点'
        }, {
          start: 131.0,
          length: 3,
          name: '哎哟，你是飞人乔丹吗'
        }, {
          start: 205,
          length: 2,
          name: '早知道就不自动博了'
        }],
        15: [{// 一秀有奖
          start: 15.0,
          length: 2.5,
          name: '唉，蚊子再小也是肉啊'
        }, {
          start: 18.5,
          length: 2,
          name: '黑人牙膏值得拥有'
        }, {
          start: 140.2,
          length: 2,
          name: '要那么多纸干什么啊'
        }, {
          start: 143.2,
          length: 2.5,
          name: '哎，回家洗碗用得上'
        }, {
          start: 147.0,
          length: 2.5,
          name: '来，拿条毛巾擦擦脸吧'
        }, {
          start: 151.3,
          length: 2.5,
          name: '哇，正好可以配王中王'
        }, {
          start: 156.4,
          length: 2.5,
          name: '哎，拿王中王的第一步哦'
        }, {
          start: 161.2,
          length: 2.2,
          name: '我感觉是一秀界的霸主'
        }, {
          start: 165,
          length: 2.5,
          name: '我去，再来一秀直播吃骰子'
        }, {
          start: 169,
          length: 2.2,
          name: '能不要老是一秀好吗'
        }],
        16: [{// 二举有奖
          start: 27.0,
          length: 2.8,
          name: '把对象叫来，搞不好就能中状元了'
        }, {
          start: 31,
          length: 3.5,
          name: '唉，是不是红衣服穿少了，不然就中状元了'
        }, {
          start: 177.2,
          length: 2.5,
          name: '轻点按，可以中状元哟'
        }, {
          start: 172.5,
          length: 4.2,
          name: '轻点按，轻点按，这样中状元的机率更高一点'
        }, {
          start: 37.2,
          length: 2,
          name: '呦，不错哦'
        }, {
          start: 42.5,
          length: 2.6,
          name: '爽，最好每把都来这个'
        }],
        17: [{// 四进有奖
          start: 37.2,
          length: 2,
          name: '哎呦，不错哦'
        }, {
          start: 39.5,
          length: 1.5,
          name: '就差那么点就是状元了'
        }, {
          start: 42.5,
          length: 2.6,
          name: '爽，最好每把都来这个'
        }, {
          start: 177.2,
          length: 2.5,
          name: '轻点按，可以中状元哟'
        }, {
          start: 172.5,
          length: 4.2,
          name: '轻点按，轻点按，这样中状元的机率更高一点'
        }],
        18: [{// 三红有奖
          start: 37.2,
          length: 2,
          name: '呦，不错哦'
        }, {
          start: 39.5,
          length: 1.5,
          name: '唉，就差那么就是状元了'
        }, {
          start: 42.5,
          length: 2.6,
          name: '爽，最好每把都来这个'
        }, {
          start: 177.2,
          length: 2.5,
          name: '轻点按，可以中状元哟'
        }, {
          start: 172.5,
          length: 4.2,
          name: '轻点按，轻点按，这样中状元的机率更高一点'
        }],
        19: [{// 对堂有奖
          start: 50,
          length: 2.5,
          name: '感觉这个比中状元还难'
        }, {
          start: 53,
          length: 2.0,
          name: '终于中了~'
        }],
        20: [{// 状元有奖
          start: 58,
          length: 2.5,
          name: '噢噢噢噢，状元状元'
        }, {
          start: 62,
          length: 2.5,
          name: '状元！！中了中了！'
        }]
      }
    }
  }
}
