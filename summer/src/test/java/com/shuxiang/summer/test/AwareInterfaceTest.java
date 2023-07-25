package com.shuxiang.summer.test;

import com.shuxiang.summer.context.support.ClassPathXmlApplicationContext;
import com.shuxiang.summer.test.bean.HelloService;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class AwareInterfaceTest {

	@Test
	public void test() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
		HelloService helloService = applicationContext.getBean("helloService", HelloService.class);
		assertNotNull(helloService.getBeanFactory());
		assertNotNull(helloService.getApplicationContext());

	}
}