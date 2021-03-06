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

	/**
	 * 根据邮箱和密码实现用户登录
	 * @param username ：邮箱
	 * @param password ：用户输入的密码
	 * @return true:返回User类对象，反之返回null
	 */
	public User loginEmail(String username, String password);

	/**
	 * 根据手机号码和密码实现用户登录
	 * @param username ：手机号码
	 * @param password ：密码
	 * @return true:返回User类对象，反之返回null
	 */
	public User loginPhone(String username, String password);

	/**
	 * 根据用户名和密码实现用户登录
	 * @param username ：用户名
	 * @param password ：密码
	 * @return true:返回User类对象，反之返回null
	 */
	public User loginUser(String username, String password);

}
