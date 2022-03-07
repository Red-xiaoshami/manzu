<template>
	<view v-clock>
		<div>
			<image class="waveTop" src="../../static/images/waveTop.png"></image> 
			<image class="waveTop-white" src="../../static/images/waveBg.png"></image>
			<image class="logo" :src="imgUrl+userInfo.header_icon"></image>
		</div>
		<div class="my-info-box">
			<div class="flex flex_center flex_just"> 
				<p class="name"> </p>
				<image class="sex" :src="userInfo.gender?'../../static/images/boy.png':'../../static/images/girl.png'"></image>
			</div>
			<div class="id-no">ID:{{userInfo.user_id}}</div>
			<view class="flex flex_just flex_center section-2">
				<view @click="goRechargeCenter" class="flex flex_just flex_center flex_column">
					<image src="../../static/images/y-person-coin.png"></image>
					<text>{{moneyData.money}}金币</text>
				</view>
				<text class="line"></text>
				<view @click="goOpenYearFee" class="flex flex_just flex_center flex_column" style="position: relative;">
					<image v-if="isVIP==1" src="../../static/images/annual.png"></image>
					<image v-else src="../../static/images/unannual.png"></image>
					<text>开通年费</text>
					<image class="ques" src="../../static/images/ques.png"></image>
				</view>
			</view>
			<view class="list-box">
				<view class="flex flex_bettwen flex_center list-item">
					<view @click="goRechargeCenter" class="flex flex_start flex_center">
						<image class="list-icon" src="../../static/images/recharge.png"></image>
						<text>充值中心</text>
					</view>
					<image class="arrow-r" src="../../static/images/icon_arrow_r.png"></image>
				</view>
				<view @click="goWelfareCenter" class="flex flex_bettwen flex_center list-item">
					<view class="flex flex_start flex_center">
						<image class="list-icon" src="../../static/images/y-center-task.png"></image>
						<text>福利中心</text>
					</view>
					<view class="flex flex_start flex_center">
						<text class="color-FD875A">免费看漫画</text>
						<image class="arrow-r" src="../../static/images/icon_arrow_r.png"></image>
					</view>
				</view>
				<view @click="goMyReward" class="flex flex_bettwen flex_center list-item">
					<view class="flex flex_start flex_center">
						<image class="list-icon" src="../../static/images/record.png"></image>
						<text>我的打赏</text>
					</view>
					<image class="arrow-r" src="../../static/images/icon_arrow_r.png"></image>
				</view>
				<view @click="goMyRecord" class="flex flex_bettwen flex_center list-item">
					<view class="flex flex_start flex_center">
						<image class="list-icon" src="../../static/images/y-center-record.png"></image>
						<text>我的记录</text>
					</view>
					<image class="arrow-r" src="../../static/images/icon_arrow_r.png"></image>
				</view>
				<view @click="goThirdWays" class="flex flex_bettwen flex_center list-item" style="display: none;">
					<view class="flex flex_start flex_center">
						<image class="list-icon" src="../../static/images/autoBuy.png"></image>
						<text>绑定其他账号</text>
					</view>
					 <!-- <switch class="switch" color="#fff" checked /> -->
				</view>
				<!-- <view class="flex flex_bettwen flex_center list-item">
					<view class="flex flex_start flex_center">
						<image class="list-icon" src="../../static/images/autoBuy.png"></image>
						<text>自动购买</text>
					</view>
					 <switch class="switch" color="#fff" checked />
				</view> -->
				<view @click="goToSuggest" class="flex flex_bettwen flex_center list-item">
					<view class="flex flex_start flex_center">
						<image class="list-icon" src="../../static/images/feedBack.png"></image>
						<text>意见反馈</text>
					</view>
					<image class="arrow-r" src="../../static/images/icon_arrow_r.png"></image>
				</view>
				<view @click="goToHelpCenter" class="flex flex_bettwen flex_center list-item">
					<view class="flex flex_start flex_center">
						<image class="list-icon" src="../../static/images/helpCenter.png"></image>
						<text>客服中心</text>
					</view>
					<image class="arrow-r" src="../../static/images/icon_arrow_r.png"></image>
				</view>
			</view>
		</div>
		<Loading v-show="isShowLoading"></Loading>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				userInfo: {},
				imgUrl: this.$dev.mediaURL,
				moneyData: 0,
				isVIP: 0,
				isShowLoading: true
			};
		},
		onShow() {
			this.getMoney();
		},
		created() {
			let that = this
			uni.getStorage({
				key: 'userInfo',
				success: function (res) {
					console.log(JSON.stringify(res.data));
					that.userInfo = res.data;
				}
			});
		},
		mounted() {
			this.getMoney();
		},
		methods: {
			  goRechargeCenter() {
				uni.navigateTo({ 
					url: 'RechargeCenter' 
				});
			  },
			  goOpenYearFee() {
				uni.navigateTo({ 
					url: 'OpenYearFee' 
				});
			  },
			goWelfareCenter() {
				uni.navigateTo({ 
						url: 'WelfareCenter' 
				});
			},
			goMyReward() {
				uni.navigateTo({ 
						url: 'MyReward' 
				});
			},
			goMyRecord() {
				uni.navigateTo({ 
						url: 'MyRecord' 
				});
			},
			goToSuggest() {
				uni.navigateTo({ 
						url: 'ToSuggest' 
				});
			},
			goToHelpCenter() {
				plus.runtime.openURL('mqq://im/chat?chat_type=wpa&uin=439045494&version=1&src_type=web')
				return;
				uni.navigateTo({ 
						url: 'ToHelpCenter' 
				});
			},
      goThirdWays() {
				uni.navigateTo({ 
						url: 'ThirdWaysList' 
				});
			},
			getMoney: function() {
				let that = this
				
				uni.request({
					url: this.$dev.baseURL + this.$api.getMoney,
					data: {
					},
					method: "POST", 
					dataType: "json",
					success: (res) => {
						console.log(JSON.stringify(res.data));
						uni.hideLoading();
						
						if(res.data.code == "-11") {
							plus.nativeUI.toast(res.data.msg);
							uni.navigateTo({ 
								url: 'Login' 
							});
							return;
						}
						
						if(res.data.succ) {
							that.moneyData = res.data.result;
							that.isVIP = res.data.vip;
						} else { 
							plus.nativeUI.toast(res.data.msg);
						}
					},
					fail: (res) => {
						uni.hideLoading()
						console.log(JSON.stringify(res));
					},
					complete:() => {
						that.isShowLoading = false;
					}
				});
			}
		}
	}
