package zhangjie.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import zhangjie.bo.CommonBO;
import zhangjie.constants.Constants;
import zhangjie.model.UserInformation;

public abstract class BaseController {
	@Autowired
	protected CommonBO commonBO;

	protected Map<String, String> getQryParamMap() {
		return commonBO.getRequestAttr(Constants.QRY_PARAM_MAP);
	}

	protected UserInformation getSessionUser() {
		return commonBO.getSessionAttr(Constants.SESSION_KEY_USER);
	}

	protected boolean isLogin() {
		return commonBO.getSessionAttr(Constants.SESSION_KEY_USER) != null;
	}
}
