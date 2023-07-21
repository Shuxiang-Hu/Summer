package com.shuxiang.summer.beans.factory;

import com.shuxiang.summer.beans.BeansException;
import com.shuxiang.summer.beans.factory.config.AutowireCapableBeanFactory;
import com.shuxiang.summer.beans.factory.config.BeanDefinition;
import com.shuxiang.summer.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

}