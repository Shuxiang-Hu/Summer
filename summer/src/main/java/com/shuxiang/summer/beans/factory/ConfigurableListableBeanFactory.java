package com.shuxiang.summer.beans.factory;

import com.shuxiang.summer.beans.BeansException;
import com.shuxiang.summer.beans.factory.config.AutowireCapableBeanFactory;
import com.shuxiang.summer.beans.factory.config.BeanDefinition;
import com.shuxiang.summer.beans.factory.config.BeanPostProcessor;
import com.shuxiang.summer.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

	/**
	 * 根据名称查找BeanDefinition
	 *
	 * @param beanName
	 * @return
	 * @throws BeansException 如果找不到BeanDefintion
	 */
	BeanDefinition getBeanDefinition(String beanName) throws BeansException;
	/**
	 * 提前实例化所有单例实例
	 *
	 * @throws BeansException
	 */
	void preInstantiateSingletons() throws BeansException;

	void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}