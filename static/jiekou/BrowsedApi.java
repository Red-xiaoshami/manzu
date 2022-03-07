package com.hb.web.api;

import com.jfinal.core.paragetter.Para;

public interface BrowsedApi {
	/**
	 * 浏览历史记录(需要登录)
	 * 
	 * @param bookid
	 *            书本id
	 */
	public void del(@Para(value = "book_id", defaultValue = "0") int bookid);
}
