package com.shuxiang.summer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory {

    private Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();


    public BeanDefinition getBean(String name){
        return beanDefinitionMap.get(name);
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition){
        beanDefinitionMap.put(name,beanDefinition);
    }
}
