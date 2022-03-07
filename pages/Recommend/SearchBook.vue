<template>
	<view>
		<view :class="IsSearch?'SearchBox':'SearchBox Back0'">
			<view class='SearchBorder'>
				<view class="Icon_Search" v-if="!IsSearch"><image mode="widthFix" src="../../static/images/icon_Search.png"></image></view>
				<view class="Search_Input" :class="{'MaxLable':IsSearch}"><input type="text" @focus="ClearVal" @blur="AddVal" v-model="InputText" placeholder="搜索漫画名~作者~" /></view>
				<view class="Search_Btn"><button @click="SearchFun">搜索</button></view>
			</view>
		</view>
		<view class="SearchHis" v-if="!IsSearch">
			<label class="BoxTitle">最近查询<image mode="widthFix" @click="DelSearchHis" src="../../static/images/icon_Del.png"></image></label>
			<view class="SearchHisList">
				<label class="SearchName">测试</label>
			</view>
		</view>
		<view class="Search_BookList" v-if="IsSearch">
			<label class="BookInfo" v-for="(Search,index) in SearchList" :key="index" @click="GotoDetails(Search.id)">
				<image style="height: 220upx;" mode="widthFix" :src="mediaURL+Search.cover_small"></image>
				<view class="SearchListInfo">
					<view class="Search_BookName">{{Search.title}}</view>
					<view class="Search_BookType">{{Search.keyword}}</view>
					<view class="Search_BookUpdate">更至{{Search.last_chapter_title}}</view>
				</view>
			</label>
			<label v-if="SearchList.length<1" class="NullListTip">当前关键字查询暂无结果!</label>
		</view>
		<label class="LineBox" v-if="!IsSearch"></label>
		<view class="PopularBook">
			<label class="BoxTitle">热门查询<image mode="widthFix" @click="GetPopularList" src="../../static/images/icon_Reload.png"></image></label>
			<view class="PopularBookList">
				<label class="BookInfo" @click="GotoDetails(Popular.id)" v-for="(Popular,index) in PopularList" :key="index">
					<view class="BookUrl"><image mode="widthFix" :src="'http://img.ttnnr.com'+Popular.cover_small"></image></view>
					<view class="BookName">{{Popular.title}}</view>
				</label>
				
			</view>
		</view>
		<!-- <view class="PopularBookBox">
			<label class="PopularBookInfo">测试</label>
		</view> -->
		<Loading v-show="isShowLoading"></Loading>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				ChannelNo:'',
				SearchText:'',
				SumPage:0,
				SearchPageNum:'1',
				InputText:'',
				PopularList:[],
				SearchList:[],
				IsSearch:false,
				mediaURL: this.$dev.mediaURL,
				isShowLoading: false,
			};
		},onReachBottom(){
			console.log("1");
		},mounted(){
			let that=this;
			that.ChannelNo=uni.getStorageSync("BookSex");
			that.GetPopularList();
		},methods:{
			SearchFun(){
				let that=this;
				if(!that.SearchText&&!that.InputText){
					plus.nativeUI.toast("请输入搜索关键字!");
					return false;
				}
				that.SearchText=that.InputText?that.InputText:that.SearchText;
				that.GetSearchList();
			},GetPopularList(){
				let that=this;
				var Params={
					type:1,
					gender:that.ChannelNo,
					count:3
				}
				let url=that.$dev.baseURL+that.$api.randMain;
				that.$axios.post(url,Params).then(response=>{
					that.PopularList=response.data.result;
				}).catch(Error=>{
					
				})
			},GetSearchList(){
				let that=this;
				var Params={
					type:1,
					gender:that.ChannelNo,
					keyword:that.SearchText,
					pageNo:that.SearchPageNum,
					size:10
				}
				that.isShowLoading = true;
				let url=that.$dev.baseURL+that.$api.searchMain;
				that.$axios.post(url,Params).then(response=>{
					that.isShowLoading = false;
					that.IsSearch=true;
					let SearchData=response.data.result.list;
					for(var i=0;i<SearchData.length;i++){
						SearchData[i].keyword=SearchData[i].keyword.replace(/,/g,' ');
					}
					that.SumPage=response.data.result.total_row;
					that.SearchList=SearchData;
				}).catch(Error=>{
					that.isShowLoading = false;
				})
			},DelSearchHis(){
				
			},GotoDetails(Bookid){
				uni.navigateTo({
					url:'BookDetails?bookid='+Bookid
				})
			},ClearVal(){
				let that=this;
				that.InputText='';
			},AddVal(){
				let that=this;
				that.InputText=that.InputText?that.InputText:that.SearchText;
			}
		}
	}
</script>

<style>
@import "../../common/Style.css";
</style>
