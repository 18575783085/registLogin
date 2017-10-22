package ou.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 数据工具类
 * @author Administrator
 *
 */
public class DaoUtils {
	/**
	 * 创建连接池对象
	 */
	private static ComboPooledDataSource cpds = new ComboPooledDataSource();
	
	/**
	 * 获取连接池
	 * @return c3p0连接池
	 */
	public static ComboPooledDataSource getPool(){
		return cpds;
	}
	
	/**
	 * 获取数据库连接
	 * @return 数据库连接
	 * @throws SQLException 获取数据库连接失败
	 */
	public static Connection getConnection() throws SQLException{
		return cpds.getConnection();
	}

	/**
	 * 关闭数据库各种资源
	 * @param conn 归还数据库连接
	 * @param stat 关闭传输器
	 * @param rs 关闭结果集器
	 */
	public static void close(Connection conn,Statement stat,ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				rs = null;
			}
		}
		if(stat != null){
			 try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
 			} finally{
 				stat = null;
 			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
