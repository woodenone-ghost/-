package zhangjie.dao;

import java.time.LocalDate;
import java.util.List;
import org.junit.Test;

import zhangjie.entity.BarChart;
import zhangjie.entity.LineChart;
import zhangjie.util.ApplicationContextUtil;

public class BillDAOTest extends BaseTest {
	private BillDAO dao = ApplicationContextUtil.getBean("billDAO");

	@Test
	public void test() {
		LocalDate now = LocalDate.now();
		LocalDate lastMonth = now.plusMonths(-1);
		LocalDate firstDay = LocalDate.of(now.getYear(), lastMonth.getMonthValue(), 1);
		LocalDate finalDay = firstDay.plusMonths(1).plusDays(-1);
		List<BarChart> barCharts = dao.selectLastMonthSalesVolume(8, firstDay, finalDay);
		System.out.println(barCharts);
	}
}
