package ou.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import ou.entity.User;
import ou.factory.BasicFactory;
import ou.service.UserService;
import ou.utils.WebUtils;
/**
 * 实现用户名/邮箱/手机号码登录
 * @author Administrator
 *
 */
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.接收参数
		//1.1获取用户名/邮箱/手机号码参数
		String username = request.getParameter("username");
		
		//1.2获取密码
		String password = request.getParameter("password");
		
		//1.3获取'记住用户名'按钮参数
		String remname = request.getParameter("remname");
		
		//2.非空校验
		//2.1判断用户输入框是否为空
		if(WebUtils.isNull(username)){
			//用户输入框为空
			request.setAttribute("msg", "用户名不能为空！");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		//2.2判断密码输入框是否为空
		if(WebUtils.isNull(password)){
			//密码输入框为空
			request.setAttribute("msg", "密码不能为空！");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		//3.数据校验                         
		String emailRegex = "^\\w+@\\w+(\\.+[a-z]+)+$";//邮箱正则表达式
		String phoneRegex = "^[1][3|5|8][0-9]{9}$";//手机正则表达式
		
		
		//创建业务层对象
		UserService userService = BasicFactory.getBasicFactory().getInstance(UserService.class);
		
		//备注：用User实体类来修饰，是为了登录正确后，获取用户名来作为cookie使用
		//3.1判断是不是邮箱作为登录入口
		if(username.matches(emailRegex)){
			//3.1.1是邮箱
			//3.1.2判断邮箱和密码信息是否匹配正确
			User resultEmail = userService.loginEmail(username,password);
			if(resultEmail != null){
				//匹配成功
				//判断是否记住用户名(判断复选按钮的选择属性)
				if("true".equals(remname)){
					//创建cookie对象---->对用户名进行url编码
					Cookie cookie = new Cookie("remname", URLEncoder.encode(resultEmail.getUsername(), "UTF-8"));
					//设置路径
					cookie.setPath(request.getContextPath()+"/");
					//设置存活时间:30天
					cookie.setMaxAge(3600*24*30);
					//添加cookie
					response.addCookie(cookie);
					
				}else {
					//如果没有勾选‘记住用户名’----"删除"cookie
					//创建cookie对象
					Cookie cookie = new Cookie("remname", "");
					//设置一样的路径
					cookie.setPath(request.getContextPath()+"/");
					//设置存活时间:0
					cookie.setMaxAge(0);
					//添加cookie
					response.addCookie(cookie);
				}
				
				//把用户信息存进session域中
				request.getSession().setAttribute("user", resultEmail);
				
				
				//登录成功--->重定向跳转到主页
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			}else {
				//信息错误--->转发回到登录页面
				request.setAttribute("msg", "不好意思，您的用户名或密码错误！");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				return;
			}
			
		}else if (username.matches(phoneRegex)) {//判断是否手机号作为登录入口
			//3.1.1是手机号码
			//3.1.2判断手机号码和密码信息是否匹配正确
			User resultPhone = userService.loginPhone(username,password);
			if(resultPhone != null){
				//匹配成功
				//判断是否记住用户名
				if("true".equals(remname)){
					//勾选了
					//创建cookie对象---对用户名进行url编码
					Cookie cookie = new Cookie("remname", URLEncoder.encode(resultPhone.getUsername(), "UTF-8"));
					cookie.setPath(request.getContextPath()+"/");
					cookie.setMaxAge(3600*24*30);
					response.addCookie(cookie);
					
				}else {
					//取消记住用户名
					//删除cookie对象
					Cookie cookie = new Cookie("remname", "");
					cookie.setPath(request.getContextPath()+"/");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				
				//把用户对象存进session域中
				request.getSession().setAttribute("user", resultPhone);
				
				
				//登录成功----->重定向跳转到主页
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			}else {
				//信息错误--->转发回到登录页面
				request.setAttribute("msg", "不好意思，您的用户名或密码错误！");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				return;
			}
			
		}else {//剩下一种情况就是：使用用户名作为登录入口
			//3.1.1是用户名
			//3.1.2判断手机号码和密码信息是否匹配正确
			User resultUser = userService.loginUser(username,password);
			
			if (resultUser != null) {
				//匹配成功
				//判断是否勾选记住用户名
				if ("true".equals(remname)) {
					//勾选了
					//创建cookie对象---对用户名进行url编码
					Cookie cookie = new Cookie("rename", URLEncoder.encode(resultUser.getUsername(), "UTF-8"));
					cookie.setPath(request.getContextPath()+"/");
					cookie.setMaxAge(60*60*24*30);
					response.addCookie(cookie);
					
				}else {
					//取消记住用户名
					//删除cookie对象
					Cookie cookie = new Cookie("remname", "");
					cookie.setPath(request.getContextPath()+"/");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				
				//把用户对象存进session域中
				request.getSession().setAttribute("user", resultUser);
				
				//登录成功--->重定向跳转到主页
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			}else {
				//信息错误---->转发回到登录页面
				request.setAttribute("msg", "不好意思，您的用户名或密码错误！");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				return;
			}
			
		}
		
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
