package com.hb.web.api;

import com.jfinal.core.paragetter.Para;

/**
 * 通用返回根json{ "msg": "ok", "result": [] "code": 1, "succ": true }
 * 首先判断succ是否为true，假如不为true。则toast显示msg消息，， result属性可以为对象，也能为对象数组
 * result:[],result:{} 都是存在的
 * 
 * @author hbslover
 *
 */
public interface BookApi {
	// 测试接口地址http://comic.3gi.cc

	/**
	 * 获取banners /api/v1/book/banners
	 * 测试地址为：http://comic.3gi.cc/api/v1/book/banners?type=1&gender=1
	 * 
	 * @param type
	 *            0：全部，1：漫画，2：小说
	 * @param gender
	 *            2：全部，0：女性，1：男性
	 */
	public void banners(@Para(value = "type", defaultValue = "1") int type, @Para(value = "gender", defaultValue = "2") int gender);
	// 返回的json
	// "img_url": "/static/kmh/cover/110015/cover_wide.jpg", 显示图片
	// "target_url": "110015", //跳转的url。 一般为http(s)://开头，否则就是书本的id。在app内跳转
	// "title": "神秘小岛", //标题
	// "target": "" //跳转方式 _blank:打开新标签跳转到target_url,
	// _self:自身跳转，跟html的a标签一致，为了与H5兼容。 为空或者为_app，则代表app跳转

	/**
	 * 获取首页数据
	 * 
	 * @param type
	 *            0：全部，1：漫画，2：小说
	 * @param gender
	 *            2：全部，0：女性，1：男性
	 */
	public void recommend(@Para(value = "type", defaultValue = "1") int type, @Para(value = "gender", defaultValue = "2") int gender);
	// 返回的json字段
	// "list" :[] 书本数据
	// "title_color": "#000000", 标题颜色
	// "more_action": "/cate?t=novel&o=0", 点击更多的动作，这里是与web上兼容。app可以忽略
	// "name": "updated_books", 占坑，暂时不用
	// "style": "2(cover_medium)*2(cover_medium)", 显示风格，占坑字段，app可以忽略。
	// "title": "火爆新作", 标题
	// "type": 1, 显示类型 1：显示2行2列 cover_medium 图 ，可以参考公众号 火爆新作
	// 2:显示1行cover_large图，其他3行cover_medium图，可以参考公众号 新书报道
	// 3:显示1行cover_large图，2行2列 cover_medium 图，可以参考公众号 佳作必看
	// 4:显示1行3列cover_small图，第二行2列 cover_medium 图，可以参考公众号 经典完结
	// "order": 0 排序号，这里已经在服务端做了排序，客户端可以忽略

	/**
	 * 随机书本
	 * 
	 * @param type
	 * @param gender
	 * @param count
	 */
	public void rand(@Para(value = "type", defaultValue = "1") int type, @Para(value = "gender", defaultValue = "2") int gender,
			@Para(value = "count", defaultValue = "3") int count);

	/**
	 * 获取排行榜
	 * 
	 * @param type
	 *            0：全部，1：漫画，2：小说
	 * @param gender
	 *            2：全部，0：女性，1：男性
	 * @param rankType
	 *            排行方式: 0：人气排行，1：点击排行，2：订阅排行，3：购买排行，4：阅读排行，5：参与排行，6：打赏排行
	 */
	public void rank(@Para(value = "type", defaultValue = "1") int type, @Para(value = "gender", defaultValue = "2") int gender,
			@Para(value = "rank_type", defaultValue = "0") int rankType);

	/**
	 * 搜索
	 * 
	 * @param type
	 *            0：全部，1：漫画，2：小说
	 * @param gender
	 *            2：全部，0：女性，1：男性
	 * @param keyword
	 *            关键词
	 * @param pageNo
	 *            页索引
	 * @param size
	 *            返回大小
	 */
	public void search(@Para(value = "type", defaultValue = "1") int type, @Para(value = "gender", defaultValue = "2") int gender,
			@Para(value = "keyword", defaultValue = "") String keyword, @Para(value = "page", defaultValue = "1") int pageNo,
			@Para(value = "size", defaultValue = "20") int size);

	/**
	 * 分类
	 * 
	 * @param type
	 *            0：全部，1：漫画，2：小说
	 * @param gender
	 *            2：全部，0：女性，1：男性
	 * @param tagid
	 *            0：全部，非0则代表tag的id
	 * @param serial
	 *            是否连载 0：完结，1：连载，2：所有
	 * @param order
	 *            1：按更新排行，2：按人气排行
	 * @param page
	 *            页索引
	 * @param size
	 *            页大小
	 */
	public void cate(@Para(value = "type", defaultValue = "1") int type, @Para(value = "gender", defaultValue = "2") int gender,
			@Para(value = "tagid", defaultValue = "0") int tagid, @Para(value = "serial", defaultValue = "2") int serial,
			@Para(value = "order", defaultValue = "1") int order, @Para(value = "page", defaultValue = "1") int page,
			@Para(value = "size", defaultValue = "20") int size);

	/**
	 * 书本详情
	 * 
	 * @param bookid
	 */
	public void details(@Para(value = "book_id", defaultValue = "0") int bookid);

	/**
	 * 阅读章节(需要登录.不登录返回预览章节,登录则根据情况返回内容, 会员返回所有内容. 非会员余额充足则扣费之后返回所有内容,
	 * 没有余额也只返回预览内容) 免费章节则返回所有内容
	 * 
	 * @param chapter_id
	 */
	public void read(@Para(value = "chapter_id", defaultValue = "0") int chapter_id);
}
