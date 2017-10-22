package ou.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import ou.dao.UserDao;
import ou.entity.User;
import ou.utils.DaoUtils;
/**
 * User的Dao层实现类
 * 执行数据库CURD语句
 * C(Create)/R（Read）/U(update)/D(deletess)
 * @author Administrator
 *
 */
public class UserDaoImpl implements UserDao {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	/**
	 * 为了让sql语句看得不那么繁琐，建议使用dbUtil框架简化sql步骤
	 */
	
	/**
	 * 使用邮箱作为"用户名"注册的业务逻辑
	 */
	public int registEmail(User user) {
		try {
			/*//1.获取数据库连接
			conn = DaoUtils.getConnection();
			//2.编写sql语句
			String sql = "insert into user" +
					"(email,password)" +
					"values" +
					"(?,?)";
			//3.预编译sql语句
			ps = conn.prepareStatement(sql);
			//4.为占位符赋值
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			//5.执行语句,并获取影响行数
			int row = ps.executeUpdate();
			//6.返回影响行数
			return row;*/
			
			//1.创建QueryRunner对象
			QueryRunner qr = new QueryRunner(DaoUtils.getPool());
			
			//2.编写sql语句
			String sql = "insert into user"
					+ "(email,password)"
					+ "values"
					+ "(?,?)";
			
			//3.执行sql语句，获取影响行数
			int row = qr.update(sql, user.getEmail(),user.getPassword());
			
			//4.返回影响行数
			return row;
		} catch (SQLException e) {
			e.printStackTrace();
			//7.注册失败
			return 0;
		}
		
		
		
	}

	/**
	 * 使用手机号作为"用户名"登录的注册方法
	 */
	public int registPhone(User user) {
		try {
			//1.创建QueryRunner对象
			QueryRunner qr = new QueryRunner(DaoUtils.getPool());
			
			//2.编写sql语句
			String sql = "insert into user"
					+ "(phone,password)"
					+ "values"
					+ "(?,?)";
			
			//3.执行sql语句，获取影响行数
			int row = qr.update(sql, user.getPhone(),user.getPassword());
			
			//4.返回影响行数
			return row;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
	}

}
