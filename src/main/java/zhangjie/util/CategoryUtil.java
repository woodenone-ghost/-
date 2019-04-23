package zhangjie.util;

public class CategoryUtil {
	public static String zhujie = "助洁";
	public static String zhucan = "助餐";
	public static String zhuyi = "助医";
	public static String kanglefuwu = "康乐服务";

	public static String wholeCategory(String category) {
		if (category.equals("购物") || category.equals("打扫")) {
			return zhujie + "-" + category;
		} else if (category.equals("上门助餐") || category.equals("老年食堂")) {
			return zhucan + "-" + category;
		} else if (category.equals("护理保健") || category.equals("上门诊治")) {
			return zhuyi + "-" + category;
		} else {
			return kanglefuwu + "-" + category;
		}

	}
}
