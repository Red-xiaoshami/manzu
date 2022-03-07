<template>
	<view class="main-box">
		<view class="flex flex_around tabs_box">
			<view @click="selectItems(0)" class="flex flex_center flex_just flex_column">
				<text>历史</text>
				<text :class="currentTabs==0?'under_line':'no_under_line'"></text>
			</view>
			<view @click="selectItems(1)" class="flex flex_center flex_just flex_column">
				<text>收藏</text>
				<text :class="currentTabs==1?'under_line':'no_under_line'"></text>
			</view>
			<view @click="selectItems(2)" class="flex flex_center flex_just flex_column">
				<text>日更</text>
				<text :class="currentTabs==2?'under_line':'no_under_line'"></text>
			</view>
			<image @click="selectItemToDelete(true)" v-if="currentTabs!=2 && !isShowDelChechbox" class="gift" src="../../static/images/gift.png"></image>
			<view @click="selectItemToDelete(false)" v-show="isShowDelChechbox" class="gift-text gift" v-else>关闭</view>
		</view> 
		<view class="book-list-box" v-if="currentTabs == 0">
			<view class="history-box">
				<!-- <view class="data-title">今天</view> -->
				<view class="flex flex_center flex_bettwen book-list-item" v-for="(item,index) in browsedList" :key="index" @longtap="deleteItemsBrowse(item.book_id, index)">
					<view class="flex flex_center flex_start">
						<view class="flex check-box" v-show="isShowDelChechbox">
							<image @click="selectThis(index,1)" v-if="deleteArrInfo[index].isSelected == 0 && currentTabs==0" src="../../static/icon-2019-01-14/3/icon_07.png"></image>
							<image @click="selectThis(index,0)" v-if="deleteArrInfo[index].isSelected == 1 && currentTabs==0" src="../../static/icon-2019-01-14/3/icon_09.png"></image>
						</view>
						<image class="book-image" :src="imgUrl+item.cover_small"></image>
						<view class="flex flex_column flex_bettwen book-details">
							<text>{{item.title}}</text>
							<text>{{item.keyword}}</text>
							<text>看至{{item.read_chapter_title}}/更至{{item.last_chapter_title}}</text>
						</view>
					</view>
					<view class="btn-to-sea" @click="goDetails(item.book_id)">续看</view>
				</view>
			</view>
		</view>
		
		<view class="book-list-box" v-if="currentTabs == 1">
			<view class="flex flex_center flex_just collect-update-tips">
				<image src="../../static/images/gengxin-pic.jpg"></image>
				<view>您收藏的漫画有<text>2</text>部更新啦！</view>
			</view>
			<view class="flex flex_center flex_start collect-list-box" style="margin-top: -10upx;">
				<view class="flex flex_center flex_just flex_column collect-list-item" v-for="(item,index) in collectionList" :key="index" @click="goDetails(item.book_id)"  @longtap="deleteItems(item.book_id, index)">
					<view class="flex check-box-collect" v-show="isShowDelChechbox" style="display: none!important;">
						<!-- <image @click="selectThis(index,1)" v-if="deleteArrInfo[index].isSelected == 0 && currentTabs==1" src="../../static/icon-2019-01-14/3/icon_07.png"></image>
						<image @click="selectThis(index,0)" v-if="deleteArrInfo[index].isSelected == 1 && currentTabs==1" src="../../static/icon-2019-01-14/3/icon_09.png"></image> -->
					</view>
					<image v-show="item.has_updated==1" class="isNew" src="../../static/images/new.png"></image>
					<image :src="imgUrl+item.cover_small"></image>
					<text class="title-col">{{item.title}}</text>
					<text>{{item.last_chapter_title}}</text>
					<!-- <text>1/更至216话</text> -->
				</view>
			</view>
			<view class="none-data flex flex_column flex_center flex_just" v-show="noneData">
				<image src="../../static/images/no-info.png"></image>
				<text>{{noneDataText}}</text>
			</view>
			<view v-show="noneData">
				<view class="flex flex_bettwen">
					<view class="flex flex_center flex_start l-content">
						<image class="loadMore" src="../../static/images/loadMore.png"></image>
						<text>猜你喜欢</text>
					</view>
					<view @click="getRandData" class="flex flex_center flex_start r-content">
						<text>再猜一下</text>
						<image class="loadMore" src="../../static/images/loadMore.png"></image>
					</view>
				</view>
				<view class="flex flex_center flex_start book-recommand">
					<view class="flex flex_center flex_just flex_column" v-for="(item,index) in rankDataList" :key="index">
						<image :src="imgUrl+item.cover_small"></image>
						<text>{{item.title}}</text>
					</view>
				</view>
			</view>
		</view>
		
		<view class="book-list-box" v-if="currentTabs == 2">
			<view class="flex flex_center flex_around time-tabs">
				<text @click="getcurrentDateIndex(index)" v-for="(item,index) in currentWeekList" :key="index" :class="{'activeTime':currentDateIndex==index}">{{item}}</text>
			</view> 
			<view class="update-book-list" v-for="(item,index) in updatedList" :key="index" @click="goDetails(item.id)">
				<image :src="imgUrl+item.cover_small"></image>
				<view class="flex flex_center flex_bettwen"> 
					<text>{{item.title}}</text>
					<text>{{item.title}}></text>
				</view> 
			</view>
			<view class="none-data flex flex_column flex_center flex_just" v-show="noneData">
				<image src="../../static/images/no-info.png"></image>
				<text>今天没有更新哦/(ㄒoㄒ)/</text>
			</view>
		</view>
		<Loading v-show="isShowLoading"></Loading>
		<view class="flex flex_bettwen flex_center delete-box" v-show="isShowDelChechbox">
			<view @click="selectAll(1)" v-if="!isSelectAll">全选</view>
			<view @click="selectAll(0)" v-else>取消</view>
			<view @click="toDelete">删除</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				currentTabs: 0,
				noneData: false,
				noneDataText: "还没有历史的哦/(ㄒoㄒ)/",
				pageNo: 1,
				imgUrl: this.$dev.mediaURL,
				rankDataList: [],
				browsedList:[],
				collectionList:[],
				updatedList: [],
				currentWeekList: [],
				currentDateIndex: 6,
				currentDate: this.$utils.getDay(0),
				isShowLoading: true,
				deleteArrInfo: [],
				isShowDelChechbox: false,
				isSelectAll: false,
			};
		},
		created() {
			this.getBrowsed();
			this.selectData();
		  getDateStr("2019-02-23 23:30:46");
		  function getDateStr(dayCount){
			if(null == dayCount){
			  dayCount = 0;
			}
			 var dd = new Date();
			 dd.setDate(dd.getDate()+dayCount);//设置日期
			 var y = dd.getFullYear();
			 var m = dd.getMonth()+1;//获取当前月份的日期
			 var d = dd.getDate();
			 return y+"-"+m+"-"+d;
		  } 
		},
		computed:{
		},
		methods: {
			selectItemToDelete: function(bolen) {
				if(bolen) {
					uni.hideTabBar();
				} else {
					uni.showTabBar();
				}
				this.isShowDelChechbox = bolen;
				this.deleteArr();
			},
			deleteArr: function() {
				let that = this;
				let list = [];
				if(that.currentTabs == 0) {
					list = that.browsedList;
				} else {
					list = that.collectionList;
				}
				let listLength = list.length;
				let arr = [];
				for(let i=0;i<listLength;i++) {
					let obj = {};
					obj.isSelected = 0;
					obj.id = list[i].chapter_id;
					arr.push(obj);
				}
				that.deleteArrInfo = arr;
			},
			selectAll: function(type) {
				let that = this;
				let list = [];
				if(that.currentTabs == 0) {
					list = that.browsedList;
				} else {
					list = that.collectionList;
				}
				let listLength = list.length;
				let arr = [];
				for(let i=0;i<listLength;i++) {
					let obj = {};
					obj.isSelected = type;
					obj.id = list[i].chapter_id;
					arr.push(obj);
				}
				that.deleteArrInfo = arr;
				that.isSelectAll = !that.isSelectAll;
			},
			toDelete:function() {
				let that = this
				let deleteArrInfoLength = that.deleteArrInfo.length;
				let arr = [];
				for(let i=0;i<deleteArrInfoLength;i++) {
					if(that.deleteArrInfo[i].isSelected == 1) {
						arr.push(that.deleteArrInfo[i].id);
					}
					console.log(that.deleteArrInfo[i].id)
				}
				console.log("删除的id数组："+JSON.stringify(arr))
				uni.showModal({
					title: '温馨提示',
					content: '删除后将无法恢复',
					success: function (res) {
						if (res.confirm) {
							toDel();
						} else if (res.cancel) {
							plus.nativeUI.toast("已取消");
						}
					}
				});
				
				function toDel() {
					uni.request({
						url: that.$dev.baseURL + that.$api.collectionLike,
						data: {
							book_id: arr
						},
						method: "POST", 
						dataType: "json",
						success: (res) => {
							console.log(JSON.stringify(res.data));
							plus.nativeUI.toast(res.data.msg);
							if(res.data.succ) {
								that.getBrowsed();
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
			},
			selectThis: function(index, type) {
				this.deleteArrInfo[index].isSelected = type;
			},
			getcurrentDateIndex (index) {
				this.currentDateIndex= index;
				this.pageNo = 1;
				this.currentDate = this.$utils.getDay(index-6);
				this.getUpdatedList();
			},
			selectItems(id) {
				let that = this
				if(this.isShowDelChechbox) { // 删除box显示时，不能选择菜单
					return;
				}
				that.isShowLoading = true;
				that.currentTabs = id;
				that.pageNo = 1;
				if(id==0) {
					that.noneDataText = "还没有历史的哦/(ㄒoㄒ)/";
				} else {
					that.noneDataText = "还没有收藏的哦/(ㄒoㄒ)/";
				}
				if(id==0) {
					that.getBrowsed();
				} else if (id==1) {
					that.getCollections();
				} else {
					that.getUpdatedList();
				}
			},
			getRandData: function() {
				let that = this
				uni.request({
					url: this.$dev.baseURL + this.$api.randMain,
					data: {
						page: 1,
						size: 3
					},
					method: "POST", 
					dataType: "json",
					success: (res) => {
						console.log(JSON.stringify(res.data));
						uni.hideLoading();
						
						if(res.data.succ) {
							that.rankDataList = res.data.result;
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
			getBrowsed: function() {
				let that = this
				that.isShowLoading = true;
				uni.request({
					url: this.$dev.baseURL + this.$api.browsed,
					data: {
						type: 1,
						page: that.pageNo,
						size: 20
					},
					method: "POST", 
					dataType: "json",
					success: (res) => {
						console.log(JSON.stringify(res.data));
						if(res.data.code == "-11") {
							uni.navigateTo({ 
								url: '../My/Login' 
							}); 
						}
						if(!res.data.succ) {
							plus.nativeUI.toast(res.data.msg);
							return;
						}
						
						if(that.pageNo !=1) {
							that.browsedList = that.browsedList.concat(res.data.result.list);
						} else {
							that.browsedList = res.data.result.list;
						}
						
						if(that.browsedList.length <= 0) {
							that.noneData = true;
						} else {
						    that.noneData = false;
						}
						
						that.pageNo = that.pageNo + 1;
						that.deleteArr();
					},
					fail: (res) => {
						console.log(JSON.stringify(res));
					},
					complete:()  => {
						that.isShowLoading = false;
					}
				});
			},
			getCollections: function() {
				let that = this
				that.isShowLoading = true;
				uni.request({
					url: this.$dev.baseURL + this.$api.collections,
					data: {
						type: 0,
						page: that.pageNo,
						size: 20
					},
					method: "POST", 
					dataType: "json",
					success: (res) => {
						console.log(JSON.stringify(res.data));
						
						if(res.data.code == "-11") {
							uni.navigateTo({ 
								url: '../My/Login' 
							}); 
						}
						
						if(!res.data.succ) {
							plus.nativeUI.toast(res.data.msg);
							return;
						}
						
						if(that.pageNo !=1) {
							that.collectionList = that.collectionList.concat(res.data.result.list);
						} else {
							that.collectionList = res.data.result.list;
						}
						
						if(that.collectionList.length <= 0) {
							that.noneData = true;
							that.getRandData();
						} else {
						    that.noneData = false;
						}
						
						that.pageNo = that.pageNo + 1;
					},
					fail: (res) => {
						console.log(JSON.stringify(res));
					},
					complete:()  => {
						that.isShowLoading = false;
					}
				});
			},
			getUpdatedList: function() {
				let that = this
				that.isShowLoading = true;
				console.log(JSON.stringify(this.currentDate))
				uni.request({
					url: this.$dev.baseURL + this.$api.updated,
					data: {
						type: 1,
						date: this.currentDate,
						page: that.pageNo,
						size: 20,
					},
					method: "GET", 
					dataType: "json",
					success: (res) => {
						console.log(JSON.stringify(res.data));
						if(res.data.code == "-11") {
							uni.navigateTo({ 
								url: '../My/Login' 
							}); 
						}
						if(!res.data.succ) {
							plus.nativeUI.toast(res.data.msg);
							return;
						}
						
						if(that.pageNo !=1) {
							that.updatedList = that.updatedList.concat(res.data.result.list);
						} else {
							that.updatedList = res.data.result.list;
						}
						
						if(that.updatedList.length <= 0) {
							console.log(333)
							that.noneData = true;
						} else {
						    that.noneData = false;
						}
						
						that.pageNo = that.pageNo + 1;
					},
					fail: (res) => {
						console.log(JSON.stringify(res));
					},
					complete:()  => {
						that.isShowLoading = false;
					}
				});
			},
			selectData:function() {
				let that = this
				var currentTime = new Date();
				var weekIndex = currentTime.getDay();
				var weekList=["周日","周一","周二","周三","周四","周五","周六"];
				var week1 = ["昨天","今天"];
				var week2 =  weekList.splice(0, weekIndex-1);
				var weekList=["周日","周一","周二","周三","周四","周五","周六"];
				var week3 =  weekList.splice(weekIndex+1, 6);
				that.currentWeekList = week3.concat(week2.concat(week1));
				console.log(JSON.stringify(that.currentWeekList));
			},
			goDetails: function(id) {
				uni.navigateTo({ 
					url: '../Recommend/BookDetails?bookid=' + id 
				});
			},
			deleteItems(id,index) {
				let that = this;
				uni.showModal({
					content: '您确定删除当前漫画吗？',
					success: function (res) {
						if (res.confirm) {
							that.isShowLoading = true;
							uni.request({
								url: that.$dev.baseURL + that.$api.collectionDel + "?book_id=" + id,
								data: {
									book_id: id, 
								},
								method: "GET", 
								dataType: "json",
								success: (res) => {
									plus.nativeUI.toast(res.data.msg);
									console.log(JSON.stringify(res.data));
									if(res.data.code == "-11") {
										uni.navigateTo({ 
											url: '../My/Login' 
										}); 
									}
									if(res.data.result.del) {
										that.collectionList.splice(index, 1);
										return;
									}
								},
								fail: (res) => {
									console.log(JSON.stringify(res));
								},
								complete:()  => {
									that.isShowLoading = false;
								}
							});
						}
					}
				});
			},
			deleteItemsBrowse(id,index) {
				let that = this;
				uni.showModal({
					content: '您确定删除当前漫画吗？',
					success: function (res) {
						if (res.confirm) {
							that.isShowLoading = true;
							uni.request({
								url: that.$dev.baseURL + that.$api.browseDel + "?book_id=" + id,
								data: {
									book_id: id, 
								},
								method: "GET", 
								dataType: "json",
								success: (res) => {
									plus.nativeUI.toast(res.data.msg);
									console.log(JSON.stringify(res.data));
									if(res.data.code == "-11") {
										uni.navigateTo({ 
											url: '../My/Login' 
										}); 
									}
									if(res.data.result.del) {
										that.browsedList.splice(index, 1);
										return;
									}
								},
								fail: (res) => {
									console.log(JSON.stringify(res));
								},
								complete:()  => {
									that.isShowLoading = false;
								}
							});
						}
					}
				});
			},
		}
	}
</script>

<style>
.main-box {
	position: relative;
	margin-bottom: 50upx;
	width: 100%;
}
.tabs_box {
   position: fixed;
   left: 0;
   top: 0;
   width: 100%;
   box-sizing: border-box;
   background: #fff;
   z-index: 100;
   padding: 0 23%;
   border-bottom: solid 1px #eee;
   padding-bottom: 20upx;
}
.tabs_box text {
	font-size: 30upx;
	margin-top: 30upx;
}
.tabs_box text.under_line {
	display: inline-block;
	background: #FFD321;
	width: 60upx;
	height: 7upx;
	margin-top: 10upx;
}
.tabs_box text.no_under_line {
	display: inline-block;
	background: #FFF;
	width: 60upx;
	height: 7upx;
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
.gift {
	position: absolute;
	width: 40upx;
	height: 40upx;
	right: 40upx;
	top: 33upx;
	font-size: 32upx;
	color: #000;
	display: none;
}
.gift-text {
	width: 80upx;
	text-align: right;
}
.l-content {
	margin: 60upx 0 50upx;
}
.loadMore {
	width: 36upx;
	height: 36upx;
	margin-right: 20upx;
	margin-left: 30upx;
}
.l-content text {
	font-size: 30upx;
	font-weight: 700;
	text-align: left;
}
.r-content text {
	font-size: 28upx;
	font-weight: 500;
	color: #CECECE;
	text-align: right;
}
.r-content image {
	width: 32upx;
	height: 32upx;
	margin-right: 30upx;
	margin-left: 10upx;
}
.book-recommand {
	padding-bottom: 100upx;
	padding-left: 32upx;
	flex-wrap: wrap;
	flex-direction: row;
}
.book-recommand view {
	margin-right: 30upx;
}
.book-recommand image {
	width: 210upx;
	height: 297upx;
	border-radius: 8upx;
}
.book-recommand text {
	color: #333;
	font-size: 28upx;
	text-overflow: ellipsis;
	overflow: hidden;
	display: inline-block;
	white-space: nowrap;
	width: 210upx;
	margin-top: 6upx;
}	
/* 历史-书籍列表 */
.book-list-box {
	margin-top: 120upx;
}
.book-list-box .book-list-item:not(:first-child) {
	padding: 20upx 0;
}
.book-list-box .book-list-item:first-child {
	padding-top: 0;
}
.book-list-box .book-list-item:last-child {
	border-bottom: solid #f0f0f0 1px;
}
.book-list-box .btn-to-sea {
	font-size: 26upx;
	color: #999;
	display: inline-block;
	padding: 4upx 16upx;
	border-radius: 30upx;
	border: solid #eee 1px;
}
.book-list-box .data-title {
	font-size: 28upx;
	color: #999;
	margin: 20upx 0;
}
.book-list-box .book-image {
	width: 144upx;
	height: 190upx;
	margin-right: 30upx;
} 
.book-details {
	text-align: left;
}
.book-details text:nth-of-type(1) {
	color: #000;
	font-size: 30upx;
}
.book-details text:nth-of-type(2) {
	color: #999; 
	font-size: 24upx;
	margin-top: 20upx;
	margin-bottom: 50upx;
}
.book-details text:nth-of-type(3) {
	display: inline-block;
  color: #999;
	font-size: 26upx;
  width: 450upx;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.time-tabs {
	padding: 15upx 0;
	margin-bottom: 16upx;
}
.time-tabs text {
	display: inline-block;
	color: #555;
	font-size: 28upx;
}
.time-tabs text.activeTime {
	color: #FFD321;
	border: solid 1px #FFD321;
	padding: 0 10upx;
	border-radius: 24upx;
}
.update-book-list, .history-box {
	padding: 0 20upx;
}
.update-book-list image {
	width: 100%;
	height: 400upx;
	border-radius: 8upx;
}
.update-book-list view {
	height: 70upx;
	padding-bottom: 16upx;
}
.update-book-list view text {
	font-size: 30upx;
}
.update-book-list view text:first-child {
	color: #000;
}
.update-book-list view text:last-child {
	color: #E8E6E6;
}
.collect-update-tips {
	display: none;
	height: 130upx; 
}
.collect-update-tips image {
	width: 80upx;
	height: 70upx;
	margin-right: 20upx;
} 
.collect-update-tips view {
	color: #999;
	font-size: 30upx;
}
.collect-update-tips view text {
	color: red;
}
.collect-list-box {
	position: relative;
	flex-wrap: wrap;
	flex-direction: row;
	margin-top: -80upx;
	margin-left: 20upx;
}
.collect-list-box .title-col {
	display: inline-block;
	text-overflow: ellipsis;
	overflow: hidden;
	white-space: nowrap;
	width: 240upx;
	text-align: center;
}
.collect-list-box .collect-list-item {
	position: relative;
	margin-top: 20px;
	width: 32.5%;
}
.collect-list-box .collect-list-item image {
	width: 210upx;
	height: 297upx;
	border-radius: 8upx;
}
.collect-list-box .collect-list-item image.isNew {
	position: absolute;
	left: 25upx;
	top: 15upx;
	width: 50upx;
	height: 34upx;
}
.collect-list-box .collect-list-item text:nth-of-type(1) {
	color: #333;
	font-size: 30upx;
}
.collect-list-box .collect-list-item text:nth-of-type(2) {
	color: #999;
	font-size: 28upx;
}
.check-box {
	 
}
.check-box image {
	width: 38upx;
	height: 38upx;
	margin-right: 30upx;
}
.check-box-collect {
	position: absolute;
	z-index: 10;
	top: 245upx;
	right: 15upx;
}
.check-box-collect image {
	width: 38upx!important;
	height: 38upx!important;
}
.delete-box {
	position: fixed;
	left: 0;
	bottom: 0;
	height: 100upx;
	width: 100%;
}
.delete-box view {
	height: 100upx;
	line-height: 100upx;
	font-size: 34upx;
	text-align: center;
	width: 50%;
	box-sizing: border-box;
	background: #EEF0EF;
}
.delete-box view:first-child {
	border-right: solid 1px #DBDDDB ;
}
</style>
