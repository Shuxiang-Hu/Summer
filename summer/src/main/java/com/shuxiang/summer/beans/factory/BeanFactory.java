package com.shuxiang.summer.beans.factory;

import com.shuxiang.summer.beans.BeansException;

public interface BeanFactory {




   Object getBean(String beanName);

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;
}
