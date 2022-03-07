<template>
	<view>
		<view class="DetailsBox">
			<view class="BookMaxUrl">
				<image class="DetailsUrl" mode="widthFix" :src="mediaURL+BookDetailsInfo.cover_small"></image>
				<view class="LayerBox"></view>
				<view class="IconGoHome"><image mode="widthFix" @click="GotoMain" src="../../static/images/home.png"></image></view>
				<view class="BookDetailsName">{{BookDetailsInfo.subtitle}}</view>
				<view class="BookDetailsOther">
					<label class="Otherleft">
						<image mode="widthFix" src="../../static/images/info-frie.png"></image>
						<label class="SellNumber">{{BookDetailsInfo.popularity}}万</label>
						<label class="BookType" v-if="keyword" :key="index" v-for="(keyword,index) in BookDetailsInfo.keyword">{{keyword}}</label>
					</label>
					<label class="Otherright">
						<label class="rightText">连载-</label>
						<label class="rightUpdate">至{{BookDetailsInfo.last_chapter_title}}</label>
					</label>
				</view>
			</view>
			<view class="ContentBox">
				<view class="DetailsSwitch">
					<view @click="SwitchDetails(1)" :class="{'Active':DetailsNumber==1}"><label>详情</label></view>
					<view @click="SwitchDetails(2)" :class="{'Active':DetailsNumber==2}"><label>目录</label></view>
				</view>
				<view class="DetailsContent" v-if="DetailsNumber==1">
					<view class="DetailsText">{{BookDetailsInfo.memo}}</view>
					<view class="CelebrityBox">
						<view class="CelebrityTit">
							<image mode="widthFix" src="../../static/images/reward.png"></image>
							<label>名人榜</label>
							<label class="rightText">共打赏2562金币</label>
						</view>
						<view class="CelebrityList">
							<label class="CelebrityInfo">
								<image mode="widthFix" src="../../static/img/132.jpg"></image>
								<view class="RewardNumber">388</view>
								<view class="CelebrityName">苏阮阮</view>
							</label>
							<label class="CelebrityInfo">
								<image mode="widthFix" src="../../static/img/132.jpg"></image>
								<view class="RewardNumber">388</view>
								<view class="CelebrityName">苏阮阮</view>
							</label>
							<label class="CelebrityInfo">
								<image mode="widthFix" src="../../static/img/132.jpg"></image>
								<view class="RewardNumber">388</view>
								<view class="CelebrityName">苏阮阮</view>
							</label>
							<label class="CelebrityInfo">
								<image mode="widthFix" src="../../static/img/132.jpg"></image>
								<view class="RewardNumber">388</view>
								<view class="CelebrityName">苏阮阮</view>
							</label>
							<label class="CelebrityInfo">
								<image mode="widthFix" src="../../static/img/132.jpg"></image>
								<view class="RewardNumber">388</view>
								<view class="CelebrityName">苏阮阮</view>
							</label>
						</view>
					</view>
					<view class="CommentBox">
						<view class="CommentTitBox">
							<image mode="widthFix" src="../../static/images/comment.png"></image>
							<label class="TitText">精彩评论</label>
							<image class="EditIcon" @click="CreateComment" mode="widthFix" src="../../static/images/commentBtn.png"></image>
						</view>
						<view class="NullCommentList" v-if="CommentList.list.length<1">当前漫画暂无评论，快去抢沙发吧!</view>
						<view class="CommentList" v-if="CommentList.list.length>0" :key="index" v-for="(Comment,index) in CommentList.list">
							<view class="CommentOther">
								<image mode="widthFix" :src="'http://img.ttnnr.com'+Comment.header_icon"></image>
								<view class="ReleaseInfo">
									<view class="ReleaseUser">{{Comment.nick_name}}</view>
									<view class="ReleaseTime">
										<image mode="widthFix" src="../../static/images/comtime.png"></image>
										<label>{{Comment.add_time}}</label>
									</view>
								</view>
								<view class="OtherRight">
									<label>37</label>
									<image mode="widthFix" src="../../static/images/icon_Fabulous.png"></image>
								</view>
							</view>
							<view class="CommentText" v-html="decodeURI(Comment.content)"></view>
						</view>
						<!-- <view class="CommentList">
							<view class="CommentOther">
								<image mode="widthFix" src="../../static/img/132.jpg"></image>
								<view class="ReleaseInfo">
									<view class="ReleaseUser">毛利小五郎</view>
									<view class="ReleaseTime">
										<image mode="widthFix" src="../../static/images/comtime.png"></image>
										<label>12小时前</label>
									</view>
								</view>
								<view class="OtherRight">
									<label>37</label>
									<image mode="widthFix" src="../../static/images/icon_Fabulous.png"></image>
								</view>
							</view>
							<view class="CommentText">666</view>
						</view>
						<view class="CommentList">
							<view class="CommentOther">
								<image mode="widthFix" src="../../static/img/132.jpg"></image>
								<view class="ReleaseInfo">
									<view class="ReleaseUser">毛利小五郎</view>
									<view class="ReleaseTime">
										<image mode="widthFix" src="../../static/images/comtime.png"></image>
										<label>12小时前</label>
									</view>
								</view>
								<view class="OtherRight">
									<label>37</label>
									<image mode="widthFix" src="../../static/images/icon_Fabulous.png"></image>
								</view>
							</view>
							<view class="CommentText">666</view>
						</view> -->
					</view>
					<view class="AllComment" v-if="CommentList.list.length>0"><button>查看更多评论~</button></view>
					<view class="IsLikeListTitle">
						<image class="IconLike" mode="widthFix" src="../../static/images/loadMore.png"></image>
						<view class="TipText">猜你喜欢</view>
						<label class="ListrigthBtn" @click="GetPopularList">
							<view>再试一下</view>
							<image mode="widthFix" src="../../static/images/icon_Reload.png"></image>
						</label>
					</view>
					<view class="IsLikeList">
						<label class="BookInfo"  @click="GotoDetails(Popular.id)" :key="index" v-for="(Popular,index) in PopularList">
							<view class="RewardBookUrl"><image :src="'http://img.ttnnr.com'+Popular.cover_small"></image></view>
							<view class="RewardBookName">{{Popular.subtitle}}</view>
						</label>
						<!-- <label class="BookInfo">
							<image mode="widthFix" src="../../static/img/1535271627_香艳小店.jpg"></image>
							<view class="RewardBookName">香艳小店</view>
						</label>
						<label class="BookInfo">
							<image mode="widthFix" src="../../static/img/1535271627_香艳小店.jpg"></image>
							<view class="RewardBookName">香艳小店</view>
						</label> -->
					</view>
				</view>
				<view v-if="DetailsNumber!=1" class="DetailsCatalogBox">
					<view class="CatalogTit">
						<label class="TitText">连载中</label>
						<label class="Dian">·</label>
						<label class="CatalogTime">{{BookDetailsInfo.NewUpdateTime}} 更至{{BookDetailsInfo.last_chapter_title}}</label>
						<label @click="Switchchapters" class="Catalogsort">{{BookSort?'正序':'倒序'}}</label>
					</view>
					<view class="CatalogList">
						<view class="CalalogInfo" v-if="BookSort" :key="index" v-for="(chapters,index) in BookDetailsInfo.book_chapters"><label>{{chapters.name}}</label></view>
						<view class="CalalogInfo" v-if="!BookSort" :key="index" v-for="(chapters,index) in BookDetailsInfo.Sortchapters"><label>{{chapters.name}}</label></view>
						<!-- <view class="CalalogInfo"><label>第1话 过去</label></view>
						<view class="CalalogInfo"><label>第1话 过去</label></view>
						<view class="CalalogInfo"><label>第1话 过去</label></view>
						<view class="CalalogInfo"><label>第1话 过去</label></view>
						<view class="CalalogInfo"><label>第1话 过去</label></view>
						<view class="CalalogInfo"><label>第1话 过去</label></view>
						<view class="CalalogInfo"><label>第1话 过去</label></view>
						<view class="CalalogInfo"><label>第1话 过去</label></view>
						<view class="CalalogInfo"><label>第1话 过去</label></view> -->
					</view>
				</view>
			</view>
			<view class="BottomOther">
				<label @click="BookCollection"><image mode="widthFix" :src="IsCollection?'../../static/images/collect.png':'../../static/images/icon_Coolection.png'"></image>加入收藏</label>
				<label><button>立即阅读</button></label>
				<label><image mode="widthFix" src="../../static/images/gift.png"></image>送礼物</label>
			</view>
		</view>
		<view class="GiftPanel" v-if="ShowGiftBool">
			<view class="BottomBox">
				<image class="GiftBg" mode="widthFix" src="../../static/images/gift-huniang.png"></image>
				<view class="GiftListBox">
					<label class="GiftLable" :key="index" v-for="(Gift,index) in GiftList">
						<image mode="widthFix" :src="'http://img.ttnnr.com'+Gift.gift_img"></image>
						<view class="GiftName">{{Gift.gift_name}}</view>
						<view class="GiftMoney">{{Gift.gift_money}}</view>
					</label>
				</view>
			</view>
		</view>
		<Loading v-show="isShowLoading"></Loading>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				BookId:'',
				BookDetailsInfo:{},
				ChannelNo:'',
				GiftList:[],
				CommentList:{list:[]},
				IsCollection:false,
				BookSort:true,
				ShowGiftBool:false,
				PopularList:[],
				DetailsNumber:1,
				mediaURL: this.$dev.mediaURL,
				isShowLoading: true,
			};
		},onLoad(option){
			let that=this;
			that.BookId=option.bookid;
			that.GetBookDetails();
		},onShow() {
			if(this.BookId){
				this.GetBookComment();
			}
		},methods:{
			SwitchDetails(Number){
				let that=this;
				that.DetailsNumber=Number;
			},GetBookDetails(){
				let that=this;
				that.isShowLoading = true;
				var Params={
					book_id:that.BookId
				}
				let url=that.$dev.baseURL+that.$api.detailsBook;
				that.$axios.post(url,Params).then(response=>{
					that.isShowLoading = false;
					var DetailsData=response.data.result;
					DetailsData.popularity=(DetailsData.popularity/10000).toFixed(2);
					DetailsData.keyword=DetailsData.keyword.split(',');
					DetailsData.Sortchapters=DetailsData.book_chapters;
					DetailsData.NewUpdateTime=DetailsData.book_chapters[(response.data.result.book_chapters.length-1)].add_time.substr(0,10);
					DetailsData.Sortchapters=JSON.parse(JSON.stringify(DetailsData.Sortchapters)).reverse()
					that.BookDetailsInfo=DetailsData;
				}).catch(Error=>{
					that.isShowLoading = false;
				})
			},GetPopularList(){
				let that=this;
				that.isShowLoading = true;
				var Params={
					type:1,
					gender:that.ChannelNo,
					count:3
				}
				let url=that.$dev.baseURL+that.$api.randMain;
				that.$axios.post(url,Params).then(response=>{
					that.isShowLoading = false;
					that.PopularList=response.data.result;
				}).catch(Error=>{
					that.isShowLoading = false;
				})
			},Switchchapters(){
				let that=this;
				that.BookSort=that.BookSort?false:true;
			},GetBookCollection(){
				let that=this;
				that.isShowLoading = true;
				var Params={book_id:that.BookId}
				let url=that.$dev.baseURL+that.$api.collectionIs;
				that.$axios.post(url,Params).then(response=>{
					that.isShowLoading = false;
					console.log(JSON.stringify(response.data))
					that.IsCollection=response.data.result.is;
				}).catch(Error=>{
				that.isShowLoading = false;
				}) 
			},BookCollection(){
				let that=this;
				that.isShowLoading = true;
				var Params={book_id:that.BookId};
				let url=that.IsCollection?that.$dev.baseURL+that.$api.collectionLike:that.$dev.baseURL+that.$api.collectionAdd;
				that.$axios.post(url,Params).then(response=>{
					that.isShowLoading = false;
					console.log(JSON.stringify(response.data))
					that.IsCollection=that.IsCollection?false:true;
				}).catch(Error=>{
					that.isShowLoading = false;
				})
			},GetBookComment(){
				let that=this;
				var Params={
					book_id:that.BookId,
					page:1,size:3
				}
				let url=that.$dev.baseURL+that.$api.commentQuery;
				that.$axios.post(url,Params).then(response=>{
					console.log(JSON.stringify(response.data))
					that.CommentList=response.data.result;
				}).catch(Error=>{
				})
			},GetAllGiftList(){
				let that=this;
				var Params={}
				let url=that.$dev.baseURL+that.$api.giftAll;
				that.$axios.post(url,Params).then(response=>{
					console.log(JSON.stringify(response.data))
					that.GiftList=response.data.result;
				}).catch(Error=>{
				})
			},GotoMain(){
				console.log(getCurrentPages()[getCurrentPages().length-2]);
// 				uni.navigateBack({
// 					delta: 1
// 				});
			},CreateComment(){
				let that=this;
				uni.navigateTo({
					url:'CreateComment?bookid='+that.BookId,
				})
			},GotoDetails(id){
				uni.navigateTo({
					url:'../Recommend/BookDetails?bookid='+id
				})
			}
		},mounted(){
			let that=this;
			that.ChannelNo=uni.getStorageSync("BookSex");
			that.GetPopularList();
			that.GetBookComment();
			that.GetAllGiftList();
			that.GetBookCollection();
		}
	}
</script>

<style>

</style>
