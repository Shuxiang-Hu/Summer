package com.shuxiang.summer.beans.factory.support;

import com.shuxiang.summer.beans.BeansException;
import com.shuxiang.summer.beans.factory.FactoryBean;
import com.shuxiang.summer.beans.factory.config.BeanDefinition;
import com.shuxiang.summer.beans.factory.config.BeanPostProcessor;
import com.shuxiang.summer.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory{
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    private final Map<String, Object> factoryBeanObjectCache = new HashMap<>();

    @Override
    public Object getBean(String beanName) {
        Object singleton = getSingleton(beanName);
        if(singleton != null){
            return getObjectForBeanInstance(singleton,beanName);
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);

        return getObjectForBeanInstance(createBean(beanName,beanDefinition),beanName);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return ((T) getBean(name));
    }
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }


    protected Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        if(!(beanInstance instanceof FactoryBean)){
            return beanInstance;
        }
        FactoryBean factoryBean = (FactoryBean) beanInstance;
        Object object;
        try {
            if(factoryBean.isSingleton()){
                object = this.factoryBeanObjectCache.get(beanName);
                if(object == null){

                    object = factoryBean.getObject();
                    this.factoryBeanObjectCache.put(beanName,object);

                }
            } else{
                object = factoryBean.getObject();
            }
        } catch (Exception ex){
            throw new BeansException("FactoryBean threw exception on object[" + beanName + "] creation", ex);
        }



        return object;
    }
}
