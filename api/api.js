// 首页
const banners = '/api/v1/book/banners'; // 获取首页banner图
const recommendMain = '/api/v1/book/recommend'; // 获取首页数据
const rankMain = '/api/v1/book/rank'; // 首页排行榜
const randMain = '/api/v1/book/rand'; // 首页随机
const MoneyRank='/api/v1/gift/top'
const MyReward = '/api/v1/gift/my_rewards'; // 土豪榜我的打赏
const searchMain = '/api/v1/book/search'; // 首页搜索
const cateMain = '/api/v1/book/cate'; // 首页分类
const detailsBook = '/api/v1/book/details'; // 书本详情

const chapterListQuery = '/api/v1/chapter/query'; // 获取书本的章节列表
const chapterListNearby = '/api/v1/chapter/nearby'; // 附近章节
const chapterListDetails = '/api/v1/chapter/details'; // 获取章节详情
// 收藏
const collectionIs = '/api/v1/collection/is'; // 是否收藏
const collectionAdd = '/api/v1/collection/add'; // 收藏
const collectionLike = '/api/v1/collection/del'; // 删除

// 评论
const commentQuery = '/api/v1/comment/query'; // 获取书本的评论
const commentAdd = '/api/v1/comment/add'; // 添加评论
const commentLike = '/api/v1/comment/like'; // 评论点赞

// 漫画详情页
const giftQuery = '/api/v1/gift/query'; // 获取书本礼物（本书状态下）
const giftAdd = '/api/v1/gift/add'; // 用户打赏(送礼物)
const giftAll = '/api/v1/gift/all'; // 查询所有礼物

// 找书
const tagQuery = '/api/v1/tag/query'; // 标签查询

// 书架
const updated = '/api/v1/user/updated'; // 日更
const browseDel = '/api/v1/browse/del'; // 删除历史记录
const collectionDel = '/api/v1/collection/del'; // 删除收藏记录

// 个人中心
const sendSms = '/api/v1/user/sms'; // 发送短信
const toLogin = '/api/v1/user/mlogin'; // 手机登录,手机不存在则注册
const rewards = '/api/v1/user/rewards'; // 打赏
const ordersList = '/api/v1/user/orders'; // 我的记录-订单
const consumes = '/api/v1/user/consumes'; // 消费
const collections = '/api/v1/user/collections'; // 收藏
const browsed = '/api/v1/user/browsed'; // 浏览记录
const rechargeList = '/api/v1/user/recharge'; // 充值列表
const getMoney = '/api/v1/user/money'; // 获取总金额
const sign = '/api/v1/user/sign'; // 获取总金额
const feedback = '/api/v1/user/feedback'; // 意见反馈

const getLastOrderInfo = '/pay/getLastOrderInfo'; // 获取最后一次订单

export default {
    sendSms,
    toLogin,
    banners,
    recommendMain,
    rankMain,
    randMain,
    searchMain,
    rewards,
    consumes,
    rechargeList,
	ordersList,
	getMoney,
	sign,
	detailsBook,
	collections,
	updated,
	browsed,
	MoneyRank,
	MyReward,
	tagQuery,
	collectionIs,
	collectionAdd,
	commentQuery,
	cateMain,
	giftAll,
	commentAdd,
	collectionLike,
	feedback,
	getLastOrderInfo,
	collectionDel,
	browseDel
}