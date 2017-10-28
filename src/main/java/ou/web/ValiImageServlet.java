package ou.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ou.utils.VerifyCode;
/**
 * 获取图片验证码---发送到浏览器
 * @author Administrator
 *
 */
public class ValiImageServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.控制浏览器不要缓存验证码图片
		response.setDateHeader("Expires", -1);
		response.setHeader("Cache-Control", "no-cache");
		
		//2.绘制一张验证码图片，并发送到浏览器
		VerifyCode verifyCode = new VerifyCode();
		verifyCode.drawImage(response.getOutputStream());
		
		//3.获取验证码
		String valistr = verifyCode.getCode();
		
		//4.创建session
		HttpSession session = request.getSession();
		
		//5.把验证码存进session域中
		session.setAttribute("valistr", valistr);
		

		System.out.println("图片验证码："+valistr);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
