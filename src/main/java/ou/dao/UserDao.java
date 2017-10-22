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

}
