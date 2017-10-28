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
import ou.factory.BasicFactory;
import ou.factory.UserServiceFactory;
import ou.service.UserService;
import ou.service.impl.UserServiceImpl;
import ou.utils.DaoUtils;
import ou.utils.WebUtils;
/**
 * 邮箱注册功能
 * @author Administrator
 *
 */
public class RegistServlet1 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//解决乱码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		//1.接收参数
		//创建用户对象
		User user = new User();
		
		try {
			//获取参数
			BeanUtils.populate(user, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		//2.数据校验
		try {
			user.checkEmail();
		} catch (MsgException e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
		}
		
		//3.图片验证码
		//3.1.获取图片验证码
		Object valistr = request.getSession().getAttribute("valistr");
		
		//3.2判断验证码是否匹配
		if(user.getValistr() != valistr || !user.getValistr().equals(valistr)){
			//不匹配，请求转发到注册页面
			request.setAttribute("msg", "图片验证码不匹配！");
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		}
		
		
		
		//TODO 4.判断是否重复提交,令牌----防止由于网络延迟，多次刷新导致重复导入数据
		
		
		//进行MVC分层
		//5.创建user业务层
		//UserService userService = new UserServiceImpl();
		//解耦---创建工厂模式（先为每一个业务层创建一个单独的工厂类）
		//UserService userService = UserServiceFactory.getUserServiceFactory().getInstance();
		//还有小问题---如果为每一个业务都要单独创建一个工厂类，那就要创建很多很多工厂类，那为什么不单独弄一个通用的工厂来被调用呢
		//UserService userService = (UserService) BasicFactory.getBasicFactory().getInstance("UserServie");
		/*
		 * 还有一个小小缺陷----因为传入的参数是一个字符串，所以假如填错也不会冒红；还有就是获取实现类都需要强转类型
		 * 解决方案----传入的参数是一个类字节码----使用泛型
		 */
		UserService userService = BasicFactory.getBasicFactory().getInstance(UserService.class);
		
		
		//5.1调用业务层的注册方法（邮箱作为登录入口）
		boolean result = userService.registEmail(user);
		
		//把用户实体类存进session域中
		request.getSession().setAttribute("user", user);
		
		//5.2注册用户
		if(result){
			//注册成功
			//5.3把用户实体类存进session域中
			request.getSession().setAttribute("user", user);
			
			//6.注册成功，定时刷新跳转到登陆页面
			response.getWriter().write("<font style='color:red'>恭喜您注册成功，3秒后跳转到登陆页面</font>");
			response.setHeader("refresh", "3;url="+request.getContextPath()+"/login.jsp");
		}else {
			//注册失败
			request.setAttribute("msg", "注册失败");
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
		}
		
			
		
		
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
