package com.shuxiang.summer.beans.factory.support;

import com.shuxiang.summer.beans.BeansException;
import com.shuxiang.summer.beans.factory.config.BeanDefinition;

public interface InstantiationStrategy {

	Object instantiate(BeanDefinition beanDefinition) throws BeansException;
}