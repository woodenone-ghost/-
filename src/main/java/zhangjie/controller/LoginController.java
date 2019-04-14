package zhangjie.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import zhangjie.cache.EntityConfigCache;
import zhangjie.constants.Constants;
import zhangjie.dao.UserInformationDAO;
import zhangjie.entity.AjaxResult;
import zhangjie.model.UserInformation;
import zhangjie.util.AssertUtil;

@Controller
public class LoginController extends BaseController {
	private static final Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private UserInformationDAO userInformationDAO;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, HttpServletRequest request) {
		if (this.isLogin()) {
			model.addAttribute(Constants.ENTITY_CONF, EntityConfigCache.get(Constants.ENTITY_BILL));
			return "main";
		}
		return "login";
	}
	
	@RequestMapping(value = "/manager/login", method = RequestMethod.GET)
	public String managerLogin(Model model, HttpServletRequest request) {
		return "managerLogin";
	}

	@RequestMapping(value = "/login/submit", method = RequestMethod.POST)
	public @ResponseBody AjaxResult loginSubmit(HttpServletRequest req, String account, String password) {
		AssertUtil.strIsNotBlank(account, "account is blank");
		AssertUtil.strIsNotBlank(password, "password is blank");
		MDC.put(Constants.LOG_UID, account);

		logger.info("用户开始登陆");

		// 检查用户有效性
		UserInformation userInformation = userInformationDAO.selectByPrimaryKey(account);
		AssertUtil.argIsNotNull(userInformation, "用户不存在");

		// 密码MD5转码
		String password1 = DigestUtils.md5DigestAsHex(password.getBytes());
		String dbPassword = userInformation.getPassword();
		AssertUtil.strEqual(dbPassword, password1, "密码错误,请重试");

		commonBO.setSessionAttr(Constants.SESSION_KEY_USER, userInformation);
		logger.info("用户登录成功");
		return commonBO.buildSuccessResult();
	}

	@RequestMapping(value = "/loginout", method = RequestMethod.GET)
	public String logout(Model model) {
		try {
			HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			HttpSession s = req.getSession(false);
			if (s != null) {
				try {
					s.removeAttribute(Constants.SESSION_KEY_USER);
					s.invalidate();
				} catch (Exception e) {
					logger.error("会话失效异常", e);
					// TODO: handle exception
				}
			}
			logger.info("用户登出成功");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("用户登出异常", e);
		}
		return "loginout";
	}
}
