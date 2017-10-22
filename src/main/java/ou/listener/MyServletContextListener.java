package ou.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 * 监听ServletContext的创建获取请求web应用路径
 * @author Administrator
 *
 */
public class MyServletContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		String path = sce.getServletContext().getContextPath();
		sce.getServletContext().setAttribute("appPath", path);

	}

	public void contextDestroyed(ServletContextEvent sce) {
		sce.getServletContext().removeAttribute("appPath");
	}

}
