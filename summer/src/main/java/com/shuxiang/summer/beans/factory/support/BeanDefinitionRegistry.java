package com.shuxiang.summer.beans.factory.support;

import com.shuxiang.summer.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
