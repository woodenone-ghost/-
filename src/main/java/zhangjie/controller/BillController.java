package zhangjie.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import zhangjie.dao.BillDAO;
import zhangjie.dao.CommodityDAO;
import zhangjie.entity.AjaxResult;
import zhangjie.entity.Pager;
import zhangjie.model.Bill;
import zhangjie.model.Commodity;
import zhangjie.util.AssertUtil;
import zhangjie.util.BeanUtil;

@Controller
@RequestMapping("/bill")
public class BillController extends BaseController {

	private static final Logger logger = Logger.getLogger(BillController.class);

	@SuppressWarnings("unused")
	private static final EntityTransformer TF = new EntityTransformer() {
		public void transform(Map<String, String> mp) {

		}
	};

	@Autowired
	private BillDAO billDAO;
	@Autowired
	private CommodityDAO commodityDAO;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manage(Model model) {
		model.addAttribute(Constants.ENTITY_CONF, EntityConfigCache.get(Constants.ENTITY_BILL));
		return "bill/manage";
	}

	@RequestMapping(value = "/sellerManage", method = RequestMethod.GET)
	public String sellerManage(Model model) {
		model.addAttribute(Constants.ENTITY_CONF, EntityConfigCache.get(Constants.ENTITY_BILL));
		return "bill/sellerManage";
	}

	@RequestMapping(value = "/qry")
	public @ResponseBody AjaxResult qry(Integer pageNumber, Integer pageSize) {
		Map<String, String> qryParamMap = this.getQryParamMap();
		if (this.getSessionUser() != null) {
			if (this.getSessionUser().getIdentity().equals("seller")) {
				qryParamMap.put("accountSellerZ", this.getSessionUser().getAccount());
			} else if (this.getSessionUser().getIdentity().equals("buyer")) {
				qryParamMap.put("accountBuyerZ", this.getSessionUser().getAccount());
			}
		}
		logger.info("分页查询开始：" + qryParamMap);
		Pager<Bill> p = billDAO.selectByPage(pageNumber, pageSize, qryParamMap);
		List<Bill> bills = p.getRows();
		for (Bill bill : bills) {
			bill.setNameCommodity(commodityDAO.selectByPrimaryKey(bill.getIdCommodity()).getName());
		}
		logger.info("分页查询结束，总记录数:" + p.getTotal());
		return commonBO.buildSuccessResult(Constants.PAGER_RESULT, p);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model, Integer id) {
		// 字段检查
		AssertUtil.argIsNotNull(id, "id is null");

		// 从数据库查询记录
		Bill entity = billDAO.selectByPrimaryKey(id);
		AssertUtil.argIsNotNull(entity, "账单不存在");
		entity.setPrice(entity.getPrice().substring(0, entity.getPrice().length() - 1));

		// 放入model，传入界面
		model.addAttribute(Constants.ENTITY_RESULT, entity);
		model.addAttribute(Constants.ENTITY_CONF, EntityConfigCache.get(Constants.ENTITY_BILL));
		return "bill/edit";
	}

