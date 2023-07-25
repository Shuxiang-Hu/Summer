package com.shuxiang.summer.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.shuxiang.summer.beans.BeansException;
import com.shuxiang.summer.beans.PropertyValue;
import com.shuxiang.summer.beans.factory.BeanFactoryAware;
import com.shuxiang.summer.beans.factory.DisposableBean;
import com.shuxiang.summer.beans.factory.InitializingBean;
import com.shuxiang.summer.beans.factory.config.AutowireCapableBeanFactory;
import com.shuxiang.summer.beans.factory.config.BeanDefinition;
import com.shuxiang.summer.beans.factory.config.BeanPostProcessor;
import com.shuxiang.summer.beans.factory.config.BeanReference;

import java.lang.reflect.Method;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean = null;
        try{
            bean = instantiateBeanInstance(beanDefinition);
            applyPropertyValues(beanName,bean,beanDefinition);
            bean = initializeBean(beanName, bean, beanDefinition);
        }
        catch (Exception e){
            throw new BeansException("Initialization of bean " + beanName + " failed",e);
        }

        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);
        if (beanDefinition.isSingleton()) {
            addSingleton(beanName, bean);
        }
        return bean;
    }

    protected Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {

        if (bean instanceof BeanFactoryAware) {
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }

        //执行BeanPostProcessor的前置处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        try{
            invokeInitMethods(beanName, wrappedBean, beanDefinition);
        } catch (Throwable ex){
            throw new BeansException("Invocation of init method of bean[" + beanName + "] failed", ex);
        }


        //执行BeanPostProcessor的后置处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        return wrappedBean;
    }

    protected void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        if(bean instanceof InitializingBean){
            ((InitializingBean) bean).afterPropertiesSet();
        }

        String initMethodName = beanDefinition.getInitMethodName();
        if(!StrUtil.isEmpty(initMethodName)){
            Method initMethod = ClassUtil.getPublicMethod(beanDefinition.getBeanClass(), initMethodName);
            if (initMethod == null) {
                throw new BeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
            }
            initMethod.invoke(bean);
        }
    }


    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName)
            throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName)
            throws BeansException {

        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    protected void applyPropertyValues(String beanName, Object instance, BeanDefinition beanDefinition){
        try{
            for(PropertyValue pv : beanDefinition.getPropertyValues().getPropertyValues()){
                String fieldName = pv.getName();
                Object value = pv.getValue();
                if (value instanceof BeanReference) {
                    // beanA依赖beanB，先实例化beanB
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                BeanUtil.setFieldValue(instance,fieldName,value);
            }
        } catch (Exception e){
            throw new BeansException("Error setting properties for bean " + beanName,e);
        }

    }


    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition){
        if(bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())){
            registerDisposableBean(beanName,new DisposableBeanAdaptor(beanName,bean,beanDefinition));
        }

    }
    protected Object instantiateBeanInstance(BeanDefinition beanDefinition){

        return getInstantiationStrategy().instantiate(beanDefinition);
    }
    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }


}
