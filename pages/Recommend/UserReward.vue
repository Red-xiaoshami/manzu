<template>
	<view>
		<view class="UserInfo">
			<image class="RewardUserBg" mode="widthFix" src="../../static/images/infoBg.png"></image>
			<image class="RewardUserUrl" mode="widthFix" src="../../static/img/132.jpg"></image>
			<view class="RewardUserName">某A</view>
			<view class="RewardNumber">
				<label class="">打赏漫画：1</label>
				<label class="">打赏值：50899</label>
			</view>
			<view class="ArrowBox"></view>
		</view>
		<view class="ListTitleTip">主人打赏的漫画</view>
		<view class="NullRewardList">
			<image mode="widthFix" src="../../static/images/giftNone.png"></image>
			<label>谈钱伤感情,该打赏还是要打赏的~</label>
		</view>
		<view v-if="RewardList.length>0" class="RewardBookList">
			<label class="BookInfo">
				<image mode="widthFix" src="../../static/img/1547112348_我的男友漏气了%20竖.jpg"></image>
				<view class="RewardBookName">我的男友漏气了</view>
			</label>
			<label class="BookInfo">
				<image mode="widthFix" src="../../static/img/1547112348_我的男友漏气了%20竖.jpg"></image>
				<view class="RewardBookName">我的男友漏气了</view>
			</label>
			<label class="BookInfo">
				<image mode="widthFix" src="../../static/img/1547112348_我的男友漏气了%20竖.jpg"></image>
				<view class="RewardBookName">我的男友漏气了</view>
			</label>
			<label class="BookInfo">
				<image mode="widthFix" src="../../static/img/1547112348_我的男友漏气了%20竖.jpg"></image>
				<view class="RewardBookName">我的男友漏气了</view>
			</label>
		</view>
		<view class="IsLikeListTitle">
			<image class="IconLike" mode="widthFix" src="../../static/images/loadMore.png"></image>
			<view class="TipText">猜你喜欢</view>
			<label class="ListrigthBtn">
				<view>再试一下</view>
				<image mode="widthFix" src="../../static/images/icon_Reload.png"></image>
			</label>
		</view>
		<view class="IsLikeList">
			<label class="BookInfo">
				<image mode="widthFix" src="../../static/img/1547112348_我的男友漏气了%20竖.jpg"></image>
				<view class="RewardBookName">我的男友漏气了</view>
			</label>
			<label class="BookInfo">
				<image mode="widthFix" src="../../static/img/1547112348_我的男友漏气了%20竖.jpg"></image>
				<view class="RewardBookName">我的男友漏气了</view>
			</label>
			<label class="BookInfo">
				<image mode="widthFix" src="../../static/img/1547112348_我的男友漏气了%20竖.jpg"></image>
				<view class="RewardBookName">我的男友漏气了</view>
			</label>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				RewardList:[]
			};
		},mounted(){
			
		},methods:{
			
		}
	}
</script>

<style>

</style>
