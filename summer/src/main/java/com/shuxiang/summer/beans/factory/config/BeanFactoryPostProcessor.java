package com.shuxiang.summer.beans.factory.config;

import com.shuxiang.summer.beans.BeansException;
import com.shuxiang.summer.beans.factory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {

	/**
	 * 在所有BeanDefintion加载完成后，但在bean实例化之前，提供修改BeanDefinition属性值的机制
	 *
	 * @param beanFactory
	 * @throws BeansException
	 */
	void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}