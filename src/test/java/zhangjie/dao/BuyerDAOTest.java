package zhangjie.dao;

import org.junit.Test;

import zhangjie.model.Buyer;
import zhangjie.util.ApplicationContextUtil;
import zhangjie.util.RandomUtil;

public class BuyerDAOTest extends BaseTest {
	private BuyerDAO dao = ApplicationContextUtil.getBean("buyerDAO");

	@Test
	public void test() {
		Buyer entity = new Buyer();
		for (int i = 30; i <= 50; i++) {
			entity.setIdBuyer(i);
			entity.setNameBuyer("name_" + i);
			entity.setSexBuyer(RandomUtil.randomSex());
			entity.setAgeBuyer(RandomUtil.randomAge(50, 100));
			entity.setAddressBuyer("address_test_" + i);
			entity.setPastHistoryBuyer("past_history_" + i);
			entity.setChildNameBuyer("child_" + i);
			entity.setChildIdentityBuyer(RandomUtil.randomChildIdentity());
			entity.setChildPhoneBuyer(i + "138172494");
			dao.add(entity);
		}
	}
}
