package com.shuxiang.summer.test.common;

import com.shuxiang.summer.beans.BeansException;
import com.shuxiang.summer.beans.factory.config.BeanPostProcessor;
import com.shuxiang.summer.test.bean.Car;

public class CustomerBeanPostProcessor implements BeanPostProcessor {
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("CustomerBeanPostProcessor#postProcessBeforeInitialization");
		//换兰博基尼
		if ("car".equals(beanName)) {
			((Car) bean).setBrand("lamborghini");
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("CustomerBeanPostProcessor#postProcessAfterInitialization");
		return bean;
	}
}