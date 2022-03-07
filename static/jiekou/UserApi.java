package com.hb.web.api;

import com.jfinal.core.paragetter.Para;

public interface UserApi {

	/**
	 * 获取用户浏览记录(需要登录)
	 * 
	 * @param type
	 *            0：全部，1：漫画，2：小说
	 * @param page
	 *            页索引
	 * @param size
	 *            页大小
	 */
	public void browsed(@Para(value = "type", defaultValue = "1") int type, @Para(value = "page", defaultValue = "1") int page,
			@Para(value = "size", defaultValue = "20") int size);

	/**
	 * 获取用户收藏(需要登录)
	 * 
	 * @param type
	 *            0：全部，1：漫画，2：小说
	 * @param page
	 *            页索引
	 * @param size
	 *            页大小
	 */
	public void collections(@Para(value = "type", defaultValue = "1") int type, @Para(value = "page", defaultValue = "1") int page,
			@Para(value = "size", defaultValue = "20") int size);

	/**
	 * 消费
	 */
	public void consumes(@Para(value = "type", defaultValue = "0") int type, @Para(value = "page", defaultValue = "1") int page,
			@Para(value = "size", defaultValue = "20") int size);

	/**
	 * 获取用户订单列表
	 * 
	 * @param result
	 *            -1:为支付, 0:失败, 1:成功 2获取所有记录
	 * @param page页索引
	 * @param size页大小
	 */
	public void orders(@Para(value = "result", defaultValue = "2") int result, @Para(value = "page", defaultValue = "1") int page,
			@Para(value = "size", defaultValue = "20") int size);

	/**
	 * 获取打赏记录
	 * 
	 * @param page页索引
	 * @param size页大小
	 */
	public void rewards(@Para(value = "page", defaultValue = "1") int page, @Para(value = "size", defaultValue = "20") int size);

	/**
	 * 更新
	 */
	public void updated(@Para(value = "type", defaultValue = "0") int type, @Para(value = "date", defaultValue = "") String date,
			@Para(value = "page", defaultValue = "1") int page, @Para(value = "size", defaultValue = "20") int size);

	/**
	 * 手机登录,手机不存在则注册(默认只有3次验证的机会.3次验证失败就得重新发送短信,验证码则失效,为了防止有人恶意穷举)
	 * 
	 * @param mobile手机号码
	 * @param vcode验证码
	 */
	public void mlogin(@Para(value = "mobile", defaultValue = "") String mobile, @Para(value = "vcode", defaultValue = "") String vcode);

	/**
	 * 手机绑定 这个是为了让QQ登录的用户绑定手机号码
	 * 
	 * @param openid
	 *            QQ登录的openid
	 * @param mobile
	 *            手机号码
	 * @param vcode
	 *            短信验证码
	 */
	public void mbind(@Para(value = "openid", defaultValue = "") String openid, @Para(value = "mobile", defaultValue = "") String mobile,
			@Para(value = "vcode", defaultValue = "") String vcode);

	/**
	 * 发送短信(默认一个号码在10分钟内只能发送4条,否则会超限导致无法接收.一天内只能发送10条, 此为了防止有人盗用接口做短信轰炸)
	 * 
	 * @param action发送动作
	 *            login/reg 此为注册,并不查询手机号码是否存在. bind是为绑定手机.
	 *            假如数据库存在相同手机,则无法绑定.(一个账户只允许绑定一个手机)
	 * @param mobile发送号码
	 */
	public void sms(@Para(value = "action", defaultValue = "login") String action,
			@Para(value = "mobile", defaultValue = "") String mobile);

	/**
	 * 用户签到(必须登录) 12小时内只允许签到一次. 签到会返还50金币
	 */
	public void sign();

	/**
	 * 查询金额(必须登录)
	 */
	public void money();

	/**
	 * 获取充值列表
	 * 
	 * @param group,分组id.默认为1
	 */
	public void recharge(@Para(value = "group", defaultValue = "1") int group);
}
