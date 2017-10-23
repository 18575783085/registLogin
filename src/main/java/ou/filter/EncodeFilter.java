package ou.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
/**
 * 乱码过滤器---统一设置解决全站乱码
 * 1、直接统一设置字符集，而不需要每一个servlet都单独设置字符集解码
 * @author Administrator
 *
 */
public class EncodeFilter implements Filter {
	
	private String encode;
	
	public void init(FilterConfig filterConfig) throws ServletException {
		//从web.xml配置文件中的ServletConfig获取初始化参数
		encode = filterConfig.getInitParameter("encode");
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//处理响应正文乱码
		response.setContentType("UTF-8");
		
		//装饰者模式
		MyHttpSR myHttpSR = new MyHttpSR((HttpServletRequest)request);
		
		//放行
		chain.doFilter(myHttpSR, response);
		
	}
	
	
	class MyHttpSR extends HttpServletRequestWrapper{
		
		private Map<String, String[]> map = null;
		
		//声明一个被装饰的对象 变量
		private HttpServletRequest request;
		
		public MyHttpSR(HttpServletRequest request) {
			super(request);
			this.request = request;
		}
		
		public Map<String, String[]> getParameterMap(){
			if(map == null){
				//判断提交请求的方法是GET、POST、还是其它的五种提交
				if("POST".equals(request.getMethod())){
					//处理乱码
					try {
						request.setCharacterEncoding(encode);
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					map = request.getParameterMap();
					return map;
				}else if ("GET".equals(request.getMethod())) {
					//获取提交数据
					map = request.getParameterMap();
					
					//遍历处理每一个表单项中的乱码
					for(Map.Entry<String, String[]> entry:map.entrySet()){
						//获取value的值
						String values[] = entry.getValue();
						
						//遍历出数组中的乱码
						for (int i = 0; i < values.length; i++) {
							/* 获取一个参数--->先将它转化为西欧编码的字节数组---->然后再通过utf-8编码变成字符串
							 * 譬如：byte[] data = values[i].getBytes("ISO8859-1")
							 * 		String value = new String(data,"utf-8")
							 * 		
							 */
							try {
								values[i] = new String(values[i].getBytes("ISO8859-1"), "UTF-8");
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
							
						}
					}
					return map;
				}else {
					//其它五种提交方法，爱咋滴咋滴
					map = request.getParameterMap();
					return map;
				}
			}else {
				return map;
			}
		}

		/**
		 * 重写getParameterValues()方法
		 */
		public String[] getParameterValues(String name){
			return getParameterMap().get(name);
		}
		
		/**
		 * 重写getParameter()方法
		 */
		public String getParameter(String name){
			String values[] = getParameterValues(name);
			//如果不为空返回下标为0的参数，反之则返回空
			return values != null ? values[0] : "";
		}
		
	}

	public void destroy() {
	}

}
