package com.shuxiang.summer.beans.factory;

import com.shuxiang.summer.beans.BeansException;
import com.shuxiang.summer.beans.factory.config.BeanDefinition;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public interface BeanFactory {



    public Object getBean(String beanName) throws BeansException;

    public Object getBean(String beanName, Object ... args) throws BeansException;
}
