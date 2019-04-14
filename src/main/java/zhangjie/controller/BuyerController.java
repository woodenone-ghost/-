package zhangjie.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import zhangjie.bo.EntityTransformer;
import zhangjie.cache.EntityConfigCache;
import zhangjie.constants.Constants;
import zhangjie.dao.BuyerDAO;
import zhangjie.entity.AjaxResult;
import zhangjie.entity.Pager;
import zhangjie.model.Buyer;
import zhangjie.util.AssertUtil;
import zhangjie.util.BeanUtil;

@Controller
@RequestMapping("/buyer")
public class BuyerController extends BaseController {

	private static final Logger logger = Logger.getLogger(BuyerController.class);

	@SuppressWarnings("unused")
	private static final EntityTransformer TF = new EntityTransformer() {
		public void transform(Map<String, String> mp) {

		}
	};

	@Autowired
	private BuyerDAO buyerDAO;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manage(Model model) {
		model.addAttribute(Constants.ENTITY_CONF, EntityConfigCache.get(Constants.ENTITY_BUYER));
		return "buyer/manage";
	}

	@RequestMapping(value = "/qry")
	public @ResponseBody AjaxResult qry(Integer pageNumber, Integer pageSize ) {
		Map<String, String> qryParamMap = this.getQryParamMap();
		logger.info("分页查询开始：" + qryParamMap);
		Pager<Buyer> p = buyerDAO.selectByPage(pageNumber, pageSize, qryParamMap);
		logger.info("分页查询结束，总记录数:" + p.getTotal());
		return commonBO.buildSuccessResult(Constants.PAGER_RESULT, p);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model, Integer idBuyer) {
		// 字段检查
		AssertUtil.argIsNotNull(idBuyer, "idBuyer is null");

		// 从数据库查询记录
		Buyer entity = buyerDAO.selectByPrimaryKey(idBuyer);
		AssertUtil.argIsNotNull(entity, "买家不存在");

		// 放入model，传入界面
		model.addAttribute(Constants.ENTITY_RESULT, entity);
		model.addAttribute(Constants.ENTITY_CONF, EntityConfigCache.get(Constants.ENTITY_BUYER));
		return "buyer/edit";
	}

	@RequestMapping(value = "/edit/submit", method = RequestMethod.POST)
	public @ResponseBody AjaxResult editSubmit(Buyer entity) {
		Integer idBuyer = entity.getIdBuyer();

		// 字段检查
		AssertUtil.argIsNotNull(idBuyer, "idBuyer is null");

		// 从数据库查询记录
		Buyer dbentity = buyerDAO.selectByPrimaryKey(idBuyer);
		AssertUtil.argIsNotNull(dbentity, "买家不存在");

		logger.info(String.format("修改买家开始：%s", BeanUtil.desc(dbentity, null)));
		buyerDAO.update(entity);
		logger.info(String.format("修改买家结束：%s", BeanUtil.desc(entity, null)));
		return commonBO.buildSuccessResult();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody AjaxResult delete(Integer idBuyer) {
		// 字段检查
		AssertUtil.argIsNotNull(idBuyer, "idBuyer is null");

		// 从数据库查询记录
		Buyer entity = buyerDAO.selectByPrimaryKey(idBuyer);
		AssertUtil.argIsNotNull(entity, "买家不存在");

		logger.info(String.format("删除买家开始：%s", BeanUtil.desc(entity, null)));
		buyerDAO.delete(idBuyer);
		logger.info(String.format("删除买家结束：%s", BeanUtil.desc(entity, null)));
		return commonBO.buildSuccessResult();
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(Model model, Integer idBuyer) {
		// 字段检查
		AssertUtil.argIsNotNull(idBuyer, "idBuyer is null");

		// 从数据库查询记录
		Buyer entity = buyerDAO.selectByPrimaryKey(idBuyer);
		AssertUtil.argIsNotNull(entity, "买家不存在");

		// 放入model，传入界面
		model.addAttribute(Constants.ENTITY_RESULT, entity);
		model.addAttribute(Constants.ENTITY_CONF, EntityConfigCache.get(Constants.ENTITY_BUYER));
		return "buyer/detail";
	}
}
