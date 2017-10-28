package ou.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import ou.entity.User;
import ou.exception.MsgException;
import ou.factory.UserServiceFactory;
import ou.service.UserService;
import ou.service.impl.UserServiceImpl;
import ou.utils.DaoUtils;
import ou.utils.WebUtils;
/**
 * 手机号码注册功能
 * @author Administrator
 *
 */
public class RegistServlet2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//解决乱码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//1.创建用户对象
		User user = new User();
		try {
			//这个jar包用法不知道，但是只知道能把所有参数统一存进到一个map集合当中
			//注意：注册页面表单项的"name"属性名的值一定要跟User对象类中的属性名一致
			BeanUtils.populate(user, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
		//2.数据校验
		try {
			user.checkPhone();
		} catch (MsgException e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
		}
		
		//TODO 3短信验证码匹配
		
		
		
		
		//TODO 4.令牌----防止网络延迟，多次刷新导致数据重复导入
		
		
		//5.创建User业务层---进行MVC分层
		//UserService userService = new UserServiceImpl();
		//解耦---创建工厂模式（先为每一个业务层创建一个单独的工厂类）
		UserService userService = UserServiceFactory.getUserServiceFactory().getInstance();
		
		//5.1调用业务层的注册方法（手机号码作为登录入口）
		boolean result = userService.registPhone(user);
		
		
		//5.2用户注册
		if(result){
			//注册成功
			//5.3把用户实体类存进session域中
			request.getSession().setAttribute("user", user);
			
			//6.注册成功，定时刷新跳转到登陆页面
			response.getWriter().write("<font style='color:red'>恭喜您注册成功，3秒后跳转到登录页面</font>");
			response.setHeader("refresh", "3;url="+request.getContextPath()+"/login.jsp");
			
		}else {
			//注册失败
			request.setAttribute("msg", "注册失败");
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
			
		}
		
		
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
