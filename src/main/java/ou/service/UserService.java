package ou.service;

import ou.entity.User;

/**
 * User的业务层接口
 * @author Administrator
 *
 */
public interface UserService {

	/**
	 * 注册用户信息
	 * @param email :邮箱（作为登录入口）
	 * @param password ：用户密码
	 * @return true:表示注册成功 false:表示注册失败
	 */
	public boolean registEmail(User user);

	/**
	 * 注册用户信息
	 * @param phone ：手机号码（作为登录入口）
	 * @param password ：用户密码
	 * @return true：表示注册成功 false：表示注册失败
	 */
	public boolean registPhone(User user);

}
