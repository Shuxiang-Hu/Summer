package com.shuxiang.summer.test;

import com.shuxiang.summer.context.support.ClassPathXmlApplicationContext;
import com.shuxiang.summer.test.bean.Car;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FactoryBeanTest {

	@Test
	public void testFactoryBean() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:factory-bean.xml");

		Car car = applicationContext.getBean("car", Car.class);
		applicationContext.getBean("car");
		assertEquals(car.getBrand(),"porsche");

	}
}