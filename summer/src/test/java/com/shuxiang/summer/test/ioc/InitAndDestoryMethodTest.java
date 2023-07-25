package com.shuxiang.summer.test.ioc;

import com.shuxiang.summer.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

public class InitAndDestoryMethodTest {

	@Test
	public void testInitAndDestroyMethod() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:init-and-destroy-method.xml");
		applicationContext.registerShutdownHook();  //或者手动关闭 applicationContext.close();
	}
}