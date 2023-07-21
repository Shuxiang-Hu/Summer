package com.shuxiang.summer.beans.factory;

import com.shuxiang.summer.beans.BeansException;

public interface BeanFactory {



    public Object getBean(String beanName) throws BeansException;

    public Object getBean(String beanName, Object ... args) throws BeansException;

    public <T> T getBean(String name, Class<T> requiredType) throws BeansException;
}
