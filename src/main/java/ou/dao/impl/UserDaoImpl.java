package ou.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

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
	 * 使用手机号作为"用户名"登录的注册方法的业务逻辑
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

	/**
	 * 通过邮箱和密码查找用户信息的业务逻辑
	 */
	public User loginEmail(String username, String password) {
		//1.创建QueryRunner对象--->获取连接池对象
		QueryRunner qr = new QueryRunner(DaoUtils.getPool());
		
		//2.编写sql语句 
		String sql = "select * from user where email=? and password=?";
		
		try {
			//3.执行sql语句，获取结果对象
			User userEmail = qr.query(sql, new BeanHandler<User>(User.class),username,password);
			
			//4.返回结果对象
			return userEmail;
		} catch (SQLException e) {
			e.printStackTrace();
			
			//4.查询不到返回null
			return null;
		}
		
	}

	/**
	 * 通过手机号码和密码查找用户信息的业务逻辑
	 */
	public User loginPhone(String username, String password) {
		//1.创建QueryRunner对象，来获取连接池对象
		QueryRunner qr = new QueryRunner(DaoUtils.getPool());
		
		//2.编写sql语句
		String sql = "select * from user where phone=? and password=?";
		
		try {
			//3.执行sql语句，获取结果对象
			User userPhone = qr.query(sql, new BeanHandler<User>(User.class),username,password);
			
			//4.返回结果对象
			return userPhone;
			
		} catch (SQLException e) {
			e.printStackTrace();
			//4.查无结果则返回null
			return null;
		}
	}

	
	/**
	 * 通过用户名和密码查询用户信息的业务逻辑
	 */
	public User loginUser(String username, String password) {
		//1.创建QueryRunner对象，来获取连接池对象
		QueryRunner qr = new QueryRunner(DaoUtils.getPool());
		
		//2.编写sql语句
		String sql = "select * from user where username=? and password=?";
		
		try {
			//3.执行sql语句，获取结果对象
			User userName = qr.query(sql, new BeanHandler<User>(User.class),username,password);
			
			//4.返回结果对象
			return userName;
			
		} catch (SQLException e) {
			e.printStackTrace();
			//4.查无结果则返回null
			return null;
		}
	}

}
