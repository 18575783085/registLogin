package ou.factory;

import ou.service.UserService;
import ou.utils.PropUtils;

/**
 * UserService业务工厂类
 * @author Administrator
 *
 */
public class UserServiceFactory {
	private UserServiceFactory(){}
	
	/**
	 * 获取UserService工厂实例
	 * @return
	 */
	public static UserServiceFactory getUserServiceFactory(){
		return new UserServiceFactory();
	}
	
	/**
	 * 通过UserService接口来获取UserServiceImpl实现类
	 * @return
	 */
	public UserService getInstance(){
		//获取配置文件中对应的value参数
		String value = PropUtils.getProperty("UserDao");
		
		try {
			//通过类加载字节码文件来获取该value的实例
			Class clz = Class.forName(value);
			
			//返回类的实例对象
			return (UserService) clz.newInstance();
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
