package zhangjie.web.interceptor;

import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import zhangjie.entity.AjaxResult;
import zhangjie.exception.MyException;

/**
 * 全局异常处理器
 * 
 * @author 60569
 *
 */

public class ExceptionResolver extends SimpleMappingExceptionResolver {
	private static final Logger logger = Logger.getLogger(ExceptionResolver.class);

	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		if (handler instanceof HandlerMethod) {
			HandlerMethod hm = (HandlerMethod) handler;
			Method mt = hm.getMethod();
			String className = hm.getBean().getClass().getSimpleName();
			String methodName = mt.getName();
			logger.error("Exception occurred: " + className + "@" + methodName, ex);
			// 如果是Ajax请求，则返回Ajax格式的失败应答，否则跳转至异常信息展示页面
			if (AjaxResult.class.equals(mt.getReturnType())) {
				try {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter wr = response.getWriter();
					if (ex instanceof MyException) {
						wr.write(this.buildErrorResult(ex.getMessage()));
					} else {
						wr.write(this.buildErrorResult(null));
					}
					wr.flush();
					return null;
				} catch (Throwable t) {
					logger.error("build Ajax error failed!", t);
					// TODO: handle exception
				}
			}
		}
		if (ex instanceof MyException) {
			request.setAttribute("errorMessage", ex.getMessage());
		} else {
			request.setAttribute("errorMessage", "未知错误");
		}
		return new ModelAndView("common/error");
	}

	/**
	 * 构造Ajax异常应答
	 * 
	 * @param errorMessage
	 * @return
	 */
	private String buildErrorResult(String errorMessage) {
		StringBuilder errorResult = new StringBuilder("{\"code\":\"ZZ\",\"errorMessage\":\"");
		errorResult.append(StringUtils.isBlank(errorMessage) ? "未知错误" : errorMessage.trim());
		errorResult.append("\"}");
		return errorResult.toString();
	}
}
