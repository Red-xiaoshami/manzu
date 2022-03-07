<template>
	<view class="bg">
		<image class="login_header_norma" src="../../static/images/login_header_normal.png"></image>
		<view class="input-box">
			<input type="number" @input="onKeyInputMobile" class="mobile input-class" maxlength="11" placeholder="请输入手机号" placeholder-class="input-pla" />
			<view class="item-box">
				<input type="text" @input="onKeyInputCode" class="mobile input-class" maxlength="6" placeholder="请输入验证码" placeholder-class="input-pla" />
				<text class="send-code" @click="sendCode">{{codeHtml}}</text>
			</view>
		</view>
		<image @click="toLogin" class="login-btn" :src="canLogin?'../../static/images/ic_affirm_click.png':'../../static/images/ic_affirm_unclick.png'"></image>
		<!-- <view class="title">———&nbsp;&nbsp;&nbsp;其他方式登录&nbsp;&nbsp;&nbsp;———</view>
		<view class="flex flex_center flex_just login-third-box">
			<view @click="thirdLogin('weixin')" class="flex flex_center flex_column flex_just item">
				<image class="login-icon" src="../../static/images/login_way_wechat_dark.png"></image>
				<text>微信</text>
			</view>
			<view @click="thirdLogin('qq')" class="flex flex_center flex_column flex_just item">
				<image class="login-icon" src="../../static/images/login_way_qq_dark.png"></image>
				<text>QQ</text>
			</view>
		</view> -->
	</view>
</template>

<script>
	export default {
		data() {
			return {
				mobile: "",
				code: "",
				canLogin: false,
				codeIsing: false,
				timer: null,
				codeTime: 0,
				codeHtml: "发送验证码",
				providerArr: []
			};
		},
		mounted() {
			// this.getProvider();
		},
		onBackPress(options) {
            if (options.from === 'navigateBack') {
                return false;
            }
            this.back();
            return true;
        },
		methods: {
			back() {
                uni.navigateBack({
                    delta: 2
                });
            },
			onKeyInputMobile: function(e) {
				if(e.target.value.length>11) {
					this.mobile =  e.target.value.slice(0, 11);
				} else {
					this.mobile = e.target.value;
				}
				this.inputLength();
			},
			onKeyInputCode: function(e) {
				this.code = e.target.value;
				this.inputLength();
			},
			inputLength: function() {
				if(this.mobile.length>=11 && this.code.length>0) {
					this.canLogin = true
				} else {
					this.canLogin = false
				} 
			},
			sendCode: function() {
				let that = this
				if(!that.mobile) {
					plus.nativeUI.toast("请输入手机号"); 
					return;
				}  
				if(that.codeIsing) {
					return;
				}
				uni.showLoading({
					title: '加载中'
				});
				
				uni.request({
					url: this.$dev.baseURL + this.$api.sendSms+ "?mobile="+ this.mobile,
					data: {
						mobile: this.mobile,
					},
					method: "POST", 
					dataType: "json",
					success: (res) => {
						uni.hideLoading()
						if(res.data.succ) {
							getCode();
						} else { 
							plus.nativeUI.toast(res.data.msg); 
						}
					},
					fail: (res) => {
						uni.hideLoading()
						console.log(JSON.stringify(res));
					},
				});
				
				function getCode() {
					var TIME_COUNT = 59;
					that.codeHtml = "59s后刷新";
					that.codeIsing = true;
					if(!that.timer) {
						that.codeTime = TIME_COUNT;
						that.timer = setInterval(function() {
							if(that.codeTime > 0 && that.codeTime <= TIME_COUNT) {
								that.codeTime--;
								that.codeHtml = that.codeTime + "s后刷新";
							} else {
								that.codeIsing = false;
								clearInterval(that.timer);
								that.timer = null;
								that.codeHtml = "发送验证码";
							}
						}, 1000)
					}
				}
			},
			toLogin: function () {
				let that = this
				if(!that.canLogin) {
					return;
				}
				
				if(!that.mobile) {
					plus.nativeUI.toast("请输入手机号"); 
					return;
				}
				
				if(!that.code) {
					plus.nativeUI.toast("请输入验证码"); 
					return;
				}
				
				uni.showLoading({
					title: '加载中'
				});
				
				uni.request({
					url: this.$dev.baseURL + this.$api.toLogin + "?mobile=" + this.mobile + "&vcode=" + this.code,
					header: {
						"Content-Type": "application/json;charset=UTF-8"
					},
					data: {
						mobile: this.mobile, 
						vcode: this.code
					},
					method: "POST", 
					dataType: "json",
					success: (res) => {
						uni.hideLoading()
						if(res.data.succ) {
							uni.setStorage({
								key: "userInfo",
								data: res.data.result,
								success: function () {}
							});
						  uni.switchTab({
							  url: '../Recommend/Main'
						  });
						} else {
							plus.nativeUI.toast(res.data.msg); 
						}
					},
					fail: (res) => {
						uni.hideLoading()
						console.log(JSON.stringify(res));
					},
				});
			},
			getProvider: function() {
				let that = this
				uni.getProvider({
					service: 'oauth',
					success: function (res) {
						that.providerArr = res.provider;
						console.log(res.provider)
					}
				});
			},
			thirdLogin: function(type) {
				if (~this.providerArr.indexOf('qq') && type=="qq") {
					console.log(type)
					uni.login({
						provider: 'qq',
						success: function (loginRes) {
							console.log(JSON.stringify(loginRes));
							uni.switchTab({
								 url: '/pages/My/MyMain'
							});
						}
					});
				} else if(~this.providerArr.indexOf('weixin') && type=="weixin") {
					console.log(type)
					uni.login({
						provider: 'weixin',
						success: function (loginRes) {
							console.log(JSON.stringify(loginRes));
							uni.switchTab({
								 url: '/pages/My/MyMain'
							});
						}
					});
				}
			},
// 			login: function() {
// 				uni.request({
// 					url: this.$dev.baseURL + this.$api.toLogin,
// 					data: {
// 					},
// 					method: "POST", 
// 					dataType: "json",
// 					success: (res) => {
// 						console.log(JSON.stringify(res))
// 						uni.hideLoading()
// 						if(res.data.succ) {
// 							
// 						} else {
// 							uni.showToast({
// 								title: res.data.msg,
// 								duration: 2000
// 							});
// 						}
// 					},
// 					fail: (res) => {
// 						uni.hideLoading()
// 						console.log(JSON.stringify(res));
// 					},
// 				});
// 			}
		}
	}
