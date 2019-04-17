package zhangjie.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import zhangjie.entity.AjaxResult;
import zhangjie.entity.Pager;
import zhangjie.model.Bill;
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

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manage(Model model) {
		model.addAttribute(Constants.ENTITY_CONF, EntityConfigCache.get(Constants.ENTITY_BILL));
		return "bill/manage";
	}
	
	@RequestMapping(value = "/todayBills")
	public @ResponseBody AjaxResult todayBills(Integer pageNumber, Integer pageSize) {
		Map<String, String> qryParamMap = new HashMap<String, String>();
		Date now =new Date();
		SimpleDateFormat sdf1 =new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf1.format(now);
		qryParamMap.put("time", "2019-04-15");
		qryParamMap.put("idSeller", this.getSessionUser().getId().toString());
		logger.info("分页查询开始：" + qryParamMap);
		Pager<Bill> p = billDAO.selectByPage(pageNumber, pageSize, qryParamMap);
		logger.info("分页查询结束，总记录数:" + p.getTotal());
		return commonBO.buildSuccessResult(Constants.PAGER_RESULT, p);
	}

	@RequestMapping(value = "/qry")
	public @ResponseBody AjaxResult qry(Integer pageNumber, Integer pageSize) {
		Map<String, String> qryParamMap = this.getQryParamMap();
		logger.info("分页查询开始：" + qryParamMap);
		Pager<Bill> p = billDAO.selectByPage(pageNumber, pageSize, qryParamMap);
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

		// 放入model，传入界面
		model.addAttribute(Constants.ENTITY_RESULT, entity);
		model.addAttribute(Constants.ENTITY_CONF, EntityConfigCache.get(Constants.ENTITY_BILL));
		return "bill/edit";
	}

	@RequestMapping(value = "/edit/submit", method = RequestMethod.POST)
	public @ResponseBody AjaxResult editSubmit(Bill entity) {
		Integer id= entity.getId();

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
		AssertUtil.argIsNotNull(entity, "账单不存在");

		// 放入model，传入界面
		model.addAttribute(Constants.ENTITY_RESULT, entity);
		model.addAttribute(Constants.ENTITY_CONF, EntityConfigCache.get(Constants.ENTITY_BILL));
		return "bill/detail";
	}
}
