<script>
	export default {
		onLaunch: function () {
			console.log('App Launch')
		},
		onShow: function () {
			console.log('App Show')
		},
		onHide: function () {
			console.log('App Hide')
		}
	}
</script>

<style>
	@import "common/Style.css";
	.uni-swiper .uni-swiper-wrapper{
		overflow: visible!important;
	}
	.flex {
		display: flex;
		display: -webkit-flex;
	}
	
	.flex_1 {
		flex: 1;
	}
	
	.flex_center {
		align-items: center;
	}
	
	.flex_just {
		justify-content: center;
	}
	.flex_start {
		justify-content: flex-start;
	}
	.flex_bettwen {
		justify-content: space-between;
	}
	
	.flex_around {
		justify-content: space-around;
	}
	
	.flex_column {
		flex-direction: column;
	}
</style>
