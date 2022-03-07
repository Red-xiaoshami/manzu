package com.hb.web.api;

import com.jfinal.core.paragetter.Para;

public interface TagApi {
	/**
	 * 标签查询
	 * 
	 * @param type
	 *            0：全部，1：漫画，2：小说
	 */
	public void query(@Para(value = "type", defaultValue = "1") int type);
}
