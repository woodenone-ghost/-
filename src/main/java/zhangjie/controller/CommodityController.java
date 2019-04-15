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
import zhangjie.dao.CommodityDAO;
import zhangjie.entity.AjaxResult;
import zhangjie.entity.Pager;
import zhangjie.model.Commodity;
import zhangjie.util.AssertUtil;
import zhangjie.util.BeanUtil;

@Controller
@RequestMapping("/commodity")
public class CommodityController extends BaseController {

	private static final Logger logger = Logger.getLogger(CommodityController.class);

	@SuppressWarnings("unused")
	private static final EntityTransformer TF = new EntityTransformer() {
		public void transform(Map<String, String> mp) {

		}
	};

	@Autowired
	private CommodityDAO commodityDAO;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manage(Model model) {
		model.addAttribute(Constants.ENTITY_CONF, EntityConfigCache.get(Constants.ENTITY_COMMODITY));
		return "commodity/manage";
	}

	@RequestMapping(value = "/qry")
	public @ResponseBody AjaxResult qry(Integer pageNumber, Integer pageSize) {
		Map<String, String> qryParamMap = this.getQryParamMap();
		logger.info("分页查询开始：" + qryParamMap);
		Pager<Commodity> p = commodityDAO.selectByPage(pageNumber, pageSize, qryParamMap);
		logger.info("分页查询结束，总记录数:" + p.getTotal());
		return commonBO.buildSuccessResult(Constants.PAGER_RESULT, p);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model, Integer id) {
		// 字段检查
		AssertUtil.argIsNotNull(id, "id is null");

		// 从数据库查询记录
		Commodity entity = commodityDAO.selectByPrimaryKey(id);
		AssertUtil.argIsNotNull(entity, "商品不存在");

		// 放入model，传入界面
		model.addAttribute(Constants.ENTITY_RESULT, entity);
		model.addAttribute(Constants.ENTITY_CONF, EntityConfigCache.get(Constants.ENTITY_COMMODITY));
		return "commodity/edit";
	}

	@RequestMapping(value = "/edit/submit", method = RequestMethod.POST)
	public @ResponseBody AjaxResult editSubmit(Commodity entity) {
		Integer id= entity.getId();

		// 字段检查
		AssertUtil.argIsNotNull(id, "id is null");
		AssertUtil.strIsNotBlank(entity.getIcon(), "icon is null");
		AssertUtil.strIsNotBlank(entity.getName(), "name is null");
		AssertUtil.argIsNotNull(entity.getPrice(), "price is null");
		AssertUtil.strIsNotBlank(entity.getCategory(), "category is null");
		AssertUtil.argIsNotNull(entity.getBusinessName(), "businessName is null");
		AssertUtil.argIsNotNull(entity.getSalesVolume(), "salesVolume is null");
		AssertUtil.strIsNotBlank(entity.getEvaluation(), "evaluation is null");

		// 从数据库查询记录
		Commodity dbentity = commodityDAO.selectByPrimaryKey(id);
		AssertUtil.argIsNotNull(dbentity, "商品不存在");

		logger.info(String.format("修改商品开始：%s", BeanUtil.desc(dbentity, null)));
		commodityDAO.update(entity);
		logger.info(String.format("修改商品结束：%s", BeanUtil.desc(entity, null)));
		return commonBO.buildSuccessResult();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody AjaxResult delete(Integer id) {
		// 字段检查
		AssertUtil.argIsNotNull(id, "id is null");

		// 从数据库查询记录
		Commodity entity = commodityDAO.selectByPrimaryKey(id);
		AssertUtil.argIsNotNull(entity, "商品不存在");

		logger.info(String.format("删除商品开始：%s", BeanUtil.desc(entity, null)));
		commodityDAO.delete(id);
		logger.info(String.format("删除商品结束：%s", BeanUtil.desc(entity, null)));
		return commonBO.buildSuccessResult();
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(Model model, Integer id) {
		// 字段检查
		AssertUtil.argIsNotNull(id, "id is null");

		// 从数据库查询记录
		Commodity entity = commodityDAO.selectByPrimaryKey(id);
		AssertUtil.argIsNotNull(entity, "商品不存在");

		// 放入model，传入界面
		model.addAttribute(Constants.ENTITY_RESULT, entity);
		model.addAttribute(Constants.ENTITY_CONF, EntityConfigCache.get(Constants.ENTITY_COMMODITY));
		return "commodity/detail";
	}
}
