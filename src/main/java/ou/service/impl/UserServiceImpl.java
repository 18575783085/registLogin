package ou.service.impl;

import ou.dao.UserDao;
import ou.dao.impl.UserDaoImpl;
import ou.entity.User;
import ou.factory.BasicFactory;
import ou.factory.UserDaoFactory;
import ou.service.UserService;
/**
 * User的业务层实现类
 * @author Administrator
 *
 */
public class UserServiceImpl implements UserService {

	//创建Dao层对象
	//UserDao userDao = new UserDaoImpl();
	//解耦---创建工厂模式（先为每一个业务层创建一个单独的工厂类）
	//UserDao userDao = UserDaoFactory.getUserDaoFactory().getInstance();
	//UserDao userDao = BasicFactory.getBasicFactory().getInstance("UserDao");
	UserDao userDao = BasicFactory.getBasicFactory().getInstance(UserDao.class);
	/**
	 * 调用Dao层的注册邮箱登录方法
	 */
	public boolean registEmail(User user) {
		int row =  userDao.registEmail(user);
		return row > 0;
	}


	/**
	 * 调用Dao层的注册手机号码登录方法
	 */
	public boolean registPhone(User user) {
		int row = userDao.registPhone(user);
		return row > 0;
	}


	/**
	 * 调用Dao层的邮箱登录方法
	 */
	public User loginEmail(String username, String password) {
		return userDao.loginEmail(username,password);
	}


	/**
	 * 调用Dao层的手机号码登录方法
	 */
	public User loginPhone(String username, String password) {
		return userDao.loginPhone(username,password);
	}


	/**
	 * 调用Dao层的用户名登录方法
	 */
	public User loginUser(String username, String password) {
		return userDao.loginUser(username,password);
	}


	

}
