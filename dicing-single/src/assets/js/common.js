/* eslint-disable */
exports.install = function(Vue, options) {
	Vue.prototype.alert = function(msg){
		this.$toast({
			message: msg,
			className:'toast',
			duration:2000
		});
	};
};
