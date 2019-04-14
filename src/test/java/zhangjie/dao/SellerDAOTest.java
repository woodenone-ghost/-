package zhangjie.dao;

import java.util.Random;

import org.junit.Test;

import zhangjie.model.Seller;
import zhangjie.util.ApplicationContextUtil;

public class SellerDAOTest extends BaseTest {
	private SellerDAO dao = ApplicationContextUtil.getBean("sellerDAO");

	@Test
	public void test() {
		Seller seller=new Seller();
		seller.setNameSeller("老人服务专卖");
		seller.setAddressSeller("威海市环翠区");
		seller.setGoodSeller(50);
		seller.setBadSeller(10);
	//	dao.add(seller);
		
		Seller entity=new Seller();
		Random random=new Random();
		for(int i=1;i<=10;i++) {
			entity.setNameSeller("nameSeller_"+i);
			entity.setAddressSeller("addressSeller_"+i);
			entity.setGoodSeller(random.nextInt(100));
			entity.setBadSeller(random.nextInt(15));
		//	dao.add(entity);
		}
		
		dao.delete(8);
		seller.setIdSeller(4);
		seller.setAddressSeller("SH");
		dao.update(seller);
		System.out.println(dao.selectByPrimaryKey(1).toString());
	}

}