	@RequestMapping(value = "/edit/submit", method = RequestMethod.POST)
	public @ResponseBody AjaxResult editSubmit(Bill entity) {
		Integer id = entity.getId();

		// 字段检查
		AssertUtil.argIsNotNull(id, "id is null");
		AssertUtil.argIsNotNull(entity.getAccountBuyer(), "accountBuyer is null");
		AssertUtil.argIsNotNull(entity.getIdCommodity(), "idCommodity is null");
		AssertUtil.argIsNotNull(entity.getAccountSeller(), "accountSeller is null");
		AssertUtil.argIsNotNull(entity.getQuantity(), "quantity is null");
		AssertUtil.argIsNotNull(entity.getPrice(), "price is null");
		AssertUtil.argIsNotNull(entity.getTime(), "time is null");
		AssertUtil.strIsNotBlank(entity.getState(), "state is null");

		// 从数据库查询记录
		Bill dbentity = billDAO.selectByPrimaryKey(id);
		AssertUtil.argIsNotNull(dbentity, "账单不存在");

		logger.info(String.format("修改账单开始：%s", BeanUtil.desc(dbentity, null)));
		entity.setPrice(entity.getPrice() + "元");
		billDAO.update(entity);
		logger.info(String.format("修改账单结束：%s", BeanUtil.desc(entity, null)));
		return commonBO.buildSuccessResult();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody AjaxResult delete(Integer id) {
		// 字段检查
		AssertUtil.argIsNotNull(id, "id is null");

		// 从数据库查询记录
		Bill entity = billDAO.selectByPrimaryKey(id);
		AssertUtil.argIsNotNull(entity, "账单不存在");

		logger.info(String.format("删除账单开始：%s", BeanUtil.desc(entity, null)));
		billDAO.delete(id);
		logger.info(String.format("删除账单结束：%s", BeanUtil.desc(entity, null)));
		return commonBO.buildSuccessResult();
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(Model model, Integer id) {
		// 字段检查
		AssertUtil.argIsNotNull(id, "id is null");

		// 从数据库查询记录
		Bill entity = billDAO.selectByPrimaryKey(id);
		entity.setNameCommodity(commodityDAO.selectByPrimaryKey(entity.getIdCommodity()).getName());
		AssertUtil.argIsNotNull(entity, "账单不存在");

		// 放入model，传入界面
		model.addAttribute(Constants.ENTITY_RESULT, entity);
		model.addAttribute(Constants.ENTITY_CONF, EntityConfigCache.get(Constants.ENTITY_BILL));
		return "bill/detail";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model, Integer id, int quantity) {
		// 字段检查
		AssertUtil.argIsNotNull(id, "id is null");
		AssertUtil.argIsNotNull(quantity, "quantity is null");

		// 从数据库查询记录
		Commodity commodity = commodityDAO.selectByPrimaryKey(id);
		AssertUtil.argIsNotNull(commodity, "commodity is null");

		Bill entity = new Bill();
		entity.setAccountBuyer(this.getSessionUser().getAccount());
		entity.setIdCommodity(id);
		entity.setAccountSeller(commodity.getBusinessName());
		entity.setQuantity(quantity);

		// 处理价格
		String[] str = commodity.getPrice().split("/");
		int price = Integer.parseInt(str[0].substring(0, str[0].length() - 1)) * quantity;
		entity.setPrice(String.valueOf(price) + "元");
		entity.setTime(new Date());
		entity.setState(Constants.WEIFAHUO);

		billDAO.add(entity);

		// 放入model，传入界面
		model.addAttribute(Constants.ENTITY_RESULT, entity);
		model.addAttribute(Constants.ENTITY_CONF, EntityConfigCache.get(Constants.ENTITY_BILL));
		return "main";
	}

	@RequestMapping(value = "/todayBills")
	public @ResponseBody AjaxResult todayBills(Integer pageNumber, Integer pageSize) {
		Map<String, String> qryParamMap = new HashMap<String, String>();
		Date now = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf1.format(now);
		qryParamMap.put("time", time);
		qryParamMap.put("accountSellerZ", this.getSessionUser().getAccount());
		logger.info("分页查询开始：" + qryParamMap);
		Pager<Bill> p = billDAO.selectByPage(pageNumber, pageSize, qryParamMap);

		List<Bill> bills = p.getRows();
		for (Bill bill : bills) {
			bill.setNameCommodity(commodityDAO.selectByPrimaryKey(bill.getIdCommodity()).getName());
		}
		logger.info("分页查询结束，总记录数:" + p.getTotal());
		return commonBO.buildSuccessResult(Constants.PAGER_RESULT, p);
	}

	@RequestMapping(value = "/notEvaluateBills")
	public @ResponseBody AjaxResult notEvaluateBills(Integer pageNumber, Integer pageSize) {
		Map<String, String> qryParamMap = new HashMap<String, String>();
		qryParamMap.put("state", Constants.WEIPINJIA);
		if (this.getSessionUser().getIdentity().equals("seller")) {
			qryParamMap.put("accountSellerZ", this.getSessionUser().getAccount());
		} else if (this.getSessionUser().getIdentity().equals("buyer")) {
			qryParamMap.put("accountBuyerZ", this.getSessionUser().getAccount());
		}

		logger.info("分页查询开始：" + qryParamMap);
		Pager<Bill> p = billDAO.selectByPage(pageNumber, pageSize, qryParamMap);
		List<Bill> bills = p.getRows();
		for (Bill bill : bills) {
			bill.setNameCommodity(commodityDAO.selectByPrimaryKey(bill.getIdCommodity()).getName());
		}
		logger.info("分页查询结束，总记录数:" + p.getTotal());
		return commonBO.buildSuccessResult(Constants.PAGER_RESULT, p);
	}

	@RequestMapping(value = "/notBills")
	public @ResponseBody AjaxResult notBills(Integer pageNumber, Integer pageSize) {
		Map<String, String> qryParamMap = new HashMap<String, String>();
		qryParamMap.put("state", Constants.WEIFAHUO);
		if (this.getSessionUser().getIdentity().equals("seller")) {
			qryParamMap.put("accountSellerZ", this.getSessionUser().getAccount());
		} else if (this.getSessionUser().getIdentity().equals("buyer")) {
			qryParamMap.put("accountBuyerZ", this.getSessionUser().getAccount());
		}

		logger.info("分页查询开始：" + qryParamMap);
		Pager<Bill> p = billDAO.selectByPage(pageNumber, pageSize, qryParamMap);
		List<Bill> bills = p.getRows();
		for (Bill bill : bills) {
			bill.setNameCommodity(commodityDAO.selectByPrimaryKey(bill.getIdCommodity()).getName());
		}
		logger.info("分页查询结束，总记录数:" + p.getTotal());
		return commonBO.buildSuccessResult(Constants.PAGER_RESULT, p);
	}

	@RequestMapping(value = "/badBills")
	public @ResponseBody AjaxResult badBills(Integer pageNumber, Integer pageSize) {
		Map<String, String> qryParamMap = new HashMap<String, String>();
		qryParamMap.put("state", Constants.YIPINJIA);
		qryParamMap.put("evaluation", "差评");
		qryParamMap.put("accountSellerZ", this.getSessionUser().getAccount());
		logger.info("分页查询开始：" + qryParamMap);
		Pager<Bill> p = billDAO.selectByPage(pageNumber, pageSize, qryParamMap);
		List<Bill> bills = p.getRows();
		for (Bill bill : bills) {
			bill.setNameCommodity(commodityDAO.selectByPrimaryKey(bill.getIdCommodity()).getName());
		}
		logger.info("分页查询结束，总记录数:" + p.getTotal());
		return commonBO.buildSuccessResult(Constants.PAGER_RESULT, p);
	}

	@RequestMapping(value = "/getEvaluationWords")
	public @ResponseBody AjaxResult myBills(Integer pageNumber, Integer pageSize, int _QRY_id) {
		Map<String, String> qryParamMap = new HashMap<String, String>();
		qryParamMap.put("idCommodity", String.valueOf(_QRY_id));
		qryParamMap.put("state", Constants.YIPINJIA);
		logger.info("分页查询开始：" + qryParamMap);
		Pager<Bill> p = billDAO.selectByPage(pageNumber, pageSize, qryParamMap);
		logger.info("分页查询结束，总记录数:" + p.getTotal());
		return commonBO.buildSuccessResult(Constants.PAGER_RESULT, p);
	}

	@RequestMapping(value = "/evaluate", method = RequestMethod.GET)
	public String evaluate(Model model, Integer id) {
		// 字段检查
		AssertUtil.argIsNotNull(id, "id is null");

		// 从数据库查询记录
		Bill entity = billDAO.selectByPrimaryKey(id);
		AssertUtil.argIsNotNull(entity, "账单不存在");
		entity.setNameCommodity(commodityDAO.selectByPrimaryKey(entity.getIdCommodity()).getName());

		// 放入model，传入界面
		model.addAttribute(Constants.ENTITY_RESULT, entity);
		model.addAttribute(Constants.ENTITY_CONF, EntityConfigCache.get(Constants.ENTITY_BILL));
		return "bill/evaluate";
	}

	@RequestMapping(value = "/evaluate/submit", method = RequestMethod.POST)
	public @ResponseBody AjaxResult evaluateSubmit(Bill entity) {
		Integer id = entity.getId();

		// 字段检查
		AssertUtil.argIsNotNull(id, "id is null");
		AssertUtil.argIsNotNull(entity.getIdCommodity(), "idCommodity is null");
		AssertUtil.argIsNotNull(entity.getAccountSeller(), "accountSeller is null");
		AssertUtil.argIsNotNull(entity.getQuantity(), "quantity is null");
		AssertUtil.argIsNotNull(entity.getPrice(), "price is null");
		AssertUtil.argIsNotNull(entity.getTime(), "time is null");
		AssertUtil.strIsNotBlank(entity.getState(), "state is null");
		AssertUtil.strIsNotBlank(entity.getEvaluation(), "evaluation is null");
		AssertUtil.strIsNotBlank(entity.getEvaluationPrice(), "evaluationPrice is null");
		AssertUtil.strIsNotBlank(entity.getEvaluationService(), "evaluationService is null");
		AssertUtil.strIsNotBlank(entity.getEvaluationWords(), "evaluationWords is null");

		// 从数据库查询记录
		Bill dbentity = billDAO.selectByPrimaryKey(id);
		AssertUtil.argIsNotNull(dbentity, "账单不存在");

		entity.setState(Constants.YIPINJIA);
		entity.setAccountBuyer(this.getSessionUser().getAccount());
		logger.info(String.format("评价账单开始：%s", BeanUtil.desc(dbentity, null)));
		billDAO.update(entity);
		logger.info(String.format("评价账单结束：%s", BeanUtil.desc(entity, null)));
		chufaqi(entity);
		return commonBO.buildSuccessResult();
	}

	@RequestMapping(value = "/fahuo", method = RequestMethod.GET)
	public @ResponseBody AjaxResult fahuo(Integer id) {
		// 字段检查
		AssertUtil.argIsNotNull(id, "id is null");

		// 从数据库查询记录
		Bill entity = billDAO.selectByPrimaryKey(id);
		AssertUtil.argIsNotNull(entity, "账单不存在");

		entity.setState(Constants.WEIPINJIA);
		entity.setEvaluation("好评");
		logger.info(String.format("发货开始：%s", BeanUtil.desc(entity, null)));
		billDAO.update(entity);
		logger.info(String.format("发货结束：%s", BeanUtil.desc(entity, null)));
		return commonBO.buildSuccessResult();
	}

	/**
	 * 代替数据库的触发器，进行销量、平均分的更新
	 * 
	 * @param bill
	 */
	public void chufaqi(Bill bill) {
		Integer idCommodity = bill.getIdCommodity();
		Commodity commodity = commodityDAO.selectByPrimaryKey(idCommodity);// 得到商品

		// 取得商品的本来信息
		int salesVolume = commodity.getSalesVolume();
		Double evaluationPrice = commodity.getEvaluationPrice();
		Double evaluationService = commodity.getEvaluationService();

		// 开始更新商品信息
		evaluationService = (evaluationService * salesVolume
				+ Double.parseDouble(bill.getEvaluationService()) * bill.getQuantity())
				/ (salesVolume + bill.getQuantity());// 更新平均服务评价
		evaluationPrice = (evaluationPrice * salesVolume
				+ Double.parseDouble(bill.getEvaluationPrice()) * bill.getQuantity())
				/ (salesVolume + bill.getQuantity());// 更新平均价格评价
		salesVolume = salesVolume + bill.getQuantity();// 更新销量

		commodity.setEvaluationPrice(evaluationPrice);
		commodity.setEvaluationService(evaluationService);
		commodity.setSalesVolume(salesVolume);

		// 更新数据库
		commodityDAO.update(commodity);
	}
}
