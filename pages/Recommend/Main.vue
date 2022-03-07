<template>
	<view v-show="isShowList">
		<view class="SwiperBox">
			<swiper indicator-dots="true" autoplay="false" circular="false" indicator-active-color="#FFFFFF" :interval="swiperTime">
				<swiper-item v-for="(UrlInfo,index) in SwiperUrlList" :key="index">
					<img class="SwiperImg" :src="mediaURL+UrlInfo.img_url" />
				</swiper-item>
			</swiper>
			<view class="ChannelBox"><image mode='widthFix' @click="SwirchChannel" :src="ChannelNo=='1'?'../../static/images/home-boy-new.png':'../../static/images/home-girl-new.png'"></image></view>
			<view class="BannerBox"><image src="../../static/images/home-banner-bottom.png"></image></view>
			<view class="SearchBtn"><image mode='widthFix' @click="GotoSearch" src="../../static/images/search.png"></image></view>
		</view>
		<view class="ChannelPanel" v-show="ShowChannelPanel"><image mode='widthFix' :src="ChannelNo=='1'?'../../static/images/y-sex-popup-man.png':'../../static/images/y-sex-popup-girl.png'"></image></view>
		<view class="BookTypeList">
			<view class="TypeBox"><image @click="GotoOneView" mode='widthFix' src="../../static/images/home-menu-01.png" ></image></view>
			<view class="TypeBox"><image @click="GotoBookRank" mode='widthFix' src="../../static/images/home-menu-02.png" ></image></view>
			<view class="TypeBox"><image @click="GotoMoneyRank" mode='widthFix' src="../../static/images/home-menu-03.png" ></image></view>
		</view>
		<view class="Listbox" v-for="(BookInfo,index) in MainBookList" :key="index" >
			<view>
				<image class="TitleUrl" src="../../static/images/y-title-boximg.png"></image>
				<view class="TitleText" style="color: #000;">{{BookInfo.title}}</view>
				<image class="TitleUrl" src="../../static/images/y-title-boximg.png"></image>
			</view>
			<view v-if="BookInfo.type=='1'" class="List">
				<label @click="GotoDetails(Book.id)" class='LableBox' v-for="(Book,Twoindex) in BookInfo.data" :key="Twoindex" >
					<view class="BookUrl"><image mode='widthFix' :src="mediaURL+Book.cover_small"></image></view>
					<view class="BookName">{{Book.title}}</view>
					<view class="Booktip">{{Book.memo}}</view>
				</label>
			</view>
			<view v-if="BookInfo.type=='2'" class="List ListTwo">
				<label @click="GotoDetails(BookInfo.data[0].id)" class="MaxLableBox">
					<view class="BookUrl"><image mode="widthFix" :src="mediaURL+BookInfo.data[0].cover_small"></image></view>
					<view class="DivLable">{{BookInfo.data[0].title}}<view class='RightInfo'><image mode='widthFix' src="../../static/images/y-bookhot-img.png"></image>{{BookInfo.data[0].popularity/10000}}万</view></view>
				</label>
				<label @click="GotoDetails(Book.id)" class="LineLable" v-if="Twoindex!=0" v-for="(Book,Twoindex) in BookInfo.data" :key="Twoindex" >
					<view class="BookUrl"><image mode='widthFix' :src="mediaURL+Book.cover_small"></image></view>
					<view class="LinerightInfo">
						<view class="BookName">{{Book.title}}</view>
						<view class="likeNumber">{{Book.popularity/10000}}万人喜欢</view>
						<view class="Booktip">{{Book.memo}}</view>
					</view>
				</label>
			</view>
			<view v-if="BookInfo.type=='3'" class="List">
				<label class="MaxLableBox" @click="GotoDetails(BookInfo.data[0].id)">
					<view class="BookUrl"><image mode="widthFix" :src="mediaURL+BookInfo.data[0].cover_small"></image></view>
					<view class="DivLable">{{BookInfo.data[0].title}}<view class='RightInfo'><image mode='widthFix' src="../../static/images/y-bookhot-img.png"></image>{{BookInfo.data[0].popularity/10000}}万</view></view>
				</label>
				<label @click="GotoDetails(Book.id)" class="LableBox" v-if="Twoindex!=0" v-for="(Book,Twoindex) in BookInfo.data" :key="Twoindex" >
					<view class="BookUrl"><image mode='widthFix' :src="mediaURL+Book.cover_small"></image></view>
					<view class="BookName">{{Book.title}}</view>
					<view class="Booktip">{{Book.memo}}</view>
				</label>
			</view>
			<view v-if="BookInfo.type=='4'" class="List">
				<label class="MMaxLableBox">
					<view class='BookInfo' @click="GotoDetails(Book.id)" v-if="Twoindex<3" v-for="(Book,Twoindex) in BookInfo.data" :key="Twoindex" >
						<image mode='widthFix' :src="mediaURL+Book.cover_small"></image>
						<view class="BookName">{{Book.title}}</view>
						<view class="Booktip">{{Book.memo}}</view>
					</view>
				</label>
				<label class="LableBox" @click="GotoDetails(Book.id)" v-if="Twoindex>=3&&Twoindex<5" v-for="(Book,Twoindex) in BookInfo.data" :key="Twoindex" >
					<view class="BookUrl"><image mode='widthFix' :src="mediaURL+Book.cover_small"></image></view>
					<view class="BookName">{{Book.title}}</view>
					<view class="Booktip">{{Book.memo}}</view>
				</label>
			</view>
		</view>
		<Loading v-show="isShowLoading"></Loading>
	</view>
