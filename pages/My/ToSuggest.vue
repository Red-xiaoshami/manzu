<template>
	<view>
		<textarea maxlength="300" v-model="content" placeholder-class="placeholder-text" placeholder="问题描述的越详细我们解决的越快哦~"></textarea>
		<view class="flex flex_center contact-box">
			<text>联系方式：</text>
			<input v-model="contact" type="text" maxlength="11" placeholder-class="placeholder-text" placeholder="请输入您的手机号" />
		</view>
		<view class="btn" @click="toSuggest">提交</view>
		<view class="content">
			<view>温馨提示：</view>
			<view>反馈的内容我们会及时查看，并第一时间微信联系您或请联系客服:</view>
			<image class="icon_wechat" src="../../static/images/icon_wechat.jpg"></image>
			<!-- <view>343242342</view> -->
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				contact: "",
				content: "",
			};
		},
		mounted() {
			
		},
		methods: {
			toSuggest: function() {
				let that = this
				that.isShowLoading = true
				if(!this.content) {
					plus.nativeUI.toast("请输入问题描述");
					return;
				}
				if(!this.contact) {
					plus.nativeUI.toast("请输入联系方式");
					return;
				}
				if(this.contact.length < 11) {
					plus.nativeUI.toast("手机号输入有误");
					return;
				}
				var params = {
					contact: this.contact,
					content: this.content
				}
				console.log(this.$dev.baseURL + this.$api.feedback)
				uni.request({
					url: this.$dev.baseURL + this.$api.feedback,
					data: params,
					method: "POST", 
					dataType: "json",
					success: (res) => {
						console.log(JSON.stringify(res.data));
						uni.hideLoading();
						plus.nativeUI.toast(res.data.msg);
					},
					fail: (res) => {
						uni.hideLoading()
						console.log(JSON.stringify(res));
					},
					complete:() => {
						that.isShowLoading = false;
					}
				});
			},
		}
	}
</script>

<style>
	.placeholder-text {
		font-size: 30upx;
		color: #999;
	}
	textarea {
		height: 300upx;
		padding: 30upx;
		font-size: 30upx;
		/* border-bottom: solid 2upx #f0f0f0 ; */
		margin: 0 auto;
	}
	.contact-box {
		padding: 0 30upx;
		box-sizing: border-box;
		font-size: 0;
	}
	.contact-box text {
		font-size: 32upx;
		color: #000;
	}
	.contact-box input {
		width: 70%;
		padding-left: 30upx;
		font-size: 32upx;
		border: solid 1px #f0f0f0;
		margin: 20upx 0;
		height: 60upx;
		line-height: 60upx;
	}
	view.btn {
		background: #FDE23D!important;
		color: #000;
		font-size: 32upx;
		width: 92%;
		height: 80upx;
		line-height: 80upx;
		text-align: center;
		border-radius: 20upx;
		margin: 0 auto;
		margin-top: 20upx;
	}
	view.btn:active {
		opacity: .8;
	}
	.content {
		margin-top: 50upx;
	}
	.content view {
		font-size: 28upx;
		color: #999;
		padding: 5upx 30upx;
	}
	.content image.icon_wechat {
		display: inherit;
		width: 300upx;
		height: 300upx;
		margin: 0 auto;
	}
</style>
