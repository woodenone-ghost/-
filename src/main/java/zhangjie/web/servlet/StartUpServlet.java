package zhangjie.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import zhangjie.cache.EntityConfigCache;

public class StartUpServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1178842095473333245L;

	private static final Logger logger = Logger.getLogger(StartUpServlet.class);

	public void init() throws ServletException {
		super.init();
		
		logger.info("--系统初始化开始--");
		// 初始化缓存
		EntityConfigCache.getInstance().init();

		logger.info("--系统初始化完成--");
	}

	public void destroy() {
		logger.info("--系统停止--");
	}

}
