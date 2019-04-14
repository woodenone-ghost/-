package zhangjie.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import zhangjie.cache.EntityConfigCache;
import zhangjie.constants.Constants;
import zhangjie.dao.BuyerDAO;
import zhangjie.dao.SellerDAO;
import zhangjie.dao.UserInformationDAO;
import zhangjie.entity.AjaxResult;
import zhangjie.model.Buyer;
import zhangjie.model.Seller;
import zhangjie.model.UserInformation;
import zhangjie.util.AssertUtil;

@Controller
public class RegisterController extends BaseController {
	private static final Logger logger = Logger.getLogger(RegisterController.class);

	@Autowired
	private UserInformationDAO userInformationDAO;
	@Autowired
	private BuyerDAO buyerDAO;
	@Autowired
	private SellerDAO sellerDAO;

	// 跳转到注册界面
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "register/userInformation";
	}

	// 用户注册
	@RequestMapping(value = "/register/submit", method = RequestMethod.POST)
	public @ResponseBody AjaxResult registerSubmit(HttpServletRequest req, UserInformation entity) {
		AssertUtil.strIsNotBlank(entity.getAccount(), "account is blank");
		AssertUtil.strIsNotBlank(entity.getPassword(), "password is blank");
		AssertUtil.strIsNotBlank(entity.getIdentity(), "identity is blank");
		logger.info("用户开始注册");

		// 检查用户有效性
		UserInformation userInformation = userInformationDAO.selectByPrimaryKey(entity.getAccount());
		AssertUtil.argIsNull(userInformation, "用户已存在");

		// 密码MD5转码
		String password1 = DigestUtils.md5DigestAsHex(entity.getPassword().getBytes());
		entity.setPassword(password1);

		userInformationDAO.add(entity);
		logger.info("用户注册完毕：" + entity.toString());

		return commonBO.buildSuccessResult();
	}

	// 身份确认
	@RequestMapping(value = "/register/identity", method = RequestMethod.GET)
	public String identity(Model model, String account) {
		UserInformation userInformation = userInformationDAO.selectByPrimaryKey(account);
		model.addAttribute("id", userInformation.getId());
		if (userInformation.getIdentity().equals("buyer")) {
			model.addAttribute(Constants.ENTITY_CONF, EntityConfigCache.get(Constants.ENTITY_BUYER));
			return "register/buyer";
		} else {
			model.addAttribute(Constants.ENTITY_CONF, EntityConfigCache.get(Constants.ENTITY_SELLER));
			return "register/seller";
		}
	}

	// 买家注册
	@RequestMapping(value = "/register/buyer/submit", method = RequestMethod.POST)
	public @ResponseBody AjaxResult buyerSubmit(HttpServletRequest req, Buyer entity) {
		AssertUtil.argIsNotNull(entity.getIdBuyer(), "idBuyer is null");
		AssertUtil.strIsNotBlank(entity.getNameBuyer(), "nameBuyer is blank");
		AssertUtil.strIsNotBlank(entity.getSexBuyer(), "sexBuyer is blank");
		AssertUtil.strIsNotBlank(entity.getAgeBuyer(), "ageBuyer is blank");
		AssertUtil.strIsNotBlank(entity.getAddressBuyer(), "addressBuyer is blank");
		logger.info("买家开始注册");

		buyerDAO.add(entity);

		logger.info("买家注册完毕：" + entity.toString());

		return commonBO.buildSuccessResult();
	}

	// 卖家注册
	@RequestMapping(value = "/register/seller/submit", method = RequestMethod.POST)
	public @ResponseBody AjaxResult sellerSubmit(HttpServletRequest req, Seller entity) {
		AssertUtil.argIsNotNull(entity.getIdSeller(), "idSeller is null");
		AssertUtil.strIsNotBlank(entity.getNameSeller(), "nameSeller is blank");
		AssertUtil.strIsNotBlank(entity.getAddressSeller(), "addressSeller is blank");
		logger.info("卖家开始注册");
		entity.setGoodSeller(0);
		entity.setBadSeller(0);

		sellerDAO.add(entity);

		logger.info("卖家注册完毕：" + entity.toString());

		return commonBO.buildSuccessResult();
	}
}
