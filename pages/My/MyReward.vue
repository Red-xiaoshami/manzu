<template>
	<view class="box">
		<image class="infoBg" src="../../static/images/infoBg.png"></image>
		<image class="avatar" :src="imgUrl+userInfo.header_icon"></image>
		<view class="details">
			<view class="title">{{userInfo.nick_name}}</view>
			<view class="flex flex_center flex_start">
				<text>打赏漫画：{{total_row}} </text>
				<text>打赏值：{{reward_money}}</text>
			</view>
			<text class="arrow"></text>
		</view>
		<view class="main-title-details">主人打赏的漫画</view>
		<view class="node-data flex flex_center flex_just flex_column" v-if="noneData">
			<image class="giftNone" src="../../static/images/giftNone.png"></image>
			<view>谈钱伤感情，该打赏还是要打赏得~</view>
		</view>
		<view class="flex flex_center flex_start my-reward-box" v-else>
			<view class="flex flex_center flex_just flex_column my-reward-item" v-for="(item,index) in dataList" :key="index" @click="goDetails(item.book_id)">
				<image :src="imgUrl+item.cover_small"></image>
				<text>{{item.title}}</text>
				<view class="reward-money">{{item.money}}</view>
			</view>
		</view>
		<view class="flex flex_bettwen">
			<view class="flex flex_center flex_start l-content">
				<image class="loadMore" src="../../static/images/loadMore.png"></image>
				<text>猜你喜欢</text>
			</view>
			<view @click="getRandData" class="flex flex_center flex_start r-content">
				<text>再猜一下</text>
				<image class="loadMore" src="../../static/images/loadMore.png"></image>
			</view>
		</view>
		<view class="flex flex_center flex_start book-recommand">
			<view class="flex flex_center flex_just flex_column" v-for="(item,index) in rankDataList" :key="index" @click="goDetails(item.id)">
				<image :src="imgUrl+item.cover_small"></image>
				<text>{{item.title}}</text>
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
				rankDataList: [],
				imgUrl: this.$dev.mediaURL,
				userInfo: {},
				total_row: 0,
				reward_money: 0,
				noneData:false,
				isShowLoading: true
			};
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
			this.getData();
			this.getRandData();
		},
		methods: {
			goDetails: function(id) {
				uni.navigateTo({ 
					url: '../Recommend/BookDetails?bookid=' + id 
				});
			},
			getData: function() {
				let that = this
				uni.request({
					url: this.$dev.baseURL + this.$api.rewards,
					data: {
						page: this.pageNo,
						size: 20
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
							that.dataList = res.data.result.list;
						} else { 
							plus.nativeUI.toast(res.data.msg);
						}
						
						if(that.dataList.length<=0) {
							that.noneData = true;
						} else {
							that.noneData = false;
						}
						that.total_row = res.data.result.total_row;
						that.reward_money = res.data.result.reward_money;
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
			getRandData: function() {
				let that = this
				that.isShowLoading = true
				uni.request({
					url: this.$dev.baseURL + this.$api.randMain,
					data: {
						page: this.pageNo,
						size: 3
					},
					method: "POST", 
					dataType: "json",
					success: (res) => {
						console.log(JSON.stringify(res.data));
						uni.hideLoading();
						if(res.data.succ) {
							that.rankDataList = res.data.result;
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
	.box {
		position: relative;
	}
	.infoBg {
		width: 100%;
		height: 364upx;
	}
	.avatar {
		position: absolute;
		top: 60upx;
		left: 30upx;
		width: 130upx;
		height: 130upx;
		border-radius: 100%;
	}
	.details {
		position: absolute;
		top: 210upx;
		left: 30upx;
	}
	.details .title {
		font-size: 40upx;
		text-align: left;
		color: #fff;
	}
	.details view {
		text-align: left;
	}
	.details text {
		font-size: 30upx;
		text-align: left;
		color: #fff;
	}
	.details text:first-child {
		margin-right: 30upx;
	}
	.arrow {
		display: inline-block;
		 margin-left: 32upx;
		 margin-top: 10upx;
		 width: 0; 
		 height: 0;
		 border-width: 20upx;
		 border-bottom-width: 30upx;
		 border-style: solid;
		 border-color: transparent transparent #fff transparent;
	}
	.node-data view {
		color: #000;
		font-size: 32upx;
		margin-top: 30upx;
	}
	.node-data .giftNone {
		width: 263upx;
		height: 184upx;
	}
	.main-title-details {
		font-size: 32upx;
		text-align: left;
		padding-left: 30upx;
		margin: 10upx 0 100upx;
	}
	.l-content {
		margin: 60upx 0 50upx;
	}
	.loadMore {
		width: 36upx;
		height: 36upx;
		margin-right: 20upx;
		margin-left: 30upx;
	}
	.l-content text {
		font-size: 30upx;
		font-weight: 700;
		text-align: left;
	}
	.r-content text {
		font-size: 28upx;
		font-weight: 500;
		color: #CECECE;
		text-align: right;
	}
	.r-content image {
		width: 32upx;
		height: 32upx;
		margin-right: 30upx;
		margin-left: 10upx;
	}
	.book-recommand {
		padding-bottom: 100upx;
		padding-left: 32upx;
		flex-wrap: wrap;
		flex-direction: row;
	}
	.book-recommand view {
		margin-right: 30upx;
	}
	.book-recommand image {
	    width: 210upx;
		height: 297upx;
		border-radius: 8upx;
	}
	.book-recommand text {
		color: #333;
		font-size: 28upx;
		text-overflow: ellipsis;
		overflow: hidden;
		display: inline-block;
		white-space: nowrap;
		width: 210upx;
		margin-top: 6upx;
	}
	.my-reward-box {
		padding-left: 32upx;
		flex-wrap: wrap;
		flex-direction: row;
		margin-top: -80upx;
	}
	.my-reward-box .my-reward-item {
		position: relative;
		margin-right: 30upx;
		margin-top: 20px;
	}
	.reward-money {
		position: absolute;
		color: #fff;
		top: 240upx;
		font-size: 32upx;
	}
	.my-reward-box image {
		width: 210upx;
		height: 297upx;
		border-radius: 8upx;
	}
	.my-reward-box text {
		color: #000;
		font-size: 28upx;
		font-weight: 600;
		text-overflow: ellipsis;
		overflow: hidden;
		display: inline-block;
		white-space: nowrap;
		width: 210upx;
		margin-top: 6upx;
		text-align: center;
	}
</style>
