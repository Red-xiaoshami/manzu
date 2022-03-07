<template>
	<div>
    <view v-show="!userInfo.is_qq_bind" @click="bindAccount('qq')" class="flex flex_center flex_bettwen list-box">
      <view class="flex flex_center flex_start">
        <image src="../../static/images/icon_qq0.png"></image>
        <text>qq绑定</text>
      </view>
      <image class="arrow" src="../../static/images/icon_arrow_r.png"></image>
    </view>
    <view v-show="!userInfo.is_wx_bind" @click="bindAccount('wechat')" class="flex flex_center flex_bettwen list-box">
      <view class="flex flex_center flex_start">
        <image src="../../static/images/icon_wx0.png"></image>
        <text>微信绑定</text>
      </view>
      <image class="arrow" src="../../static/images/icon_arrow_r.png"></image>
    </view>
    <view v-show="!userInfo.is_mobile_bind" @click="bindAccount('mobile')" class="flex flex_center flex_bettwen list-box">
      <view class="flex flex_center flex_start">
        <image src="../../static/images/icon_mobile0.png"></image>
        <text>手机号绑定</text>
      </view>
      <image class="arrow" src="../../static/images/icon_arrow_r.png"></image>
    </view>
  </div>
</template>


<script>
	export default {
		data() {
			return {
				userInfo: {},
			};
		},
		onShow() {
			// this.getMoney();
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
      
		},
		methods: {
      bindAccount(data) {
        console.log(data)
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
  .list-box {
    height: 110upx;
    border-bottom: solid #f0f0f0 1px;
    padding-left: 30upx;
    box-sizing: border-box;
  }
  .list-box image {
    width: 60upx;
    height: 60upx;
    margin-right: 30upx;
  }
  .list-box text {
    color: #000;
    font-size: 32upx;
  }
  .arrow {
    width: 40upx!important;
    height: 40upx!important;
  }
</style>
