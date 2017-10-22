<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>

	<head lang="en">
		<meta charset="UTF-8">
		<title>注册</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="format-detection" content="telephone=no">
		<meta name="renderer" content="webkit">
		<meta http-equiv="Cache-Control" content="no-siteapp" />

		<link rel="stylesheet" href="${appPath}/AmazeUI-2.4.2/assets/css/amazeui.min.css" />
		<link href="${appPath}/css/dlstyle.css" rel="stylesheet" type="text/css">
		<script src="${appPath}/AmazeUI-2.4.2/assets/js/jquery.min.js"></script>
		<script src="${appPath}/AmazeUI-2.4.2/assets/js/amazeui.min.js"></script>

<!-- 		<script>
			//不勾选协议，不能提交注册
			$(function(){
				//获取协议复选框
				var $reader = $("#reader-me");
				var reader = $reader.get(0);
				//获取注册按钮
				var registbtn = $("input[type=sumbit]");
				
				$reader.click(function(){
					if(reader.checked){
						registbtn.removeAttr("disabled");
						
					}else{
						registbtn.attr("disabled",true);
						
					}
				});
	
			});
		</script> -->
	</head>

	<body>

		<div class="login-boxtitle">
			<a href="home/demo.html"><img alt="" src="${appPath}/images/logobig.png" /></a>
		</div>

	<div class="res-banner">
		<div class="res-main">
			<div class="login-banner-bg">
				<span></span><img src="${appPath}/images/big.jpg" />
			</div>
			<div class="login-box">

				<div class="am-tabs" id="doc-my-tabs">
					<ul class="am-tabs-nav am-nav am-nav-tabs am-nav-justify">
						<li class="am-active"><a href="">邮箱注册</a>
						</li>
						<li><a href="">手机号注册</a>
						</li>
					</ul>

					<div class="am-tabs-bd">
						<div class="am-tab-panel am-active">
							<form action="<%=request.getContextPath() %>/servlet/RegistServlet1" method="post">
								<span style="color:red">
									<%=
										request.getAttribute("msg") == null ? "":request.getAttribute("msg")
									 %>
								</span>
								<div class="user-email">
									<label class="lab" for="email"><i class="am-icon-envelope-o"></i>
									</label> <input class="inp" type="email" name="email" id="email" placeholder="请输入邮箱账号"
										value="<%= request.getParameter("email")== null ? "":request.getParameter("email") %>"
										>

								</div>
								<div class="verification">
									<label class="lab" for="code2"><i class="am-icon-code-fork"></i>
									</label> <input class="inp" type="tel" name="valistr" id="code2" placeholder="请输入验证码"
										value="<%= request.getParameter("valistr") == null ? "":request.getParameter("valistr") %>"
										>
									<img alt="加载失败" src="${appPath}/images/yzm.jpg" width="" height="" id="verification">
								</div>
								<div class="user-pass">
									<label class="lab" for="password"><i class="am-icon-lock"></i>
									</label> <input class="inp" type="password" name="password" id="password"
										placeholder="设置密码"
										value="<%= request.getParameter("password") == null ? "":request.getParameter("password") %>"
										>
								</div>
								<div class="user-pass">
									<label class="lab" for="passwordRepeat"><i class="am-icon-lock"></i>
									</label> <input class="inp" type="password" name="password2" id="passwordRepeat"
										placeholder="确认密码"
										value="<%= request.getParameter("password2") == null ? "":request.getParameter("password2") %>"
										>
								</div>

							<div class="login-links">
								<label for="reader-me"> <input id="reader-me"
									type="checkbox"> 点击表示您同意商城《服务协议》 </label>
							</div>
							<div class="am-cf">
								<input type="submit" name="" value="注册"
									class="am-btn am-btn-primary am-btn-sm am-fl" >
							</div>
							
							</form>


						</div>

						<div class="am-tab-panel">
							<form action="<%=request.getContextPath() %>/servlet/RegistServlet2" method="post">
								<span style="color:red">
									<%=
										request.getAttribute("msg") == null ? "":request.getAttribute("msg")
									 %>
								</span>
								<div class="user-phone">
									<label class="lab" for="phone"><i
										class="am-icon-mobile-phone am-icon-md"></i>
									</label> <input class="inp" type="tel" name="phone" id="phone" placeholder="请输入手机号"
											value="<%= request.getParameter("phone") == null ? "":request.getParameter("phone")%>"
											>
								</div>
								<div class="verification">
									<label class="lab" for="code"><i class="am-icon-code-fork"></i>
									</label> <input class="inp" type="tel" name="smsvalistr" id="code" placeholder="请输入验证码"
											value="<%= request.getParameter("smsvalistr") == null ? "":request.getParameter("smsvalistr") %>"
											>
									<a class="btn" href="javascript:void(0);"
										onclick="sendMobileCode();" id="sendMobileCode"> <span
										id="dyMobileButton">获取</span>
									</a>
								</div>
								<div class="user-pass">
									<label class="lab" for="password"><i class="am-icon-lock"></i>
									</label> <input class="inp" type="password" name="password" id="password"
										placeholder="设置密码"
										value="<%= request.getParameter("password") == null ? "":request.getParameter("password") %>"
										>
								</div>
								<div class="user-pass">
									<label class="lab" for="passwordRepeat"><i class="am-icon-lock"></i>
									</label> <input class="inp" type="password" name="password2" id="passwordRepeat"
										placeholder="确认密码"
										value="<%= request.getParameter("password2") == null ? "":request.getParameter("password2") %>"
										>
								</div>
							<div class="login-links">
								<label for="reader-me"> <input id="reader-me"
									type="checkbox"> 点击表示您同意商城《服务协议》 </label>
							</div>
							<div class="am-cf">
								<input type="submit" name="" value="注册"
									class="am-btn am-btn-primary am-btn-sm am-fl" >
									
							</div>
							
							</form>

							<hr>
						</div>

						<script>
							$(function() {
								$('#doc-my-tabs').tabs();
							})
						</script>

					</div>
				</div>

			</div>
		</div>
	</div>
	<div class="footer ">
						<div class="footer-hd ">
							<p>
								<a href="# ">恒望科技</a>
								<b>|</b>
								<a href="# ">商城首页</a>
								<b>|</b>
								<a href="# ">支付宝</a>
								<b>|</b>
								<a href="# ">物流</a>
							</p>
						</div>
						<div class="footer-bd ">
							<p>
								<a href="# ">关于恒望</a>
								<a href="# ">合作伙伴</a>
								<a href="# ">联系我们</a>
								<a href="# ">网站地图</a>
								<em>© 2015-2025 Hengwang.com 版权所有</em>
							</p>
						</div>
					</div>
	</body>

</html>