package com.shuxiang.summer.beans.factory.config;

/**
 * Represent a bean that references another bean
 */
public class BeanReference {

	private final String beanName;


	public BeanReference(String beanName) {
		this.beanName = beanName;
	}

	public String getBeanName() {
		return beanName;
	}
}