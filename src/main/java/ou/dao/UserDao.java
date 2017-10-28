package ou.dao;

import ou.entity.User;

/**
 * User的Dao层接口
 * @author Administrator
 *
 */
public interface UserDao {

	/**
	 * 注册用户信息
	 * @param email ：邮箱（作为登录入口）
	 * @param password ：用户密码
	 * @return 返回影响行数
	 */
	public int registEmail(User user);

	/**
	 * 注册用户信息
	 * @param phone ： 手机号码（作为登录入口）
	 * @param password ： 用户密码
	 * @return 返回影响行数
	 */
	public int registPhone(User user);

	/**
	 * 根据邮箱和密码实现用户登录
	 * @param username 邮箱
	 * @param password 密码
	 * @return true:返回User类对象，反之返回null
	 */
	public User loginEmail(String username, String password);

	/**
	 * 根据手机号码和密码实现用户登录
	 * @param username 手机号码
	 * @param password 密码
	 * @return true:返回User类对象，反之返回null
	 */
	public User loginPhone(String username, String password);

	/**
	 * 根据用户名和密码实现用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return true:返回User类对象，反之返回null
	 */
	public User loginUser(String username, String password);

}