</template>

<script>
	export default {
		name:'Main',
		data() {
			return {
				SwiperUrlList:[],
				MainBookList:[],
				ChannelNo:1,
				ShowChannelPanel:false,
				swiperTime:'10000000000000',
				isShowList: false,
				mediaURL: this.$dev.mediaURL,
				isShowLoading: true,
			};
		},methods:{
			GetManHuaData(){
				let that=this;
				that.isShowLoading = true;
				var Params={
					type:1,
					gender:that.ChannelNo
				}
				let url=this.$dev.baseURL+this.$api.banners;
				that.$axios.post(url,Params).then(response=>{
					that.isShowLoading = false;
					that.SwiperUrlList=response.data.result;
				}).catch(Error=>{
					that.isShowLoading = false;
				})
			},GetMainBookList(){
				let that=this;
				that.isShowLoading = true;
				var Params={
					type:1,
					gender:that.ChannelNo
				}
				let url=that.$dev.baseURL+that.$api.recommendMain;
				that.$axios.post(url,Params).then(response=>{
					that.isShowLoading = false;
					that.isShowList = true;
					that.MainBookList=response.data.result;
				}).catch(Error=>{
					that.isShowLoading = false;
					that.isShowList = true;
				})
			},SwirchChannel(){
				let that=this;
				this.ShowChannelPanel='true';
				setTimeout(function(){
					that.ChannelNo=that.ChannelNo=='1'?0:1;
					that.ShowChannelPanel=false;
					that.GetManHuaData();
					that.GetMainBookList();
					uni.setStorageSync("BookSex",that.ChannelNo)
				},2000)
			},GotoSearch(){
				uni.navigateTo({
					url:'SearchBook'
				})
			},GotoMoneyRank(){
				uni.navigateTo({
					url:'MoneyRank'
				})
			},GotoBookRank(){
				uni.navigateTo({
					url:'RankBook'
				})
			},GotoDetails(id){
				uni.navigateTo({
					url:'BookDetails?bookid='+id
				})
			},GotoOneView(){
				uni.navigateTo({
					url:'../My/WelfareCenter'
				})
			}
		},mounted() {
			let that=this;
			that.GetManHuaData();
			uni.setStorageSync("BookSex",1)
			that.GetMainBookList();
		},filters:{
		}
	}
</script>

<style>
@import "../../common/Style.css";
</style>
