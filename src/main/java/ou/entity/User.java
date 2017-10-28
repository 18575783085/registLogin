package ou.entity;

import ou.exception.MsgException;
import ou.utils.WebUtils;

/**
 * 用户信息类
 * @author Administrator
 *
 */
public class User {
	/**
	 * 用户id
	 */
	private int id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 用户密码
	 */
	private String password;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 确认密码
	 */
	private String password2;
	/**
	 * 图片验证码
	 */
	private String valistr;
	/**
	 * 短信验证码
	 */
	private String smsvalistr;

	
	/**
	 * 手机注册：非空和数据校验方法
	 * @throws MsgException
	 */
	public void checkPhone() throws MsgException{
		//手机注册：非空校验
		if(WebUtils.isNull(phone)){
			//如果手机号码为空，跳转回注册页面
			throw new MsgException("手机号码不能为空！");
		}
		if(WebUtils.isNull(smsvalistr)){
			//如果短信验证码为空，跳转回注册页面
			throw new MsgException("短信验证码不能为空！");
		}
		if (WebUtils.isNull(password)) {
			//如果密码为空，跳转回注册页面
			throw new MsgException("密码不能为空！");
		}
		if(WebUtils.isNull(password2)){
			//如果确认密码为空，跳转回注册页面
			throw new MsgException("确认密码不能为空！");
		}
		//3.格式校验
		//3.1手机号码格式校验
		//regex:^1[3|8|5][0-9]{9}$
		String regex = "^1[3|5|8][0-9]{9}$";
		if(!phone.matches(regex)){
			//手机号码格式不正确,跳转回注册页面
			throw new MsgException("手机号码格式不正确！");
		}
		//3.2.两次密码校验
		if(!password.equals(password) || password != password2){
			//两次密码不匹配，跳转回注册页面
			throw new MsgException("两次密码不匹配！");
		}
	}
	
	
	/**
	 * 邮箱注册：非空和数据校验
	 * @throws MsgException
	 */
	public void checkEmail() throws MsgException{
		//2.非空判断
		if(WebUtils.isNull(email)){
			//如果邮箱为空,跳转到注册页面显示警告语
			throw new MsgException("alert('邮箱不能为空！')");
		}
		if(WebUtils.isNull(valistr)){
			//如果图片验证码为空，跳转到注册页面
			throw new MsgException("alert('图片验证码不能为空！')");
		}
		if(WebUtils.isNull(password)){
			//如果密码为空，跳转到注册页面
			throw new MsgException("alert('密码不能为空！')");
		}
		if(WebUtils.isNull(password2)){
			//如果确认密码为空，跳转到注册页面
			throw new MsgException("alert('确认密码不能为空！')");
		}
		
		//3.格式校验
		//3.1.判断邮箱格式是否正确
		//regex:^\\w+@\\w+(\\.[a-z]+)+$
		String regex = "^\\w+@\\w+(\\.[a-z]+)+$";
		if(!email.matches(regex)){
			throw new MsgException("邮箱格式不正确！");
		}
		
		//3.2.密码是否匹配
		if(!password.equals(password2) || password != password2){
			throw new MsgException("两次密码不一致！");
		}
	}
	
	
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getValistr() {
		return valistr;
	}
	public void setValistr(String valistr) {
		this.valistr = valistr;
	}
	public String getSmsvalistr() {
		return smsvalistr;
	}
	public void setSmsvalistr(String smsvalistr) {
		this.smsvalistr = smsvalistr;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