</script>

<style>
	.waveTop {
		width: 750upx; 
		height: 230upx;
	}
	.waveTop-white {
		width: 750upx;
		position: absolute;
		left: 0;
		top: 150upx;
	}
	.logo {
		position: absolute;
		left: 50%;
		transform: translate(-50%);
		top: 70upx;
		width: 150upx;
		height: 150upx;
		border: solid 10upx #fff;
		border-radius: 100%;
		z-index: 1;
	}
	.my-info-box {
		position: absolute;
		left: 0%;
		width: 100%;
		top: 240upx; 
		background: #fff;
		text-align: center;
	}
	.my-info-box .name {
		font-size: 36upx;
		text-align: center;
	}
	.my-info-box .sex {
		width: 36upx;
		height: 36upx;
		margin-left: 10upx;
	}
	.my-info-box .id-no {
		display: inline-block;
		background: #FFA921;
		padding: 0 20upx;
		color: #fff;
		font-size: 28upx;
		width: auto;
		border-radius: 30upx;
	}
	.my-info-box .section-2 view {
		width: 50%;
		margin-top: 40upx;
	}
	.my-info-box .section-2 text.line {
		display: inline-block;
		width: 2upx;
		height: 70upx;
		background: #EEE;
		margin-top: 30upx;
	}
	.my-info-box .section-2 view image:nth-of-type(1) {
		width: 50upx;
		height: 50upx;
	}
	.my-info-box .section-2 view image:nth-of-type(2) {
		width: 80upx;
		height: 65upx;
	}
	.my-info-box .section-2 text {
		font-size: 28upx;
		margin-top: 10upx;
	}
	.list-box {
		padding: 0 30upx;
		font-size: 32upx;
		margin-top: 50upx;
	}
	.list-box .list-item {
		border-bottom: solid 2upx #F8F7F7;
		height: 90upx;
	}
	.list-box .list-icon {
	   width: 36upx;
	   height: 30upx;
	   margin-right: 20upx;
	}
	.list-box .arrow-r {
		width: 30upx;
		height: 30upx;
	}
	.color-FD875A {
		color: #FD875A;
		font-size: 28upx;
		margin-right: 15upx;
	}
	.switch {
		width: 100upx;
	}
	.ques {
		position: absolute;
		right: 130upx;
		top: 0; 
		width: 20upx!important;
		height: 20upx!important;
	}
</style>
