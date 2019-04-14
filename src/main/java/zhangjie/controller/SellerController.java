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
import zhangjie.dao.SellerDAO;
import zhangjie.entity.AjaxResult;
import zhangjie.entity.Pager;
import zhangjie.model.Seller;
import zhangjie.util.AssertUtil;
import zhangjie.util.BeanUtil;

@Controller
@RequestMapping("/seller")
public class SellerController extends BaseController {

	private static final Logger logger = Logger.getLogger(SellerController.class);

	@SuppressWarnings("unused")
	private static final EntityTransformer TF = new EntityTransformer() {
		public void transform(Map<String, String> mp) {

		}
	};

	@Autowired
	private SellerDAO sellerDAO;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manage(Model model) {
		model.addAttribute(Constants.ENTITY_CONF, EntityConfigCache.get(Constants.ENTITY_SELLER));
		return "seller/manage";
	}

	@RequestMapping(value = "/qry", method = RequestMethod.POST)
	public @ResponseBody AjaxResult qry(int pageNumber, int pageSize) {
		Map<String, String> qryParamMap = this.getQryParamMap();
		logger.info("分页查询开始：" + qryParamMap);
		Pager<Seller> p = sellerDAO.selectByPage(pageNumber, pageSize, qryParamMap);
		logger.info("分页查询结束，总记录数:" + p.getTotal());
		return commonBO.buildSuccessResult(Constants.PAGER_RESULT, p);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model, Integer idSeller) {
		// 字段检查
		AssertUtil.argIsNotNull(idSeller, "idSeller is null");

		// 从数据库查询记录
		Seller entity = sellerDAO.selectByPrimaryKey(idSeller);
		AssertUtil.argIsNotNull(entity, "卖家不存在");

		// 放入model，传入界面
		model.addAttribute(Constants.ENTITY_RESULT, entity);
		model.addAttribute(Constants.ENTITY_CONF, EntityConfigCache.get(Constants.ENTITY_SELLER));
		return "seller/edit";
	}

	@RequestMapping(value = "/edit/submit", method = RequestMethod.POST)
	public @ResponseBody AjaxResult editSubmit(Seller entity) {
		Integer idSeller = entity.getIdSeller();

		// 字段检查
		AssertUtil.argIsNotNull(idSeller, "idSeller is null");

		// 从数据库查询记录
		Seller dbentity = sellerDAO.selectByPrimaryKey(idSeller);
		AssertUtil.argIsNotNull(dbentity, "卖家不存在");

		logger.info(String.format("修改卖家开始：%s", BeanUtil.desc(dbentity, null)));
		sellerDAO.update(entity);
		logger.info(String.format("修改卖家结束：%s", BeanUtil.desc(entity, null)));
		return commonBO.buildSuccessResult();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody AjaxResult delete(Integer idSeller) {
		// 字段检查
		AssertUtil.argIsNotNull(idSeller, "idSeller is null");

		// 从数据库查询记录
		Seller entity = sellerDAO.selectByPrimaryKey(idSeller);
		AssertUtil.argIsNotNull(entity, "卖家不存在");

		logger.info(String.format("删除卖家开始：%s", BeanUtil.desc(entity, null)));
		sellerDAO.delete(idSeller);
		logger.info(String.format("删除卖家结束：%s", BeanUtil.desc(entity, null)));
		return commonBO.buildSuccessResult();
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(Model model, Integer idSeller) {
		// 字段检查
		AssertUtil.argIsNotNull(idSeller, "idSeller is null");

		// 从数据库查询记录
		Seller entity = sellerDAO.selectByPrimaryKey(idSeller);
		AssertUtil.argIsNotNull(entity, "卖家不存在");

		// 放入model，传入界面
		model.addAttribute(Constants.ENTITY_RESULT, entity);
		model.addAttribute(Constants.ENTITY_CONF, EntityConfigCache.get(Constants.ENTITY_SELLER));
		return "seller/detail";
	}
}
