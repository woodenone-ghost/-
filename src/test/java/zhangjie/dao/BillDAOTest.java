package zhangjie.dao;

import java.util.List;
import org.junit.Test;

import zhangjie.entity.LineChart;
import zhangjie.util.ApplicationContextUtil;

public class BillDAOTest extends BaseTest {
	private BillDAO dao = ApplicationContextUtil.getBean("billDAO");

	@Test
	public void test() {
		List<LineChart> list = dao.selectDateAndSalesVolumeById(8);
		System.out.println(list);
		List<LineChart> list1 = dao.selectDateAndSalesVolumeById(8);
		System.out.println(list1);
	}
}
