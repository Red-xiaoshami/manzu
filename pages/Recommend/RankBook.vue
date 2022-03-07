<template>
	<view>
		<view class="BookRankList">
			<view class="TopBtnBox">
				<image class="RankTopBg" mode="widthFix" src="../../static/images/rtp.png"></image>
				<view class="SwitchRankType">
					<!-- <view @click="SwitchRankTypeFun('Home')" class="LableInfo IconHome"><image mode="widthFix" src="../../static/images/icon_Home.png"></image></view> -->
					<view @click="SwitchRankTypeFun(0)" class="LableInfo"><label :class="{'active':RankType==0}">人气榜</label></view>
					<view @click="SwitchRankTypeFun(1)" class="LableInfo"><label :class="{'active':RankType==1}">点击榜</label></view>
					<view @click="SwitchRankTypeFun(2)" class="LableInfo"><label :class="{'active':RankType==2}">订阅榜</label></view>
					<view @click="SwitchRankTypeFun(3)" class="LableInfo"><label :class="{'active':RankType==3}">购买榜</label></view>
					<view @click="SwitchRankTypeFun(4)" class="LableInfo"><label :class="{'active':RankType==4}">阅读榜</label></view>
					<view @click="SwitchRankTypeFun(5)" class="LableInfo"><label :class="{'active':RankType==5}">参与榜</label></view>
					<view @click="SwitchRankTypeFun(6)" class="LableInfo"><label :class="{'active':RankType==6}">打赏榜</label></view>
				</view>
				<!-- <view class="SwitchTimeRank">
					<label @click="SwitchTimeFun(1)" :class="IsTimeRank==1?'active':''">月</label>
					<label @click="SwitchTimeFun(2)" :class="IsTimeRank==2?'active':''">总</label>
				</view> -->
			</view>
			<view class="BookRakList">
				<label class="BookInfo" v-for="(Book,index) in BookRankList" :key="index" v-show="isShowList">
					<image class="RankBookUrl" mode="widthFix" :src="'http://img.ttnnr.com'+Book.cover_small"></image>
					<label class="Otherright">
						<view class="RankBookName">{{Book.title}}</view>
						<view class="RankBookUpdata">更新至{{Book.last_chapter_title}}</view>
						<image class="RankNumberOne" v-if="index==0" mode="widthFix" src="../../static/images/rtop1.png"></image>
						<image class="RankNumberTwo" v-if="index==1" mode="widthFix" src="../../static/images/rtop2.png"></image>
						<image class="RankNumberThree" v-if="index==2" mode="widthFix" src="../../static/images/rtop3.png"></image>
						<view class="BookOther">
							<label v-if="typeText&&typeText!=''" :key="TwoIndex" v-for="(typeText,TwoIndex) in Book.keyword">{{typeText}}</label>
							<label class="rightNumber">热度 {{Book.popularity}}万</label>
						</view>
					</label>
				</label>
			</view>
		</view>
		<Loading v-show="isShowLoading"></Loading>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				RankType:0,
				ChannelNo:'',
				BookRankList:[],
				IsTimeRank:1,
				mediaURL: this.$dev.mediaURL,
				isShowLoading: false,
				isShowList: false
			};
		},methods:{
			SwitchTimeFun(TimeNumber){
				let that=this;
				that.IsTimeRank=TimeNumber;
			},SwitchRankTypeFun(SwitchText){
				let that=this;
				if(SwitchText=='Home'){
					uni.switchTab({
						url:'Main'
					})
				}else{
					that.RankType=SwitchText;
					that.$nextTick(function(){
						that.GetBookRank();
					})
				}
			},GetBookRank(){
				let that=this;
				that.isShowLoading = true;
				that.isShowList = false;
				var Params={
					type:1,
					rankType:that.RankType,
					gender:that.ChannelNo
				}
				let url=that.$dev.baseURL+that.$api.rankMain;
				that.$axios.post(url,Params).then(response=>{
					that.isShowLoading = false;
					let RankList=response.data.result;
					for(var i=0;i<RankList.length;i++){
						RankList[i].popularity=(RankList[i].popularity/10000).toFixed(2)
						RankList[i].keyword=RankList[i].keyword.split(',');
					}
					that.BookRankList=RankList;
					that.isShowList = true;
				}).catch(Error=>{
					that.isShowList = true;
					that.isShowLoading = false;
				})
			}
		},mounted(){
			let that=this;
			that.ChannelNo=uni.getStorageSync("BookSex");
			that.GetBookRank();
		}
	}
</script>

<style>

</style>
