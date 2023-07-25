package com.shuxiang.summer.test.ioc;

import com.shuxiang.summer.beans.PropertyValue;
import com.shuxiang.summer.beans.PropertyValues;
import com.shuxiang.summer.beans.factory.config.BeanDefinition;
import com.shuxiang.summer.beans.factory.config.BeanReference;
import com.shuxiang.summer.beans.factory.support.DefaultListableBeanFactory;
import com.shuxiang.summer.test.bean.Car;
import com.shuxiang.summer.test.bean.Person;
import org.junit.Test;

import static org.junit.Assert.*;

public class PopulateBeanWithPropertyValuesTest {

	/**
	 * 为bean注入bean
	 *
	 * @throws Exception
	 */
	@Test
	public void testPopulateBeanWithBean() throws Exception {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		//注册Car实例
		PropertyValues propertyValuesForCar = new PropertyValues();
		propertyValuesForCar.addPropertyValue(new PropertyValue("brand", "porsche"));
		BeanDefinition carBeanDefinition = new BeanDefinition(Car.class, propertyValuesForCar);
		beanFactory.registerBeanDefinition("car", carBeanDefinition);

		//注册Person实例
		PropertyValues propertyValuesForPerson = new PropertyValues();
		propertyValuesForPerson.addPropertyValue(new PropertyValue("name", "derek"));
		propertyValuesForPerson.addPropertyValue(new PropertyValue("age", 18));
		//Person实例依赖Car实例
		propertyValuesForPerson.addPropertyValue(new PropertyValue("car", new BeanReference("car")));
		BeanDefinition beanDefinition = new BeanDefinition(Person.class, propertyValuesForPerson);
		beanFactory.registerBeanDefinition("person", beanDefinition);

		Person person = (Person) beanFactory.getBean("person");
		System.out.println(person);
		assertEquals(person.getName(),"derek");
		assertEquals(person.getAge(),18);
		Car car = person.getCar();
		assertNotNull(car);
		assertEquals(car.getBrand(),"porsche");

	}
}