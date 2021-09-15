/* eslint-disable */
// 命名要求。在参数尾部加个GB  如unitGB

let param = {
		env : { // 版本控制变量(环境版本控制prod 正式(生产)、test测试、dev开发  )
	    type: 'dev', // 项目环境
	    prodUrlGB: {
	      is_url: 'https://xms.4846.com', // 基础服务
        house_collect_url: 'https://house.xms.4846.com/houseCollect', // 采集服务
				is_static_url: 'https://isstatic.xms.4846.com', // 基础服务
	      mweb_url: 'https://weixin.4846.com/mweb', // 微信服务
	      hwimg_url: 'https://hwimg.xms.4846.com/image-web', // 图片上传
	      hwvideo_url: 'https://hwvideo.xms.4846.com/videoweb', // 视频上传
        oa_url : 'https://oa.xms.4846.com/officialAnalysis-web',  //大数据
        dicing_ws:'wss://dicing.xms.4846.com',  //博饼
        dicing_url:'https://dicing.xms.4846.com',  //博饼
				erp_url:'https://erp.4846.com/' // erp
	    },
	    testUrlGB: { // 测试
	      is_url:'http://xms3.4846.com', // 接口地址
        house_collect_url: 'http://xms3.4846.com/houseCollect/', // 采集服务
				is_static_url:'http://xms3.4846.com',
	      mweb_url: 'http://xms3.4846.com/mweb',
	      hwimg_url: 'http://hwimg.xms.4846.com/image-web', // 图片上传
	      hwvideo_url: 'http://hwvideo.xms.4846.com/videoweb', // 视频上传
        oa_url : 'http://oatest.xms.4846.com/officialAnalysis-web',//大数据
        dicing_ws:'ws://dicing.xms.4846.com',  //博饼
        dicing_url:'http://dicing.xms.4846.com',  //博饼
				erp_url:'http://erp3.4846.com:8181'
	    },
	    devUrlGB: { // 本地
	      is_url: 'http://xms3.4846.com', // 接口地址
        house_collect_url: 'http://xms3.4846.com/houseCollect/', // 采集服务
        is_static_url:'http://xms3.4846.com',
	      mweb_url: 'http://weixin.4846.com/mweb',
	      hwimg_url: 'http://hwimg.xms.4846.com/image-web', // 图片上传
	      hwvideo_url: 'http://hwvideo.xms.4846.com/videoweb', // 视频上传
        oa_url : 'http://oatest.xms.4846.com/officialAnalysis-web',//大数据
        dicing_ws:'ws://local.4846.com:8080',  //博饼
        dicing_url:'http://local.4846.com:8080',  //博饼
				erp_url:'http://erp3.4846.com:8181'
	    }
	},
  getReqBaseUrl (type) {
    /* type 域名类型(如：'aicard_url')
  	 * */
    return this.env[this.env.type + 'UrlGB'][type];
  },
  comvar: { // 通用变量
    offsetGB: 50,
		appCode: 10, //  app版本号
    nomoreGB: '没有更多数据了' // 没有数据的提示
  },
	platform: {
		platformGB: 'ios', //  平台
		initPlatform () {
			let _this = this
			let platform = 'ios'
			let userAgent = window.navigator.userAgent.toLowerCase()
			if (userAgent.indexOf('android') > -1) {
				if (userAgent.indexOf('micromessenger') > -1) {
					platform = 'weixin'
				} else {
					platform = 'android'
				}
			} else if (userAgent.indexOf('iphone') > -1 ||
				userAgent.indexOf('ipad') > -1 ||
				userAgent.indexOf('ipod') > -1 ||
				userAgent.indexOf('xms_ios') > -1) {
				if (userAgent.indexOf('micromessenger') > -1) {
					platform = 'weixin'
				} else {
					platform = 'ios'
				}
			}

			_this.platformGB = platform
		}
	},
	initAppCode () {
		let userAgent = window.navigator.userAgent.toLowerCase()
		if (userAgent) {
			let uaArr = userAgent.split('appcode=')
			if (uaArr.length === 2) {
				this.comvar.appCode = Number(uaArr[1])
			} else {
				let appCode = localStorage.getItem('appCode')
				if (appCode) {
					this.comvar.appCode = Number(appCode)
				}
			}
		}
		this.platform.initPlatform()
	}
}

export default param
