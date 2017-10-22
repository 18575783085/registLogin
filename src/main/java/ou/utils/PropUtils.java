package ou.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * 加载并获取properties配置文件
 * @author Administrator
 *
 */
public class PropUtils {
	//创建properties私有化实例对象
	private static Properties p = new Properties();
	
	//将构造方法私有化（单例模式）
	private PropUtils(){}
	
	static{
		try {
			//获取properties文件
			p.load(PropUtils.class.getClass().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取properties对象
	 * @return 返回一个properties对象
	 */
	public static Properties getProp(){
		return p;
	}
	
	/**
	 * 获取配置文件中的参数
	 * @param key ：properties配置文件中的key
	 * @return 返回key对应的value参数值
	 */
	public static String getProperty(String key){
		return p.getProperty(key); 
	}
}
