package com.shuxiang.summer.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.shuxiang.summer.beans.BeansException;
import com.shuxiang.summer.beans.PropertyValue;
import com.shuxiang.summer.beans.factory.config.BeanDefinition;
import com.shuxiang.summer.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

public abstract  class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{


    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition,Object[] args) throws BeansException {

        Object newInstance = null;
        try {
            newInstance = createBeanInstance(beanDefinition,beanName,args);
            applyPropertyValues(beanName,newInstance,beanDefinition);
        } catch (Exception e){
            throw new BeansException("Instantiation of bean failed", e);
        }


        addSingleton(beanName,newInstance);
        return newInstance;
    }

    protected void applyPropertyValues(String beanName, Object newInstance, BeanDefinition beanDefinition) {

        try{
            for(PropertyValue pv:beanDefinition.getPropertyValues().getPropertyValues()){
                String fieldName = pv.getName();
                Object value = pv.getValue();

                if(value instanceof BeanReference){
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());

                }

                BeanUtil.setFieldValue(newInstance,fieldName,value);
            }
        } catch (Exception e){
            throw new BeansException("Error setting property values：" + beanName,e);
        }




    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor ctor : declaredConstructors) {
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
