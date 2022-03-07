<template>
	<view class="bg">
		<image class="yearvip-top" src="../../static/images/yearvip-top.png"></image>
    <view class="flex flex_just flex_center flex_column list-item">
      <view class="flex flex_bettwen flex_center">
        <image class="icon-year" src="../../static/images/icon_year_fee_1.png"></image>
        <view class="flex flex_start  flex_column content">
          <view>漫画免费看</view>
          <view>站内漫画统统免费看！如丝般顺滑，再也不用担心余额不足了。</view>
        </view>
      </view>
    </view>
    
    <view class="flex flex_just flex_center flex_column list-item">
      <view class="flex flex_bettwen flex_center">
        <image class="icon-year" src="../../static/images/icon_year_fee_2.png"></image>
        <view class="flex flex_start  flex_column content">
          <view>超级金币礼包</view>
          <view>购买即送10000金币！任性打赏漫画小主，收下围观群众的膝盖。</view>
        </view>
      </view>
    </view>
    
    <view class="flex flex_just flex_center flex_column list-item">
      <view class="flex flex_bettwen flex_center">
        <image class="icon-year" src="../../static/images/icon_year_fee_3.png"></image>
        <view class="flex flex_start  flex_column content">
          <view>专属客服</view>
          <view>即将开通VIP专属客服！制服小姐姐等你来撩哟！么么哒~</view>
        </view>
      </view>
    </view>
    
    <view class="flex flex_just flex_center flex_column list-item">
      <view class="flex flex_bettwen flex_center">
        <image class="icon-year" src="../../static/images/icon_year_fee_4.png"></image>
        <view class="flex flex_start  flex_column content">
          <view>敬请期待</view>
          <view>后续还有更多惊喜特权加入！没时间解释了，快点上车吧！</view>
        </view>
      </view>
    </view>
    
    <view class="btn">购买/续费年费VIP</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			};
		}
	}
</script>

<style>
.bg {
  background-image: linear-gradient(
  	#EDDFCE 0%,
  	#fff 70%);
    height: 100%;
    padding-top: 100upx;
}
.yearvip-top {
  display: inherit;
  margin: 0 auto;
  width: 418upx;
  height: 180upx;
}
.icon-year {
  width: 110upx;
  height: 110upx;
  margin-left: 50upx;
  margin-top: -30upx;
}
.content {
  width: 70%;
  margin-left: 50upx;
  border-bottom: solid 1px #eee;
  padding-bottom: 30upx;
  padding-right: 30upx;
}
.list-item:nth-of-type(4) .content {
	border: none!important;
}
.content view:nth-of-type(1) {
  font-size: 34upx;
  color: #C07100;
  font-weight: 500;
}
.content view:nth-of-type(2) {
  font-size: 30upx;
  color: #999;
}
.list-item {
  margin-bottom: 30upx;
}
.list-item:nth-of-type(1) {
  margin-top: 100upx;
}
.btn {
  font-size: 36upx;
  width: 78%;
  height: 100upx;
  line-height: 100upx; 
  background: #EA940D;
  border-radius: 15upx;
  text-align: center;
  color: #fff;
  display: inherit;
  margin: 50upx auto;
}
</style>
