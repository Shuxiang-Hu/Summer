package com.shuxiang.summer.beans.factory.support;

import com.shuxiang.summer.beans.BeansException;
import com.shuxiang.summer.beans.factory.config.BeanDefinition;
import com.shuxiang.summer.beans.factory.config.BeanFactory;
import com.shuxiang.summer.beans.factory.config.DefaultSingletonBeanRegistry;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String beanName) {
        Object singleton = this.getSingleton(beanName);
        if(singleton != null){
            return singleton;
        }

        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName,beanDefinition);
    }

    /**
     * Get bean definition from a bean name
     **/
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * ceate object from bean definition and give it a name
     * @param beanName name for the bean
     * @param beanDefinition definition for the bean
     * @throws BeansException exception related to bean
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;
}
