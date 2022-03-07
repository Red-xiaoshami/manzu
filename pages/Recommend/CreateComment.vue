<template>
	<view>
		<view class="CommentBtnBox">
			<button class="CancelBtn">取消</button>
			<button @click="SendComment" class="SendBtn">评论</button>
		</view>
		<view class="CommentInputBox">
			<textarea class="CommentInput" contenteditable="true" v-model='CommentText' ref="Comment"></textarea>
		</view>
		<view class="ExpressionBox">
			<!-- <label class="ExpressionBtn"><image @click="SwitchExpressionShow" mode="widthFix" :src="ShowExpression?'../../static/images/icon_Expression2.png':'../../static/images/icon_Expression.png'"></image></label> -->
			<view class="ExpressionList" v-if="ShowExpression">
				<label class="Expression" @click="ChoiceExpression(Expression)" :key="index" v-for="(Expression,index) in Expressionlist">
					<image mode="widthFix" :src="'../../static/Icon/'+Expression+'.gif'"></image>
				</label>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				CommentText:'',
				ShowExpression:false,
				Expressionlist:[],
			};
		},onLoad(option){
			let that=this;
			that.BookId=option.bookid;
		},methods:{
			GetExpressionList(){
				let NumberList=[];
				for(var i=1;i<=60;i++){
					NumberList[i-1]=i;
				}
				return NumberList;
			},SwitchExpressionShow(){
				let that=this;
				that.ShowExpression=that.ShowExpression?false:true;
			},ChoiceExpression(Expression){
				let that=this;
				console.log(that.CommentText)
				that.CommentText.target.firstChild.data+='<image mode="widthFix" src="static/Icon/'+Expression+'.gif"></image>';
				that.ShowExpression=that.ShowExpression?false:true;
			},SendComment(){
				let that=this;
				var Params={
					book_id:that.BookId,
					content:encodeURI(encodeURI(that.CommentText))
				}
				let url=that.$dev.baseURL+that.$api.commentAdd;
				that.$axios.post(url,Params).then(response=>{
					// that.IsCollection=response.data.result.is;
					uni.navigateBack({
						delta: 1,
					});
				}).catch(Error=>{
				}) 
			}
		},mounted(){
			let that=this;
			that.Expressionlist=that.GetExpressionList();
		}
	}
</script>

<style>

</style>
