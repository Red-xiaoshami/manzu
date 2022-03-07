<template>
	<view class="MaxContentBox">
		<view class="SwitchBookType">
			<view class="SeekSearch">
				<label @click="GotoSearch">
					<image mode="widthFix" src="../../static/images/icon_Search.png"></image>
					<view class="SeekSearchText">搜索漫画名~作者~</view>
				</label>
			</view>
			<view class="SeekBookTypeList">
				<view :class="{'Active':ThisLable==0}" @click="SwitchLable(0)"><label>全部</label></view>
				<view :class="{'Active':ThisLable==Lable.id}" v-for="(Lable,index) in LableList" @click="SwitchLable(Lable.id)" :key="index"><label>{{Lable.name}}</label></view>
			</view>
		</view>
		<view class="OverState">
			<view @click="SwitchOver(2)" class="OverLable" :class="{'Active':OverMode=='2'}"><label>所有</label></view>
			<view @click="SwitchOver(0)" class="OverLable" :class="{'Active':OverMode=='0'}"><label>完结</label></view>
			<view @click="SwitchOver(1)" class="OverLable" :class="{'Active':OverMode=='1'}"><label>连载</label></view>
		</view>
		<view class="RankState">
			<view @click="SwitchRank(1)" class="OverLable" :class="{'Active':RankMode=='1'}"><label>更新排行</label></view>
			<view @click="SwitchRank(2)" class="OverLable" :class="{'Active':RankMode=='2'}"><label>人气排行</label></view>
		</view>
		<view class="SeekBookList" v-show="isShowList">
			<view class="SeekBookLable" :key="index" v-for="(Book,index) in BookListInfo.list" @click="GotoDetails(Book.id)">
				<view class="SeekBookUrl">
					<image class="book-img" mode="widthFix" :src="mediaURL+Book.cover_small"></image>
					<label class="SeekBookUpdate">更新至{{Book.last_chapter_title}}</label>
				</view>
				<view class="SeekBookName">{{Book.title}}</view>
			</view>
		</view>
		<Loading v-show="isShowLoading"></Loading>
	</view>
</template>

<script>
export default {
	data() {
		return {
			LableList:[],
			Lableid:'0',
			BookListInfo:{list:[]},
			ChannelNo:'',
			RankMode:'1',
			OverMode:'2',
			ThisLable:0,
			isShowList: false,
			mediaURL: this.$dev.mediaURL,
			isShowLoading: true,
		};
	},methods:{
		GotoSearch(){
			uni.navigateTo({
				url:'../Recommend/SearchBook'
			})
		},SwitchOver(Num){
			let that=this;
			that.OverMode=Num;
			that.GetBookList();
		},SwitchRank(Num){
			let that=this;
			that.RankMode=Num;
			that.GetBookList();
		},SwitchLable(LableId){
			let that=this;
			that.ThisLable=LableId;
			that.Lableid=LableId;
			that.GetBookList();
		},GetManHuaData(){
			let that=this;
			var Params={
				type:1
			}
			let url=this.$dev.baseURL+this.$api.tagQuery;
			that.$axios.post(url,Params).then(response=>{
				that.LableList=response.data.result;
			}).catch(Error=>{
				
			})
		},GetBookList(){
			let that=this;
			that.isShowLoading = true;
			var Params={
				type:1,gender:that.ChannelNo,
				tagid:that.Lableid,
				serial:that.OverMode,
				order:that.RankMode,
				page:1,size:10
			}
			let url=that.$dev.baseURL+that.$api.cateMain;
			that.$axios.post(url,Params).then(response=>{
				that.isShowLoading = false;
				that.isShowList = true
				console.log(JSON.stringify(response.data))
				that.BookListInfo=response.data.result;
			}).catch(Error=>{
				that.isShowLoading = false;
				that.isShowList = true
			})
		},GotoDetails(id){
			uni.navigateTo({
				url:'../Recommend/BookDetails?bookid='+id
			})
		}
	},mounted(){
		let that=this;
		that.ChannelNo=uni.getStorageSync("BookSex");
		that.GetManHuaData();
		that.GetBookList();
	}
}
</script>

<style>
page-body{overflow: hidden;height: 100%;}
.book-img {
	height: 300upx;
}
</style>
