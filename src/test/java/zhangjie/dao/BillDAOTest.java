package zhangjie.dao;

import java.util.Date;

import org.junit.Test;

import zhangjie.model.Bill;
import zhangjie.util.ApplicationContextUtil;

public class BillDAOTest extends BaseTest {
	private BillDAO dao = ApplicationContextUtil.getBean("billDAO");

	@Test
	public void test() {
		Bill entity = new Bill();
		Date time=new Date();
		for (int i = 1; i <= 25; i++) {
			entity.setIdBuyer(21);
			entity.setIdCommodity(3);
			entity.setIdSeller(28);
			entity.setQuantity(1);
			entity.setPrice(0.23+i*19);
			entity.setTime(time);
			entity.setState("未完成");
			dao.add(entity);
		}
	}
}
