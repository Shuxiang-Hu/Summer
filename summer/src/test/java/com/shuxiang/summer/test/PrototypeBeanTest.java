package com.shuxiang.summer.test;

import com.shuxiang.summer.context.support.ClassPathXmlApplicationContext;
import com.shuxiang.summer.test.bean.Car;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class PrototypeBeanTest {

	@Test
	public void testPrototype() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:prototype-bean.xml");

		Car car1 = applicationContext.getBean("car", Car.class);
		Car car2 = applicationContext.getBean("car", Car.class);
		assertFalse(car2 == car1) ;
	}
}