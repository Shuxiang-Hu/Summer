package com.shuxiang.summer.test.service;

import com.shuxiang.summer.beans.BeansException;
import com.shuxiang.summer.beans.factory.BeanFactory;
import com.shuxiang.summer.beans.factory.BeanFactoryAware;
import com.shuxiang.summer.context.ApplicationContext;
import com.shuxiang.summer.context.ApplicationContextAware;

public class HelloService implements ApplicationContextAware, BeanFactoryAware {

	private ApplicationContext applicationContext;

	private BeanFactory beanFactory;

	public String sayHello() {
		System.out.println("hello");
		return "hello";
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}
}
