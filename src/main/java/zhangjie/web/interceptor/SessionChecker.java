package zhangjie.web.interceptor;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import zhangjie.constants.Constants;
import zhangjie.model.UserInformation;

public class SessionChecker extends HandlerInterceptorAdapter {
	public static Logger logger = Logger.getLogger(SessionChecker.class);

	private Set<String> ignoredURLSet = new HashSet<String>();// 忽略名单
	private Set<String> managerPermissionSet = new HashSet<String>();// 管理员权限url
	private Set<String> buyerPermissionSet = new HashSet<String>();// 买家权限url
	private Set<String> sellerPermissionSet = new HashSet<String>();// 卖家权限url
	private static final String EXP_RESP = "{\"code\":\"99\"}";
	private static final String errorMessage = "{\"code\":\"ZZ\",\"errorMessage\":\"操作失败！\"}";

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String reqPath = request.getServletPath();// 请求地址
		boolean ajaxReq = request.getHeader("X-Requested-With") != null;// 是否为ajax请求
		try {
			logger.debug("reqPath: " + reqPath);
			HttpSession session = request.getSession();
			MDC.put(Constants.LOG_SID, session.getId());
			// 请求的url不在忽略名单内，则主要检查会话是否过期
			if (!ignoredURLSet.contains(reqPath)) {
				if (session.getAttribute(Constants.SESSION_KEY_USER) == null) {
					if (ajaxReq) {
						response.setContentType("application/json;charset=UTF-8");
						PrintWriter wr = response.getWriter();
						wr.write(EXP_RESP);
						wr.flush();
					} else {
						response.sendRedirect(request.getContextPath() + "/loginout");
					}
					return false;
				} else {// 已登录
					UserInformation userInformation = (UserInformation) session
							.getAttribute(Constants.SESSION_KEY_USER);
					MDC.put(Constants.LOG_UID, userInformation.getAccount());

					String identity = userInformation.getIdentity();// 获得身份
					if (identity.equals("buyer")) {// 身份为买家
						if (!buyerPermissionSet.contains(reqPath)) {// 请求的url不在权限内
							if (ajaxReq) {
								response.setContentType("application/json;charset=UTF-8");
								PrintWriter wr = response.getWriter();
								wr.write(errorMessage);
								wr.flush();
							} else {
								response.sendRedirect(request.getContextPath() + "/noPermission");
							}
							return false;
						}
					} else if (identity.equals("seller")) {// 身份为卖家
						if (!sellerPermissionSet.contains(reqPath)) {
							if (ajaxReq) {
								response.setContentType("application/json;charset=UTF-8");
								PrintWriter wr = response.getWriter();
								wr.write(errorMessage);
								wr.flush();
							} else {
								response.sendRedirect(request.getContextPath() + "/noPermission");
							}
							return false;
						}
					} else if (identity.equals("manager")) {// 身份为管理员
						if (!managerPermissionSet.contains(reqPath)) {
							if (ajaxReq) {
								response.setContentType("application/json;charset=UTF-8");
								PrintWriter wr = response.getWriter();
								wr.write(errorMessage);
								wr.flush();
							} else {
								response.sendRedirect(request.getContextPath() + "/noPermission");
							}
							return false;
						}
					} else {
						response.sendRedirect(request.getContextPath() + "/noPermission");
						return false;
					}

				}
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(ajaxReq + "访问异常", e);
			response.sendRedirect("/loginout");
			return false;
		}
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		MDC.remove(Constants.LOG_SID);
		MDC.remove(Constants.LOG_UID);
		super.postHandle(request, response, handler, modelAndView);
	}

	public void setIgnoredUrls(String ignoredUrls) {
		String[] urls = ignoredUrls.split(",");
		for (String url : urls) {
			ignoredURLSet.add(url.trim());
		}
		logger.info("ignoredURLSet：" + ignoredURLSet);
	}

	public void setManagerPermissions(String managerPermissions) {
		String[] urls = managerPermissions.split(",");
		for (String url : urls) {
			managerPermissionSet.add(url.trim());
		}
		logger.info("managerPermissionSet：" + managerPermissionSet);
	}

	public void setBuyerPermissions(String buyerPermissions) {
		String[] urls = buyerPermissions.split(",");
		for (String url : urls) {
			buyerPermissionSet.add(url.trim());
		}
		logger.info("buyerPermissionSet：" + buyerPermissionSet);
	}

	public void setSellerPermissions(String sellerPermissions) {
		String[] urls = sellerPermissions.split(",");
		for (String url : urls) {
			sellerPermissionSet.add(url.trim());
		}
		logger.info("sellerPermissionSet：" + sellerPermissionSet);
	}
}
