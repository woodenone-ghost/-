package zhangjie.web.interceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import zhangjie.constants.Constants;

/**
 * 将页面上送来的_QRY_开头的查询参数，整合成一个map
 * 
 * @author 60569
 *
 */
public class QryParamCollector extends HandlerInterceptorAdapter {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(QryParamCollector.class);

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			final Map<String, String> qryParamMap = new HashMap<String, String>();
			Map<String, String[]> params = request.getParameterMap();
			if (params != null) {
				params.forEach(new BiConsumer<String, String[]>() {
					public void accept(String key, String[] values) {
						if (key.startsWith(Constants.QRY_PARAM_NM_PREFIX) && values != null && values.length == 1) {
							qryParamMap.put(key.substring(Constants.QRY_PARAM_NM_PREFIX_LEN), values[0].trim());
						}
					}
				});
			}
			// 将整合结果放到Request上下文中，供Controller使用
			if (!qryParamMap.isEmpty()) {
				request.setAttribute(Constants.QRY_PARAM_MAP, qryParamMap);
			}
			return true;
		} else {
			return super.preHandle(request, response, handler);
		}
	}
}
