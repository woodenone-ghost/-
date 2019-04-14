package zhangjie.controller;

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
import zhangjie.util.BeanUtil;

@Controller
public class CommonController extends BaseController {

	private static final Logger logger = Logger.getLogger(CommonController.class);

	@Autowired
	private UserInformationDAO userInformationDAO;
	@Autowired
	private BuyerDAO buyerDAO;
	@Autowired
	private SellerDAO sellerDAO;

	@RequestMapping(value = "/userInformation", method = RequestMethod.GET)
	public String userInformation(Model model) {
		String identity = this.getSessionUser().getIdentity();
		int id=this.getSessionUser().getId();
		if(identity.equals("buyer")) {
			Buyer entity=buyerDAO.selectByPrimaryKey(id);
			model.addAttribute(Constants.ENTITY_CONF, EntityConfigCache.get(Constants.ENTITY_BUYER));
			model.addAttribute(Constants.ENTITY_RESULT, entity);
		}else if(identity.equals("seller")) {
			Seller entity=sellerDAO.selectByPrimaryKey(id);
			model.addAttribute(Constants.ENTITY_CONF, EntityConfigCache.get(Constants.ENTITY_SELLER));
			model.addAttribute(Constants.ENTITY_RESULT, entity);
		}
		return "common/userInformation";
	}
	
	@RequestMapping(value = "/userInformation/submit", method = RequestMethod.POST)
	public @ResponseBody AjaxResult userInformationSubmit(Buyer buyer,Seller seller) {
		// 字段检查
		if(buyer.getIdBuyer()!=null) {//传输的买家信息
			Buyer entity=buyer;
			Integer idBuyer = entity.getIdBuyer();

			// 字段检查
			AssertUtil.argIsNotNull(idBuyer, "idBuyer is null");

			// 从数据库查询记录
			Buyer dbentity = buyerDAO.selectByPrimaryKey(idBuyer);
			AssertUtil.argIsNotNull(dbentity, "买家不存在");

			logger.info(String.format("修改买家开始：%s", BeanUtil.desc(dbentity, null)));
			buyerDAO.update(entity);
			logger.info(String.format("修改买家结束：%s", BeanUtil.desc(entity, null)));
		}else {
			Seller entity=seller;
			Integer idSeller = entity.getIdSeller();

			// 字段检查
			AssertUtil.argIsNotNull(idSeller, "idSeller is null");

			// 从数据库查询记录
			Seller dbentity = sellerDAO.selectByPrimaryKey(idSeller);
			AssertUtil.argIsNotNull(dbentity, "卖家不存在");

			logger.info(String.format("修改卖家开始：%s", BeanUtil.desc(dbentity, null)));
			entity.setGoodSeller(dbentity.getGoodSeller());
			entity.setBadSeller(dbentity.getBadSeller());
			sellerDAO.update(entity);
			logger.info(String.format("修改卖家结束：%s", BeanUtil.desc(entity, null)));
		}

		return commonBO.buildSuccessResult();
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public String changePassword(Model model) {
		return "common/changePassword";
	}

	@RequestMapping(value = "/changePassword/submit", method = RequestMethod.POST)
	public @ResponseBody AjaxResult changePasswordSubmit(String oldPassword, String newPassword, String newPasswordZ) {
		// 字段检查
		AssertUtil.strIsNotBlank(oldPassword, "旧密码为空");
		AssertUtil.strIsNotBlank(newPassword, "新密码为空");
		AssertUtil.strIsNotBlank(newPasswordZ, "新密码为空");

		String password = DigestUtils.md5DigestAsHex(oldPassword.getBytes());
		String dbPassword = this.getSessionUser().getPassword();
		AssertUtil.strEqual(dbPassword, password, "输入旧密码错误");
		AssertUtil.strEqual(newPassword, newPasswordZ, "两次输入的密码不一样");

		logger.info("修改密码开始");

		String account = this.getSessionUser().getAccount();
		UserInformation entity = userInformationDAO.selectByPrimaryKey(account);
		String newDBPassword = DigestUtils.md5DigestAsHex(newPassword.getBytes());
		// 设置新密码
		entity.setPassword(newDBPassword);
		userInformationDAO.update(entity);

		logger.info("修改密码结束");

		return commonBO.buildSuccessResult();
	}
}
