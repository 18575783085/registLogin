package ou.factory;

import ou.utils.PropUtils;

/**
 * 通用型工厂类
 * @author Administrator
 *
 */
public class BasicFactory {
	private BasicFactory(){}
	
	/**
	 * 获取通用工厂类实例
	 * @return
	 */
	public static BasicFactory getBasicFactory(){
		return new BasicFactory();
	}
	
	/**
	 * 通过传入的参数来获取properties配置文件中对应的value值
	 * @param key
	 * @return
	 * T:UserDao
	 * Class<T>:UserDao.class
	 */
	public <T> T getInstance(Class intfclz){
		/*
		 * UserDao.class -> "UserDao"
		 * getName() -> "cn.ou.dao.UserDaoImpl"
		 * getSimpleName() -> "UserDao"
		 */
		String intfName = intfclz.getSimpleName();
		
		//通过key来获取配置文件对应的value参数
		String value = PropUtils.getProperty(intfName);
		
		try {
			//通过类加载字节码文件来获取value的实例
			Class clz = Class.forName(value);
			
			//返回类的实例对象
			return (T) clz.newInstance();
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
