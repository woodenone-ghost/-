package zhangjie.dao;

import org.junit.Test;

import zhangjie.model.Commodity;
import zhangjie.util.ApplicationContextUtil;
import zhangjie.util.RandomUtil;

@SuppressWarnings("unused")
public class CommodityDAOTest extends BaseTest {
	private CommodityDAO dao = ApplicationContextUtil.getBean("commodityDAO");

	@Test
	public void test() {
		Commodity entity = new Commodity();
		for (int i = 0; i <= 25; i++) {
			entity.setIcon("icon_"+i);
			entity.setName("name_"+i);
		//	entity.setPrice(1.23+i);
			entity.setCategory("category_"+i);
			entity.setBusinessName("张杰公司");
			entity.setCharacteristic("characteristic_"+i);
			entity.setSalesVolume(0);
		//	entity.setEvaluation("0");
			dao.add(entity);
		}
	}
}
