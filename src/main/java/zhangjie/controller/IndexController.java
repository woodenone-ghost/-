package zhangjie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zhangjie.cache.EntityConfigCache;
import zhangjie.constants.Constants;

@Controller
public class IndexController extends BaseController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		if (this.isLogin()) {
			model.addAttribute(Constants.ENTITY_CONF, EntityConfigCache.get(Constants.ENTITY_BILL));
			return "main";
		}
		return "index";
	}
	
	@RequestMapping(value = "/noPermission", method = RequestMethod.GET)
	public String noPermission() {
		return "noPermission";
	}
}
