package com.hb.web.api;

import com.jfinal.core.paragetter.Para;

public interface ChapterApi {

	/**
	 * 获取书本的章节列表
	 * 
	 * @param bookid
	 *            书本id
	 * @param page
	 *            页索引
	 * @param size
	 *            页大小
	 * @param order
	 *            排序方式 0=正序 1倒序
	 */
	public void query(@Para(value = "book_id", defaultValue = "0") int bookid, @Para(value = "page", defaultValue = "1") int page,
			@Para(value = "size", defaultValue = "10") int size, @Para(value = "order", defaultValue = "0") int order);

	/**
	 * 获取附近章节(返回前后两章节. prev代表前一章,next代表后一章)
	 * 
	 * @param bookid
	 * @param chapter_id
	 */
	public void nearby(@Para(value = "book_id", defaultValue = "0") int bookid,
			@Para(value = "chapter_id", defaultValue = "0") int chapter_id);
}
