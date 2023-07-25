package com.shuxiang.summer.context.event;

import com.shuxiang.summer.beans.BeansException;
import com.shuxiang.summer.beans.factory.BeanFactory;
import com.shuxiang.summer.beans.factory.BeanFactoryAware;
import com.shuxiang.summer.context.ApplicationEvent;
import com.shuxiang.summer.context.ApplicationListener;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    private Set<ApplicationListener<ApplicationEvent>> applicationListenerSet = new HashSet<>();

    private BeanFactory beanFactory;
    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        this.applicationListenerSet.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        this.applicationListenerSet.remove(listener);
    }



    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public Set<ApplicationListener<ApplicationEvent>> getApplicationListenerSet() {
        return applicationListenerSet;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
