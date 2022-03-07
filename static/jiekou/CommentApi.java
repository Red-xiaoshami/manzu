package com.hb.web.api;

import com.jfinal.core.paragetter.Para;

public interface CommentApi {
	/**
	 * 获取书本的评论
	 * 
	 * @param bookid书本id
	 * @param page页索引
	 * @param size页大小
	 */
	public void query(@Para(value = "book_id", defaultValue = "0") int bookid, @Para(value = "page", defaultValue = "1") int page,
			@Para(value = "size", defaultValue = "20") int size);

	/**
	 * 添加评论(需要登录), 用户无法刷屏.默认策略是2小时内无法重发发布评论. 目前未做评论审核
	 * 
	 * @param bookid书本
	 * @param content
	 *            内容默认过滤非法字符(html标签请转义再提交)
	 */
	public void add(@Para(value = "book_id", defaultValue = "0") int bookid, @Para(value = "content", defaultValue = "") String content);

	/**
	 * 评论点赞(需要登录) 用户只能点赞一次.
	 * 
	 * @param commentid评论id
	 */
	public void like(@Para(value = "comment_id", defaultValue = "0") int commentid);
}
