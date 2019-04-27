package zhangjie.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import zhangjie.bo.EntityTransformer;
import zhangjie.cache.EntityConfigCache;
import zhangjie.constants.Constants;
import zhangjie.dao.CommodityDAO;
import zhangjie.entity.AjaxResult;
import zhangjie.entity.Pager;
import zhangjie.model.Commodity;
import zhangjie.util.AssertUtil;
import zhangjie.util.BeanUtil;
import zhangjie.util.CategoryUtil;

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

	@RequestMapping(value = "/sellerManage", method = RequestMethod.GET)
	public String sellerManage(Model model) {
		model.addAttribute(Constants.ENTITY_CONF, EntityConfigCache.get(Constants.ENTITY_COMMODITY));
		return "commodity/sellerManage";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute(Constants.ENTITY_CONF, EntityConfigCache.get(Constants.ENTITY_COMMODITY));
		return "commodity/add";
	}

	@RequestMapping(value = "/add/submit", method = RequestMethod.POST)
	public @ResponseBody AjaxResult addSubmit(String name, String price, String danwei, String category,
			MultipartFile icon, MultipartFile characteristic, HttpSession session) {
		// 字段检查
		AssertUtil.argIsNotNull(icon, "icon is null");
		AssertUtil.strIsNotBlank(name, "name is null");
		AssertUtil.strIsNotBlank(price, "price is null");
		AssertUtil.strIsNotBlank(danwei, "danwei is null");
		AssertUtil.strIsNotBlank(category, "category is null");
		AssertUtil.argIsNotNull(characteristic, "characteristic is null");

		String str = RandomStringUtils.randomAlphanumeric(5);// 得到长度为5的随机字符串
		Date now = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf1.format(now);

		String iconAddress = time + "_" + str + "_icon" + ".jpg";
		String path = session.getServletContext().getRealPath("/resources/icon/");
		File newFile = new File(path, iconAddress);

		String characteristicAddress = time + "_" + str + "_characteristic" + ".jpg";
		String path1 = session.getServletContext().getRealPath("/resources/characteristic/");
		File newFile1 = new File(path1, characteristicAddress);
		try {
			Commodity entity = new Commodity();
			entity.setIcon(iconAddress);
			entity.setName(name);
			entity.setPrice(price + danwei);
			entity.setCategory(CategoryUtil.wholeCategory(category));
			entity.setBusinessName(this.getSessionUser().getAccount());
			entity.setCharacteristic(characteristicAddress);
			commodityDAO.add(entity);
			icon.transferTo(newFile);
			characteristic.transferTo(newFile1);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return commonBO.buildSuccessResult();
	}

	@RequestMapping(value = "/qry")
	public @ResponseBody AjaxResult qry(Integer pageNumber, Integer pageSize) {
		Map<String, String> qryParamMap = this.getQryParamMap();
		if (this.getSessionUser() != null) {
			String identity = this.getSessionUser().getIdentity();
			if (identity.equals("seller")) {
				qryParamMap.put("businessNameZ", this.getSessionUser().getAccount());
			} else if (identity.equals("buyer")) {
				qryParamMap.put("shangjia", "上架");
			}
		}
		logger.info("分页查询开始：" + qryParamMap);
		Pager<Commodity> p = commodityDAO.selectByPage(pageNumber, pageSize, qryParamMap);
		logger.info("分页查询结束，总记录数:" + p.getTotal());
		return commonBO.buildSuccessResult(Constants.PAGER_RESULT, p);
	}

	@RequestMapping(value = "/gotoSearch")
	public String search(Model model, String _QRY_category, String _QRY_name) {
		model.addAttribute(Constants.ENTITY_CONF, EntityConfigCache.get(Constants.ENTITY_COMMODITY));
		model.addAttribute("name", _QRY_name);
		model.addAttribute("category", _QRY_category);
		return "someCommodities";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model, Integer id) {
		// 字段检查
		AssertUtil.argIsNotNull(id, "id is null");

		// 从数据库查询记录
		Commodity entity = commodityDAO.selectByPrimaryKey(id);
		AssertUtil.argIsNotNull(entity, "商品不存在");
		String[] str = entity.getPrice().split("/");
		entity.setPrice(str[0].substring(0, str[0].length() - 1));

		// 放入model，传入界面
		model.addAttribute(Constants.ENTITY_RESULT, entity);
		model.addAttribute("danwei", str[1]);
		model.addAttribute(Constants.ENTITY_CONF, EntityConfigCache.get(Constants.ENTITY_COMMODITY));
		return "commodity/edit";
	}

	@RequestMapping(value = "/edit/submit", method = RequestMethod.POST)
	public @ResponseBody AjaxResult editSubmit(int id, String name, String price, String danwei, String category,
			String businessName, MultipartFile icon, MultipartFile characteristic, int salesVolume,
			double evaluationPrice, double evaluationService, HttpSession session) {

		// 字段检查
		AssertUtil.argIsNotNull(id, "id is null");
		AssertUtil.strIsNotBlank(name, "name is null");
		AssertUtil.strIsNotBlank(price, "price is null");
		AssertUtil.strIsNotBlank(danwei, "danwei is null");
		AssertUtil.strIsNotBlank(category, "category is null");
		AssertUtil.strIsNotBlank(businessName, "businessName is null");
		AssertUtil.argIsNotNull(salesVolume, "salesVolume is null");
		AssertUtil.argIsNotNull(evaluationPrice, "evaluationPrice is null");
		AssertUtil.argIsNotNull(evaluationService, "evaluationService is null");

		// 从数据库查询记录
		Commodity dbentity = commodityDAO.selectByPrimaryKey(id);
		AssertUtil.argIsNotNull(dbentity, "商品不存在");

		String str = RandomStringUtils.randomAlphanumeric(5);// 得到长度为5的随机字符串
		Date now = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf1.format(now);

		String iconAddress = time + "_" + str + "_icon" + ".jpg";
		String path = session.getServletContext().getRealPath("/resources/icon/");
		File newFile = new File(path, iconAddress);

		String characteristicAddress = time + "_" + str + "_characteristic" + ".jpg";
		String path1 = session.getServletContext().getRealPath("/resources/characteristic/");
		File newFile1 = new File(path1, characteristicAddress);
		Commodity entity = new Commodity();
		try {
			entity.setId(id);
			if (null != icon) {
				entity.setIcon(iconAddress);
			} else {
				entity.setIcon(dbentity.getIcon());
			}
			entity.setName(name);
			entity.setPrice(price + danwei);
			entity.setCategory(category);
			entity.setBusinessName(this.getSessionUser().getAccount());
			if (null != characteristic) {
				entity.setCharacteristic(characteristicAddress);
			} else {
				entity.setCharacteristic(dbentity.getCharacteristic());
			}
			entity.setSalesVolume(salesVolume);
			entity.setEvaluationPrice(evaluationPrice);
			entity.setEvaluationService(evaluationService);

			if (null != icon) {
				icon.transferTo(newFile);
			}
			if (null != characteristic) {
				characteristic.transferTo(newFile1);
			}
		} catch (IllegalStateException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	@RequestMapping(value = "/shangjia", method = RequestMethod.POST)
	public @ResponseBody AjaxResult shangjia(Integer id) {
		// 字段检查
		AssertUtil.argIsNotNull(id, "id is null");

		// 从数据库查询记录
		Commodity entity = commodityDAO.selectByPrimaryKey(id);
		AssertUtil.argIsNotNull(entity, "商品不存在");

		logger.info(String.format("上架商品开始：%s", BeanUtil.desc(entity, null)));
		entity.setShangjia("上架");
		commodityDAO.update(entity);
		logger.info(String.format("上架商品结束：%s", BeanUtil.desc(entity, null)));
		return commonBO.buildSuccessResult();
	}

	@RequestMapping(value = "/xiajia", method = RequestMethod.POST)
	public @ResponseBody AjaxResult xiajia(Integer id) {
		// 字段检查
		AssertUtil.argIsNotNull(id, "id is null");

		// 从数据库查询记录
		Commodity entity = commodityDAO.selectByPrimaryKey(id);
		AssertUtil.argIsNotNull(entity, "商品不存在");

		logger.info(String.format("下架商品开始：%s", BeanUtil.desc(entity, null)));
		entity.setShangjia("下架");
		commodityDAO.update(entity);
		logger.info(String.format("下架商品结束：%s", BeanUtil.desc(entity, null)));
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
