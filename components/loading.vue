<template>
	<div class="shadow-box flex flex_center flex_just" v-show="isShowLoading">
		<image class="loading-icon" src="../../static/images/ic_drop_down_dermas_refreshing.png"></image>
	</div>
</template>

<script>
	export default {
		name: "loading",
		data() {
			return {
				isShowLoading: this.isShowLoading
			}
		},
		props: ['isShowLoading']
	}
</script>

<style>
	.shadow-box {
		position: fixed;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		/* background-color: rgba(0,0,0,0.3); */
		z-index: 10000;
	}
	.loading-icon {
		margin-top: -100upx;
		width: 74upx;
		height: 74upx;
		animation:rotating 1.2s linear infinite
	}
	@keyframes rotating {
		from{transform:rotate(0)}
		to{transform:rotate(360deg)}
	}
</style>
