package zhangjie.util;

import org.junit.Test;
import org.springframework.util.DigestUtils;

import zhangjie.dao.BaseTest;

public class MD5 extends BaseTest {
	@Test
	public void test() {
		String password="123123";
		String password1 = DigestUtils.md5DigestAsHex(password.getBytes());
		System.out.println(password1);
	}
}
