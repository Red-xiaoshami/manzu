<template>
  <view>
    <view class="flex flex_bettwen money-box">
      <view @click="selectItems(index)" v-for="(item,index) in dataList" :key="index" :class="currentItem===index?'flex flex_center flex_just flex_column money-item active':'flex flex_center flex_just flex_column money-item'">
        <view>{{item.title}}</view> 
        <view>{{item.subtitle}}</view>
        <view>{{item.title}}</view>
        <image src="../../static/images/hot.png"></image>
      </view>
    </view>
    
    <view @click="selectItems(9)" :class="currentItem===9?'flex flex_center flex_just money-long active':'flex flex_center flex_just money-long'">
      <image class="icon" src="../../static/images/hot.png"></image>
      <image src="../../static/images/y-person-coin.png"></image>
      <view class="flex flex_center flex_just flex_column money-item" >
        <view>365.00元</view>
        <view>年费会员+10000金币</view>
        <view>漫画免费看 金币免费花</view>
      </view>
    </view>
    
    <button type="default" class="confirm-btn">确定充值</button>
    <view class="warting">
      <view class="title">充值说明</view>
      <view>1. 金币为虚拟货币，购买后不可退换，敬请谅解。</view>
      <view>2. 未成年人进行充值应得到家长同意，请自觉遵守。</view>
      <view>3. 客服工作时间： 9：00~17：30(周一至周五)</view>
    </view>
    <view class="my-info">
      <view>充值账户: {{userInfo.nick_name}}</view>
      <view>账户余额： <text>{{moneyData.money}}金币</text></view>
    </view>
		<Loading v-show="isShowLoading"></Loading>
  </view>
</template>

<script>
	export default {
		data() {
			return {
				currentItem: "",
				dataList: {},
				isShowLoading: true,
				moneyData: {},
				userInfo: {},
			};
		},
		created() {
			let that = this
			uni.getStorage({
				key: 'userInfo',
				success: function (res) {
					that.userInfo = res.data;
				}
			});
		},
		mounted() {
			this.getData();
			this.getMoney();
		},
		onBackPress(options) {
			
		},
    methods: {
      selectItems(id) {
        this.currentItem = id;
      },
			getData: function() {
				let that = this
				uni.request({
					url: this.$dev.baseURL + this.$api.rechargeList,
					data: {
					},
					method: "POST", 
					dataType: "json",
					success: (res) => {
						console.log(JSON.stringify(res.data));
						uni.hideLoading();
						if(!res.data.succ) {
							plus.nativeUI.toast(res.data.msg);
							return;
						}
						that.dataList = res.data.result;
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
			getLastOrderInfo: function() {
				let that = this
				uni.request({
					url: this.$dev.baseURL + this.$api.getLastOrderInfo,
					data: {
					},
					method: "GET", 
					dataType: "json",
					success: (res) => {
						console.log(JSON.stringify(res.data));
						if(res.data.succ) {
							that.moneyData = res.data.result;
						} else { 
							plus.nativeUI.toast(res.data.msg);
						}
						
						if (data && data.succ) {
							var paytype = '支付宝';
							if (data.result.pay_code < 20)
								paytype = '微信';
								window.dialog.alert({
									title : "最近" + paytype + "订单",
									msg : '您有一个最近订单: ' + data.result.pay_goods,
									buttons : [ '直接刷新账户', '查看支付结果' ]
								}, function(ret) {
									if (ret.buttonIndex == 1) {
										$.get('/pay/clearLastOrderInfo');
										window.location.reload();
									} else if (ret.buttonIndex == 2) {
										window.location.href = '/pay/orderResult?order_no=' + data.result.order_no;
									}
								});
						}
					},
					fail: (res) => {
						console.log(JSON.stringify(res));
					},
					complete:() => {
					}
				});
			}
    }
	}
</script>

<style>
.money-box {
  margin-top: 30upx;
  padding: 0 40upx;
  flex-wrap: wrap;
  flex-direction: row;
}
.money-box .money-item {
  border: solid 1px #eee;
  border-radius: 8upx; 
  width: 48%;
  padding: 20upx 0;
  margin-top: 15upx;
}
.money-box .money-item image {
  display: none;
}
.active {
  position: relative;
  border: solid 1px #F4C627!important;
  background: #FFFAF2;
}
.active view:nth-of-type(1),.money-box .active view:nth-of-type(3){
  color: #EF7F1A!important;
}
.money-box .active image {
  display: inline-block;
  width: 60upx;
  height: 60upx;
  position: absolute;
  left: 0;
  top: 0;
}
.money-box .money-item view:nth-of-type(1) {
  color: #000000;
  font-size: 38upx;
}
.money-box .money-item view:nth-of-type(2) {
  color: #999;
  font-size: 30upx;
}
.money-box .money-item view:nth-of-type(3) {
  color: #EF7F1A;
  font-size: 32upx;
}
.money-long {
  position: relative;
  width: 88%;
  height: 220upx;
  border: solid 1px #eee;
  border-radius: 8upx;
  margin-top: 30upx;
  margin-left: 40upx;
}
.money-long image {
  width: 120upx;
  height: 120upx;
  margin-right: 60upx;
}
.money-long  image.icon{
  display: none;
}
.active image.icon{
  display: inline-block;
  width: 60upx;
  height: 60upx;
  position: absolute;
  left: 0;
  top: 0;
}
.money-long .money-item view:nth-of-type(1) {
  color: #000000;
  font-size: 38upx;
}
.money-long .money-item view:nth-of-type(2) {
  color: #999;
  font-size: 30upx;
}
.money-long .money-item view:nth-of-type(3) {
  color: #EF7F1A;
  font-size: 32upx;
}
.confirm-btn {
  color: #000;
  width: 88%;
  display: inherit;
  margin: 0 auto;
  background: #FBE23B!important;
  border-color: #FBE23B!important;
  outline: none!important;
  border-radius: 50upx;
  margin-top: 100upx;
}
.warting {
  display: inherit;
  margin: 0 auto;
  color: #999;
  font-size: 32upx;
  width: 88%;
}
.warting view {
	margin: 10upx 0;
}
.warting .title {
  margin: 50upx 0 30upx;
  color: #333;
}
.my-info {
   display: inherit;
   margin: 0 auto;
   color: #333;
   font-size: 34upx;
   width: 88%;
   margin: 30upx auto 100upx auto;
 }
 .my-info text {
   color: #E40101;
 }
</style>
