<template>
	<view>
		<view class="MoneyRankBox">
			<image class="RankBgImg" mode="widthFix" src="../../static/images/rank-bg1.png"></image>
			<view class="RankInfoList">
				<view class="RankList">
					<view class="RankTopList">
						<view @click="GotoRewardInfo()" v-if="index==1" :key="index" v-for="(Money,index) in RankList.list" class="TopRankInfoTwo">
							<view class="RankUrlMaxBox">
								<view class="UserUrlBox">
									<label class="RankUrlBox"><image class="RankUrl" mode="widthFix" src="../../static/images/Icon_RankTwo.png"></image></label>
									<view class="TopRankUserUrl"><image mode="widthFix" :src="Money.header_icon"></image></view>
								</view>
							</view>
							<view class="TopRankName">{{Money.nick_name}}</view>
							<view class="UserRankInfo">{{Money.money}}<image mode="widthFix" src="../../static/images/y-tuhao-gogold.png"></image></view>
						</view>
						<view @click="GotoRewardInfo()" v-if="index==0" :key="index" v-for="(Money,index) in RankList.list" class="TopRankInfoOne">
							<view class="RankUrlMaxBox">
								<view class="UserUrlBox">
									<label class="RankUrlBox"><image class="RankUrl" mode="widthFix" src="../../static/images/Icon_RankOne.png"></image></label>
									<view class="TopRankUserUrl"><image mode="widthFix" :src="Money.header_icon"></image></view>
								</view>
							</view>
							<view class="TopRankName">{{Money.nick_name}}</view>
							<view class="UserRankInfo">{{Money.money}}<image mode="widthFix" src="../../static/images/y-tuhao-gogold.png"></image></view>
						</view>
						<view @click="GotoRewardInfo()" v-if="index==2" :key="index" v-for="(Money,index) in RankList.list" class="TopRankInfoThree">
							<view class="RankUrlMaxBox">
								<view class="UserUrlBox">
									<label class="RankUrlBox"><image class="RankUrl" mode="widthFix" src="../../static/images/Icon_RankThree.png"></image></label>
									<view class="TopRankUserUrl"><image mode="widthFix" :src="Money.header_icon"></image></view>
								</view>
							</view>
							<view class="TopRankName">{{Money.nick_name}}</view>
							<view class="UserRankInfo">{{Money.money}}<image mode="widthFix" src="../../static/images/y-tuhao-gogold.png"></image></view>
						</view>
					</view>
					<view class="RankUserInfo" v-if="index!=0&&index!=1&&index!=2" :key="index" v-for="(Money,index) in RankList.list">
						<label class="RankNumber">{{index+1}}</label>
						<view class="RankUserUrl"><image mode="widthFix" :src="Money.header_icon"></image></view>
						<label class="RankUserName">{{Money.nick_name}}</label>
						<label class="RankMoney">
							<image class="MoneyIcon" mode="widthFix" src="../../static/images/y-tuhao-gogold.png"></image>
							<label class="MoneyNum">{{Money.money}}</label>
						</label>
					</view>
				</view>
			</view>
		</view>
		<view class="ThisUserRankInfo">
			<label class="UserRank">
				<image mode="widthFix" :src="'http://img.ttnnr.com'+userInfo.header_icon"></image>
				<view class="UserName">{{userInfo.nick_name}}</view>
				<view class="OtherRight">
					<label class="LableTip">打赏</label>
					<label class="LableMoney">{{UserRewardNum}}</label>
					<image class="MoneyIcon" mode="widthFix" src="../../static/images/y-tuhao-gogold.png"></image>
					<image class="Iconright" mode="widthFix" src="../../static/images/icon_right.png"></image>
				</view>
			</label>
		</view>
	</view>
</template>
<script>
	export default {
		data() {
			return {
				ChannelNo:'',
				UserRewardNum:'',
				RankList:[],
				userInfo:{}
			};
		},methods:{
			GotoRewardInfo(){
				uni.navigateTo({
					url:'UserReward'
				})
			},GetMoneyRankList(){
				let that=this;
				var Params={}
				let url=this.$dev.baseURL+this.$api.MoneyRank;
				that.$axios.post(url,Params).then(response=>{
					console.log(JSON.stringify(response.data))
					let RankUserList=JSON.parse(JSON.stringify(response.data.result.list));
					for(var i=0;i<RankUserList.length;i++){
						console.log((RankUserList[i].header_icon).indexOf('http'));
						if((RankUserList[i].header_icon).indexOf('http')=='-1'){
							RankUserList[i].header_icon='http://img.ttnnr.com'+RankUserList[i].header_icon
						}
					}
					response.data.result.list=RankUserList;
					that.RankList=response.data.result;
				}).catch(Error=>{
					
				})
			},GetMyReward(){
				let that=this;
				var Params={}
				let url=this.$dev.baseURL+this.$api.MyReward;
				that.$axios.post(url,Params).then(response=>{
					console.log(JSON.stringify(response.data))
					that.UserRewardNum=response.data.result;
				}).catch(Error=>{
					
				})
			}
		},mounted(){
			let that=this;
			that.ChannelNo=uni.getStorageSync("BookSex");
			that.GetMoneyRankList();
			that.GetMyReward();
			uni.getStorage({
				key: 'userInfo',
				success: function (res) {
					that.userInfo = res.data;
				}
			});
		}
	}
</script>

<style>

</style>
