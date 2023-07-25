package com.shuxiang.summer.test.bean;

import com.shuxiang.summer.beans.BeansException;
import com.shuxiang.summer.beans.factory.BeanFactory;
import com.shuxiang.summer.beans.factory.BeanFactoryAware;
import com.shuxiang.summer.context.ApplicationContext;
import com.shuxiang.summer.context.ApplicationContextAware;

public class HelloService implements ApplicationContextAware, BeanFactoryAware {

	private ApplicationContext applicationContext;

	private BeanFactory beanFactory;

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("set bean factory");
		this.beanFactory = beanFactory;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("set application context");
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}
}