package com.hb.web.api;

import com.jfinal.core.paragetter.Para;

public interface CollectionApi {

	/**
	 * 是否收藏(需要登录)
	 * 
	 * @param bookid
	 */
	public void is(@Para(value = "book_id", defaultValue = "0") int bookid);

	/**
	 * 添加收藏(需要登录)
	 * 
	 * @param bookid
	 */
	public void add(@Para(value = "book_id", defaultValue = "0") int bookid);

	/**
	 * 删除收藏(需要登录)
	 * 
	 * @param bookid
	 */
	public void del(@Para(value = "book_id", defaultValue = "0") int bookid);
}
