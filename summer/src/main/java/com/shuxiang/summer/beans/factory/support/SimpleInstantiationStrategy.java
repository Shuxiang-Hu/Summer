package com.shuxiang.summer.beans.factory.support;

import com.shuxiang.summer.beans.BeansException;
import com.shuxiang.summer.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SimpleInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition) throws BeansException {
        Class beanClass = beanDefinition.getBeanClass();
        Object bean = null;
        try {
            Constructor constructor = beanClass.getDeclaredConstructor();
            return constructor.newInstance();
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            throw new BeansException("Creat bean of type  " + beanClass.getName() + " failed",e);

        }


    }
}
