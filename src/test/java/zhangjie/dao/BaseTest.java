package zhangjie.dao;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class BaseTest {
	protected ApplicationContext ctx = null;

	public BaseTest() {
		PropertyConfigurator.configure("src/test/resources/log4j.properties");
		ctx = new FileSystemXmlApplicationContext("src/test/resources/applicationContext.xml");
	}
}
