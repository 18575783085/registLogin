package ou.factory;

import ou.dao.UserDao;
import ou.utils.PropUtils;

/**
 * UserDao工厂类
 * @author Administrator
 *
 */
public class UserDaoFactory {
	
	//将构造方法私有化（单例模式）
	private UserDaoFactory(){}
	
	/**
	 * 获取UserDao工厂类实例
	 * @return
	 */
	public static UserDaoFactory getUserDaoFactory(){
		return new UserDaoFactory();
	}
	
	/**
	 * 通过UserDao接口来获取UserDaoImpl实现类
	 * @return
	 */
	public UserDao getInstance(){
		//获取配置文件中对应的value参数
		 String value = PropUtils.getProperty("UserDao");
		 
		 try {
			 //通过类加载字节码文件来获取该value值的实现
			Class clz = Class.forName(value);
			
			//返回字节码文件
			return (UserDao) clz.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		 
		 return null;
	}

}
