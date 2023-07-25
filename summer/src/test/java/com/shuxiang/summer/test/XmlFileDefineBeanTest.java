package com.shuxiang.summer.test;

import com.shuxiang.summer.beans.factory.support.DefaultListableBeanFactory;
import com.shuxiang.summer.beans.factory.xml.XmlBeanDefinitionReader;
import com.shuxiang.summer.test.bean.Car;
import com.shuxiang.summer.test.bean.Person;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class XmlFileDefineBeanTest {

	@Test
	public void testXmlFile() throws Exception {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
		beanDefinitionReader.loadBeanDefinitions("classpath:init-and-destroy-method.xml");

		Person person = (Person) beanFactory.getBean("person");
		System.out.println(person);

		assertEquals(person.getName(),"derek");
		assertEquals(person.getAge(),18);
		Car car = person.getCar();
		assertNotNull(car);
		assertEquals(car.getBrand(),"porsche");
	}
}