</script>

<style>
 .bg {
	 position: relative;
	 background: #FDE23D;
	 background-size: 100% 100%;
	 height: 100vh;
	 width: 100%;
	 text-align: center;
 }
 .input-pla {
	 font-size: 32upx;
	 width: 60%;
 }
 .input-box, .item-box {
	 position: relative;
 }
 .input-box .send-code {
	font-size: 28upx; 
	position: absolute;
	top: 50%;
	transform: translateY(-50%);
	right: 150upx;
 }
 .input-box .input-class {
	 font-size: 32upx;
	 text-align: left;
	 padding-left: 30upx;
	 margin-bottom: 28upx;
 }
 
 .login_header_norma {
	 display: inline-block;
	 width: 280upx; 
	 height: 210upx;
	 margin-top: 80upx;
	 margin-bottom: -10upx;
 } 
 
 .mobile {
	 width: 65%;
	 height: 74upx;
	 line-height: 74upx;
	 background: #fff;
	 margin: 0 auto;
	 border-radius: 20upx;
 }
 
 .title {
	 font-size: 30upx;
	 margin-top: 80upx;
	 margin-bottom: 30upx;
 }
 .login-third-box text {
	 display: none; 
	 font-size: 28upx;
 }
 .login-third-box .item {
	 margin: 0 40upx;
 }
 .login-icon {
	 width: 65upx;
	 height: 65upx;
	 margin-bottom: 10upx;
 }
 .login-btn {
	 width: 100upx;
	 height: 100upx;
	 margin-top: 50upx;
 }
 
</style>
