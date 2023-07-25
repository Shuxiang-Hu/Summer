package com.shuxiang.summer.context.event;

import com.shuxiang.summer.beans.BeansException;
import com.shuxiang.summer.beans.factory.BeanFactory;
import com.shuxiang.summer.context.ApplicationEvent;
import com.shuxiang.summer.context.ApplicationListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        this.setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for(ApplicationListener<ApplicationEvent> listener : getApplicationListenerSet()){
            if(supportsEvent(listener,event)){
                listener.onApplicationEvent(event);
            }
        }
    }


    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
        Type type = applicationListener.getClass().getGenericInterfaces()[0];
        Type actualTypeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name: " + className);
        }
        return eventClassName.isAssignableFrom(event.getClass());
    }
}
