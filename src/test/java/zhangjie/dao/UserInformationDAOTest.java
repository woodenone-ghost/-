package zhangjie.dao;

import org.junit.Test;
import org.springframework.util.DigestUtils;

import zhangjie.model.UserInformation;
import zhangjie.util.ApplicationContextUtil;

public class UserInformationDAOTest extends BaseTest {
	private UserInformationDAO dao = ApplicationContextUtil.getBean("userInformationDAO");

	@Test
	public void test() {
		UserInformation entity=new UserInformation();
		for(int i=1;i<=10;i++) {
			entity.setAccount("zhangjie"+i);
			String password="123123"+i;
			String dbPassword=DigestUtils.md5DigestAsHex(password.getBytes());
			entity.setPassword(dbPassword);
			entity.setIdentity("buyer");
			dao.add(entity);
		}
	}
}
