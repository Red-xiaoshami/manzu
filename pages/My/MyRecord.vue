<template>
	<view>
			<view class="flex flex_around tabs_box">
				<view @click="selectItems(0)" class="flex flex_center flex_just flex_column">
					<text>充值记录</text>
					<text :class="currentTabs==0?'under_line':'no_under_line'"></text>
				</view>
				<view @click="selectItems(1)" class="flex flex_center flex_just flex_column">
					<text>消费记录</text>
					<text :class="currentTabs==1?'under_line':'no_under_line'"></text>
				</view>
			</view> 
			<view v-show="noneData" class="none-data flex flex_column flex_center flex_just">
				<image src="../../static/images/no-info.png"></image>
				<text>{{noneDataText}}</text> 
			</view>
			<!-- <uni-load-more status="loading"></uni-load-more> -->
			<view class="list-box">
				<view class="flex flex_center flex_bettwen list-item" v-show="currentTabs==0" v-for="(item,index) in rechargeDataList" :key="index">
					<view class="flex flex_center flex_start content_l"> 
						<text class="yellow-point"></text>
						<view class="flex flex_column flex_start"> 
							<view>{{item.title + item.subtitle}}</view>
							<view>{{item.add_time}}</view>
						</view>
					</view>
					<view class="flex flex_column content_r">
						<view>+{{item.coins_buy}}</view>
						<view>￥{{item.order_amt}}</view>
					</view>
				</view>
				<view class="flex flex_center flex_bettwen list-item" v-show="currentTabs==1" v-for="(item,index) in consumesDataList" :key="index">
					<view class="flex flex_center flex_start content_l"> 
						<text class="yellow-point"></text>
						<view class="flex flex_column flex_start"> 
							<view>{{item.title}}</view>
							<view>{{item.pay_time}}</view>
						</view>
					</view>
					<view class="flex flex_column content_r">
						<view>-{{item.price}}</view>
						<view>&nbsp;</view>
					</view>
				</view>
			</view>
			<uni-load-more :contentText="loadingText"></uni-load-more>
		<Loading v-show="isShowLoading"></Loading>
	</view>
</template>

<script>
	import {uniLoadMore} from "@dcloudio/uni-ui"
	export default {
		data() {
			return {
				currentTabs: 0,
				noneData: false,
				noneDataText: "还没有充值记录哦/(ㄒoㄒ)/",
				pageNo: 1,
				rechargeDataList: [],
				consumesDataList: [],
				isShowLoading: true,
				loadingText: {
					contentdown: '上拉加载更多',
					contentrefresh: '正在加载...',
					contentnomore: '没有更多数据了'
				},
			};
		},
		components: {uniLoadMore},
		mounted() {
			this.getRechargeData();
		},
		onPullDownRefresh() {
			console.log('refresh');
			this.pageNo = 1;
			
			if(this.currentTabs==0) {
				this.getRechargeData();
			} else {
				this.getConsumesData();
			}
		},
		onReachBottom() {
			console.log('refresh');
this.isShowLoading = true;			
			if(this.currentTabs==0) {
				this.getRechargeData();
			} else {
				this.getConsumesData();
			}
		},
		methods: {
			selectItems(id) {
				this.isShowLoading = true;
				this.currentTabs = id;
				this.pageNo = 1;
				if(id==0) {
					this.getRechargeData();
					this.noneDataText = "还没有充值记录哦/(ㄒoㄒ)/";
				} else {
					this.getConsumesData();
					this.noneDataText = "还没有消费记录哦/(ㄒoㄒ)/";
				}
			},
			loadMore: function() {
				if(this.currentTabs==0) {
					this.getRechargeData();
				} else {
					this.getConsumesData();
				}
			},
			getConsumesData: function() {
				let that = this
				uni.request({
					url: this.$dev.baseURL + this.$api.consumes,
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
							uni.navigateTo({ 
								url: 'Login' 
							});
							return;
						}
						
						if(!res.data.succ) {
							plus.nativeUI.toast(res.data.msg);
							return;
						}
						
						if(this.pageNo !=1) {
							that.consumesDataList = that.consumesDataList.concat(res.data.result.list);
						} else {
							that.consumesDataList = res.data.result.list;
						}
						
						if(that.consumesDataList.length <= 0) {
							that.noneData = true;
						} else {
						    that.noneData = false;
						}
						
						that.pageNo = that.pageNo + 1;
					},
					fail: (res) => {
						uni.hideLoading()
						console.log(JSON.stringify(res));
					},
					complete:() => {
						uni.stopPullDownRefresh();
						that.isShowLoading = false;
					}
				});
			},
			getRechargeData: function() {
				let that = this
				uni.request({
					url: this.$dev.baseURL + this.$api.ordersList,
					data: {
						page: this.pageNo,
						size: 20
					},
					method: "POST", 
					dataType: "json",
					success: (res) => {
						console.log(JSON.stringify(res.data));
						if(res.data.code == "-11") {
							uni.navigateTo({ 
								url: 'Login' 
							});
						}
						if(!res.data.succ) {
							plus.nativeUI.toast(res.data.msg);
							return;
						}
						console.log(this.pageNo)
						if(this.pageNo !=1) {
							that.rechargeDataList = that.rechargeDataList.concat(res.data.result.list);
						} else {
							that.rechargeDataList = res.data.result.list;
						}
						
						if(that.rechargeDataList.length <= 0) {
							that.noneData = true;
						} else {
						    that.noneData = false;
						}
						
						that.pageNo = that.pageNo + 1;
					},
					fail: (res) => {
						console.log(JSON.stringify(res));
					},
					complete:() => {
						uni.stopPullDownRefresh();
						uni.hideLoading();
						that.isShowLoading = false;
					}
				});
			},
		}
	}
</script>

<style>
.tabs_box text {
	font-size: 30upx;
	margin-top: 40upx;
}
.tabs_box text.under_line {
	display: inline-block;
	background: #FFD321;
	width: 60upx;
	height: 6upx;
	margin-top: 10upx;
}
.tabs_box text.no_under_line {
	display: inline-block;
	background: #FFF;
	width: 60upx;
	height: 6upx;
	margin-top: 10upx;
}
.none-data {
	margin-top: 120upx;
}
.none-data image {
	width: 320upx;
	height: 320upx;
	margin-bottom: 30upx;
}
.none-data text {
	color: #999;
	font-size: 30upx;
}
.list-box {
	padding: 0 30upx;
	margin-top: 30upx;
}
.list-item {
	padding: 10upx 0;
}
.list-item .content_l view view {
	line-height: 50upx;
}
.list-item .content_l view view:first-child {
	font-size: 32upx;
}
.list-item .content_l view view:last-child {
	font-size: 28upx;
	color: #ACACAC;
}
.list-item .content_r view view {
	line-height: 70upx;
	text-align: right;
}
.list-item .content_r view {
	text-align: right;
}
.list-item .content_r view:first-child {
	font-size: 32upx;
	color: #E67A7A;
}
.list-item .content_r view:last-child {
	font-size: 28upx;
	color: #ACACAC;
}
.yellow-point {
	display: inline-block;
	width: 20upx;
	height: 20upx;
	border-radius: 100%;
	background-color: #FDCB44;
	margin-right: 30upx;
}
</style>
