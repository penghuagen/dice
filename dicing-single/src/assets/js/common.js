/* eslint-disable */
exports.install = function(Vue, options) {
	//函数命名规则：以大写C结尾为全局函数
    // 封装数据请求

	//通知
	Vue.prototype.msgInfoC = function(msg){
	  	/**
	    * msg 信息
	    */
	    this.$toast({
		    message: msg,
		    className:'toast',
		    duration:2000
		});
	};
	Vue.prototype.alert = function(msg){
		/**
		 * msg 信息
		 */
		this.$toast({
			message: msg,
			className:'toast',
			duration:2000
		});
	};
	//显示加载中
	Vue.prototype.showLoading = function(msg){
	    this.loading = this.$toast.loading({
	      message: '加载中...',
	      forbidClick: true,
	      duration: 0
	    })
	};
	//隐藏加载中
	Vue.prototype.hideLoding = function(msg){
	  	/**
	    * msg 信息
	    */
	    this.loading.clear()
	};
	/**
	 * 时间格式转化   时间戳（1403149534）   时间格式化（yyyy-MM-dd hh:mm:ss）
	 * mode 0获得具体时间    1时间戳时间格式化    2普通时间格式化    3.普通时间转成时间戳    4时间格式化转成时间戳
	 * dateValue mode ！= 0 时间    mode=0时正数几天后，负数几天前
	 * type，0（年月日时分）,1（年月日）,2（时分）  -1普通时间
	 */
	Vue.prototype.getFormatDate = function(params){
		let {splitStr,time,mode} = params;
		if(!splitStr)  splitStr =  '-';
		if(!time) time =  '-';
		if(mode == 0){
			/* 获取具体时间  （7天前）
       * num 正数几天后，负数几天前
       * type -1返回普通时间  其他：格式化类型
       */
			let {num,type} = params
			let F_today = new Date();
			F_today.setDate(F_today.getDate() + num);
			if(type == -1){
				return F_today;
			}else{
				return this.getTimeFormatDate({mode:2,time:F_today})
			}
		}else if(mode == 1){  //时间串格式化    19911005 》 1991-10-05
			let {type} = params;
			let y = time.substring(0, 4);
			let m = time.substring(4, 6);
			let d = time.substring(6, 8);
			let h = time.substring(8, 10);
			let mi = time.substring(10, 12);
			let s = time.substring(12, 14);
			if(type == -1){  //年月日时分秒
				return y + splitStr + m + splitStr + d + ' ' + h + ':' + mi + ':' + s;
			}else if(type == 0){  //年月日时分
				return y + splitStr + m + splitStr + d + ' ' + h + ':' + mi;
			}else if(type == 1){ //年月日
				return y + splitStr + m + splitStr + d ;
			}else if(type == 2){ //时分
				return  h + ':' + mi ;
			}else if(type == 3){ //月日
				return  m + splitStr + d ;
			}
		}else if(mode == 2){  //普通时间格式化
			var y = time.getFullYear();
			var m = time.getMonth()+1;//获取当前月份的日期
			var d = time.getDate();
			return y + splitStr + m + splitStr + d;
		}else if(mode == 3){  //普通时间转成时间戳
			return time.getTime();
		}else if(mode == 4){  //时间格式化转成时间戳
			let F_val = new Date(time.replace(/-/g,'/'));
			return this.getTimeFormatDate({mode:3,time:F_val});
		}else if(mode == 5){  //具体月份
			let F_today = new Date();
			let {num,type} = params;
			F_today.setMonth(F_today.getMonth()+num);
			let Y = F_today.getFullYear();
			let M = F_today.getMonth() + 1;
			let D = F_today.getDate();
			M =(M<10 ? "0"+ M:M);
			if(type == 1){
				return Y + splitStr + M + splitStr +D
			}if(type == 2){
				return Y + splitStr + M
			}
		}else if(mode == 6){  //秒转成分钟、小时
			let {time} = params;
			if(time == 0){
				return '-'
			}else if(time < 60){
				return time + '秒'
			}else if(time >= 60 && time < 3600){
				return parseInt(time/60) + '分' + (parseInt(time%60) == 0 ? '':  parseInt(time%60) + '秒')
			}else if(time >= 3600 ){
				return parseInt(time/60/60) + '小时' + (parseInt(time/60%60) == 0 ? '':  parseInt(time/60%60) + '分')
			}
		}
	};
	/**
	 * 软键盘针对ios,android的兼容
	 */
	Vue.prototype.isAndroidC = function(){
		//判断是不是安卓
		var ua = window.navigator.userAgent.toLocaleLowerCase();
		var isIOS = /iphone|ipad|ipod/.test(ua);
		var isAndroid = /android/.test(ua);
		var $input = document.querySelectorAll('input');
		var $textarea = document.querySelectorAll('textarea');
		var Arr = [];
		for (let i = 0; i < $input.length; i++) {
		    Arr.push($input[i]);
		}
		if($textarea.length!=0){
			for (let i = 0; i < $textarea.length; i++) {
			    Arr.push($textarea[i]);
			}
		}
		for (var i = 0; i < Arr.length; i++) {
			if(!Arr[i].readOnly){
				if(isAndroid){
					//解决不发自动聚焦input
				    var originHeight = document.documentElement.clientHeight || document.body.clientHeight;
				    window.addEventListener('resize', function () {
				      var resizeHeight = document.documentElement.clientHeight || document.body.clientHeight;
				      let mode = document.getElementsByClassName('submit')[0];
				      if (originHeight == resizeHeight) {
				        // Android 键盘收起后操作
						    mode.style.display = 'block';
				      } else {
				        // Android 键盘弹起后操作
   						mode.style.display = 'none';
				      }
				    }, false)
				}
				if(isIOS){
					//解决键盘收起无法回到到原先页面的位置
					Arr[i].addEventListener('blur', () => {
				        var wechatInfo = window.navigator.userAgent.match(/MicroMessenger\/([\d\.]+)/i);
						if (!wechatInfo) return;
						var wechatVersion = wechatInfo[1];
						var version = (navigator.appVersion).match(/OS (\d+)_(\d+)_?(\d+)?/);
						if (+wechatVersion.replace(/\./g, '') >= 674 && +version[1] >= 12) {
						  window.scrollTo(0, Math.max(document.body.clientHeight, document.documentElement.clientHeight));
						}
				    })
				}
			}
		}
	};
	//复制内容
	Vue.prototype.copyPhone = function(clipboard, status, phone){
		let _this = this
		if (status) {
			let platform = _this.GLOBAL.platform.platformGB
			console.log(platform)
			clipboard.on('success', e => {
				if (platform === 'weixin') {
					_this.alert('手机号已复制成功')
					clipboard.destroy()
				} else {
					console.log(platform)
					_this.$dialog.alert({
						message: '手机号已复制成功,请打开微信搜索',
						className: 'common-confirm trans-unset green',
						showCancelButton: true,
						confirmButtonText: '打开微信'
					}).then(() => {
						window.location.href = 'weixin://'
					}).catch(() => {})
				}
				clipboard.destroy()
			})
			clipboard.on('error', e => {
				// 不支持复制
				// _this.alert('该浏览器不支持自动复制');
				if (platform === 'ios') {
					window.webkit.messageHandlers.copyUrl.postMessage(phone)
					_this.$dialog.alert({
						message: '手机号已复制成功,请打开微信搜索',
						className: 'common-confirm trans-unset',
						showCancelButton: true,
						confirmButtonText: '打开微信'
					}).then(() => {
						window.location.href = 'weixin://'
					}).catch(() => {})
				}
				// 释放内存
				clipboard.destroy()
			})
		} else {
			this.alert('无法搜索此微信')
		}
	};

  /**
   * 从store中获取card
   * @param {Object} callback
   */
	Vue.prototype.getCardFromStore = function(callback) {
	    let _this = this;
	    let card = _this.$store.state.card;
	    if (card) {
	      typeof(callback) === 'function' && callback(card);
	    } else {
	      setTimeout(function() {
	        _this.getCardFromStore(callback);
	      }, 200)
	    }
	};

	/**
	 * 表单常见的验证
	 * type:phone手机号码
	 */
	Vue.prototype.formData = function(obj){
		let {type,value} = obj
		if(type === 'phone'){
			return /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/.test(value)
		}
	};

	Vue.prototype.toQueryString = obj => {
		let query = ''
		for (let i in obj) {
			query += '&' + i + '=' + obj[i]
		}
		if (query !== '') query = query.substring(1)
		return query
	}
};
