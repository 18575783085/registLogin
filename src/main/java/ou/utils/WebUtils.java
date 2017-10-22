package ou.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 检验非空
 * @author Administrator
 *
 */
public class WebUtils {
	private WebUtils(){}
	
	/**
	 * 判断字符串是否为空 ( null 或 "")
	 * @param str
	 * @return true : 表示字符串为空
	 */
	public static boolean isNull(String str){
		boolean res = str == null || "".equals(str);
		return res;
	}

	
}
