package com.shuxiang.summer.beans.factory;

import com.shuxiang.summer.beans.BeansException;

import java.beans.Beans;

public interface BeanFactoryAware {


    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
