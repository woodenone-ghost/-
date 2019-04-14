package zhangjie.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextUtil implements ApplicationContextAware, DisposableBean {

	private static ApplicationContext ctx = null;

	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		// TODO Auto-generated method stub
		ctx = arg0;
	}

	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		ctx = null;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		AssertUtil.argIsNotNull(ctx, "Spring容器未初始化");
		Object o = ctx.getBean(name);
		AssertUtil.argIsNotNull(o, "未找到指定Bean：" + name);
		return (T) o;
	}

}
