package com.hb.web.api;

import com.jfinal.core.paragetter.Para;

public interface GiftApi {

	/**
	 * 获取书本礼物列表(用于排行榜页面的走马灯内容)
	 * 
	 * @param bookid
	 * @param page
	 * @param size
	 */
	public void query(@Para(value = "book_id", defaultValue = "0") int bookid, @Para(value = "page", defaultValue = "1") int page,
			@Para(value = "size", defaultValue = "20") int size);

	/**
	 * 获取书本打赏排行榜(土豪榜)
	 * 
	 * @param bookid
	 * @param page
	 * @param size
	 */
	public void rank(@Para(value = "book_id", defaultValue = "0") int bookid, @Para(value = "page", defaultValue = "1") int page,
			@Para(value = "size", defaultValue = "20") int size);

	/**
	 * 用户打赏(需要登录)
	 * 
	 * @param bookid书本id
	 * @param giftid礼物id
	 * @param count数量
	 */
	public void add(@Para(value = "book_id", defaultValue = "0") int bookid, @Para(value = "gift_id", defaultValue = "0") int giftid,
			@Para(value = "count", defaultValue = "1") int count);

	/**
	 * 查询所有礼物列表
	 */
	public void all();
}
