package com.shuxiang.summer.beans.factory.config;

import com.shuxiang.summer.beans.BeansException;
import com.shuxiang.summer.beans.factory.config.BeanDefinition;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public interface BeanFactory {



    public Object getBean(String name) throws BeansException;

}
