<template>
	<view class="bg">
		<view class="section-top">
			<image class="signTopBg" src="../../static/images/signTopBg.png"></image>
			<view class="top-box">
				<view class="flex flex_center flex_just total-grade">
					<image class="signMoney" src="../../static/images/signMoney.png"></image>
					<text>10</text>
				</view>
				<view class="tips-1">今日获得金币</view>
			</view>
		</view>
		
		<view class="section-2">
			<view class="flex flex_just flex_center box-1">
				<image class="signInfoBg" src="../../static/images/signInfoBg.png"></image>
				<text class="tips-2">连续签到获得更多金币</text>
			</view>
			<view class="flex flex_around flex_center sign-list">
				<view class="flex flex_center flex_just flex_column">
					<image class="giftDefault" src="../../static/images/gift-1.png"></image>
					<text class="day">10</text>
					<text class="title">1天</text>
				</view>
				<view class="flex flex_center flex_just flex_column">
					<image class="giftDefault" src="../../static/images/gift-1.png"></image>
					<text class="day">20</text>
					<text class="title">2天</text>
				</view>
				<view class="flex flex_center flex_just flex_column">
					<image class="giftDefault" src="../../static/images/giftDefault.png"></image>
					<text class="day-default">30</text>
					<text class="title">3天</text>
				</view>
				<view class="flex flex_center flex_just flex_column">
					<image class="giftDefault" src="../../static/images/giftDefault.png"></image>
					<text class="day-default">40</text>
					<text class="title">4天</text>
				</view>
				<view class="flex flex_center flex_just flex_column">
					<image class="giftDefault" src="../../static/images/giftDefault.png"></image>
					<text class="day-default">50</text>
					<text class="title">5天</text>
				</view>
				<view class="flex flex_center flex_just flex_column">
					<image class="giftDefault" src="../../static/images/giftDefault.png"></image>
					<text class="day-default">60</text>
					<text class="title">6天</text>
				</view>
				<view class="flex flex_center flex_just flex_column">
					<image class="giftDefault" src="../../static/images/giftDefault.png"></image>
					<text class="day-default">70</text>
					<text class="title">7天</text>
				</view>
			</view>
			<text class="line-1"></text>
			<view class="flex flex_center flex_bettwen sign-box">
				<view class="flex flex_center flex_just" style="width: ;">
					<image class="icon-sign" src="../../static/images/"></image>
					<view>您已累计签到<text style="color: #EF7F1A;">2天</text></view>
				</view>
				<text class="sign-btn" @click="toSign">已签到</text>
			</view>
		</view>
		
		<view class="section-3">
			<view class="main-title">每日任务</view>
			<view class="flex flex_bettwen flex_center task-box">
				<view class="flex flex_center flex_start">
					<image class="icon-sing" src="../../static/Icon/sing-icon_05.png"></image>
					<view class="flex flex_just flex_start flex_column task-content">
						<text class="task-title">阅读3本漫画</text>
						<text class="task-reward">金币+20</text>
					</view>
				</view>
				<text><text class="to-complete">去完成</text></text>
			</view>
			<view class="flex flex_bettwen flex_center task-box">
				<view class="flex flex_center flex_start">
					<image class="icon-sing" src="../../static/Icon/sing-icon_07.png"></image>
					<view class="flex flex_just flex_start flex_column task-content">
						<text class="task-title">邀请新用户加入</text>
						<text class="task-reward">每邀请一人金币+50</text>
					</view>
				</view>
				<text><text class="to-complete">去完成</text></text>
			</view>
		</view>
		
		<view class="section-3">
			<view class="main-title">新手任务</view>
			<view class="flex flex_bettwen flex_center task-box">
				<view class="flex flex_center flex_start">
					<image class="icon-sing" src="../../static/Icon/sing-icon_09.png"></image>
					<view class="flex flex_just flex_start flex_column task-content">
						<text class="task-title">给任意1部漫画点赞</text>
						<text class="task-reward">金币+20</text>
					</view>
				</view>
				<text><text class="to-complete" @click="toSign">去完成</text></text>
			</view>
			<view class="flex flex_bettwen flex_center task-box">
				<view class="flex flex_center flex_start">
					<image class="icon-sing" src="../../static/Icon/sing-icon_17.png"></image>
					<view class="flex flex_just flex_start flex_column task-content">
						<text class="task-title">收藏任意1部漫画</text>
						<text class="task-reward">金币+20</text>
					</view>
				</view>
				<text><text class="to-complete">去完成</text></text>
			</view>
			<view class="flex flex_bettwen flex_center task-box">
				<view class="flex flex_center flex_start">
					<image class="icon-sing" src="../../static/Icon/sing-icon_15.png"></image>
					<view class="flex flex_just flex_start flex_column task-content">
						<text class="task-title">第一次评论</text>
						<text class="task-reward">金币+20</text>
					</view>
				</view>
				<text><text class="to-complete">去完成</text></text>
			</view>
			<view class="flex flex_bettwen flex_center task-box">
				<view class="flex flex_center flex_start">
					<image class="icon-sing" src="../../static/Icon/sing-icon_18.png"></image>
					<view class="flex flex_just flex_start flex_column task-content">
						<text class="task-title">第一次打赏</text>
						<text class="task-reward">金币+20</text>
					</view>
				</view>
				<text><text class="to-complete">去完成</text></text>
			</view>
			<view class="flex flex_bettwen flex_center task-box">
				<view class="flex flex_center flex_start">
					<image class="icon-sing" src="../../static/Icon/sing-icon_21.png"></image>
					<view class="flex flex_just flex_start flex_column task-content">
						<text class="task-title">新手任务全部完成</text>
						<text class="task-reward">金币+20</text>
					</view>
				</view>
				<text><text class="to-complete">去完成</text></text>
			</view>
		</view>
		<view class="sign-success-box" v-show="isSignSucess">
			<view class="container flex flex_center flex_just flex_column">
				<image class="signSuccessBg" src="../../static/images/signSuccessBg.png"></image>
				<image @click="hideSignBox" class="closeBtn" src="../../static/images/closeBtn.png"></image>
				<view class="text-content flex flex_center flex_just flex_column">
					<text>签到成功</text>
					<text>恭喜获得<text style="color: #FFDA4E;">10</text>金币</text>
				</view>
			</view>
		</view>
		<Loading v-show="isShowLoading"></Loading>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				pageNo: 1,
				dataList: [],
				isSignSucess: false,
				isShowLoading: true
			};
		},
		mounted() {
			this.toSign();
		},
		methods: {
			toSign: function() {
				let that = this
				that.isShowLoading = true;
				uni.request({
					url: this.$dev.baseURL + this.$api.sign,
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
							that.isSignSucess = true;
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
			},
			hideSignBox: function() {
				this.isSignSucess = false;
			}
		}
	}
</script>

<style scoped="scoped">
	.sign-success-box {
		position: fixed;
		left: 0;
		top: 0;
		width: 100%;
		height: 100%;
		background-color: rgba(0,0,0,0.3);
		z-index: 100;
	}
	.sign-success-box .container {
		position: relative;
		top: 200upx;
	}
	.sign-success-box .text-content {
		position: absolute;
		left: 50%;
		transform: translateX(-50%);
		top: 20upx;
	}
	.sign-success-box .text-content text {
		color: #fff;
	}
	.sign-success-box .text-content text:nth-of-type(1) {
		font-size: 38upx;
	}
	.sign-success-box .text-content text:nth-of-type(2) {
		font-size: 36upx;
	}
	.signSuccessBg {
		width: 482upx;
		height: 525upx;
	}
	.closeBtn {
		width: 85upx;
		height: 85upx;
		margin-top: 40upx;
		z-index: 1000;
	}
.bg {
	width: 100%;
	height: 100%;
	background: #F3F2F2;
}
.section-top {
	display: block;
	position: relative;
}
.section-top image.signTopBg {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	z-index: 1;
}
.section-top .top-box {
	display: inline-block;
	position: relative;
	width: 100%;
	z-index: 2;
	text-align: center;
}
.section-top .top-box .total-grade {
	position: relative;
	margin-top: 80upx;
}
.section-top .top-box image.signMoney {
	width: 60upx;
	height: 53upx;
}
.section-top .top-box text {
	font-size: 54upx;
	color: #fff;
	font-weight: 700;
	margin-left: 30upx;
}
.tips-1 {
	display: inline-block;
	background-color: rgba(0,0,0,0.3) ;
	padding: 5upx 20upx;
	color: #fff;
	border-radius: 30upx;
	font-size: 32upx;
	margin-top: 10upx;
}
.section-2 {
	position: relative;
	background: #fff;
	border-radius: 20upx;
	z-index: 3;
	width: 95%;
	margin: 0 auto;
	text-align: center;
	padding-bottom: 50upx;
	margin-top: 90upx;
}
.section-2 .box-1 {
	position: relative;
	padding-top: 40upx;
}
.signInfoBg {
	width: 475upx;
	height: 30upx;
	margin: 0 auto;
}
.tips-2 {
	position: absolute;
	font-size: 32upx;
	/* top: 30upx; */
}
.giftDefault {
	width: 56upx;
	height: 56upx;
}
.sign-list {
	padding: 0 20upx;
	margin: 30upx 0;
}
.sign-list view {
	position: relative;
}
.sign-list view text.day {
	position: absolute;
	top: 14upx;
	font-size: 28upx;
	color: #fff;
}
.sign-list view text.day-default {
	position: absolute;
	top: 14upx;
	font-size: 28upx;
	color: #eee;
}
.sign-list view text.title {
	color: #888;
	font-size: 30upx;
	margin-top: 6upx;
}
.line-1 {
	display: inline-block;
	background: #EEE;
	height: 2upx;
	width: 70%;
	margin: 0 auto;
	margin-bottom: 30upx;
}
.icon-sign {
	width: 45upx;
	height: 45upx;
	margin-right: 10upx;
}
 .sign-box {
	 padding: 0 30upx;
 }
 .sign-box view,.sign-box text {
	 font-size: 34upx;
 }
 .sign-btn {
	 display: inline-block;
	 font-size: 36upx;
	 color: #fff;
	 padding: 4upx 16upx;
	 border-radius: 30upx;
	 background: #FD4E4D;
 }
 .section-3 {
 	position: relative;
 	background: #fff;
 	border-radius: 20upx;
 	z-index: 3;
 	width: 95%;
 	margin: 0 auto;
	margin-top: 20upx;
 	text-align: center;
 }
 .section-3 .main-title {
	 text-align: left;
	 padding: 40upx 20upx 20upx 20upx ;
	 font-size: 34upx;
	 color: #000;
 }
 .section-3:nth-last-of-type(1) {
	 margin-bottom: 100upx;
 }
 .task-box {
	 padding: 30upx 20upx;
	 border-bottom: solid 1px #E8E6E6;
 }
 .task-box:nth-last-of-type(1) {
	 border: none;
 }
 .task-box .to-complete {
 	display: inline-block;
 	font-size: 32upx;
 	color: #fff;
 	padding: 4upx 20upx;
 	border-radius: 30upx;
 	background: #FFC102;
 }
 .task-box .icon-sing {
 	width: 50upx;
 	height: 50upx;
 	margin-right: 30upx;
 	margin-left: 10upx;
 }
 .task-box .task-content {
   text-align: left;
 }
 .task-box .task-title {
	 color: #000;
	 font-size: 32upx;
 }
 .task-box .task-reward {
 	color: #999;
 	font-size: 30upx;
 }
</style>
