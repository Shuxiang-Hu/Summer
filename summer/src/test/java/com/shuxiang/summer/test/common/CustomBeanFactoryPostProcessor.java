package com.shuxiang.summer.test.common;

import com.shuxiang.summer.beans.BeansException;
import com.shuxiang.summer.beans.factory.ConfigurableListableBeanFactory;
import com.shuxiang.summer.beans.PropertyValue;
import com.shuxiang.summer.beans.PropertyValues;
import com.shuxiang.summer.beans.factory.config.BeanDefinition;
import com.shuxiang.summer.beans.factory.config.BeanFactoryPostProcessor;

public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition personBeanDef = beanFactory.getBeanDefinition("person");
        PropertyValues propertyValues = personBeanDef.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name","shuxiang"));
    }
}
