package com.shuxiang.summer.beans.factory.support;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.shuxiang.summer.beans.BeansException;
import com.shuxiang.summer.beans.factory.DisposableBean;
import com.shuxiang.summer.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

public class DisposableBeanAdaptor implements DisposableBean {

    private String beanName;
    private Object bean;
    private String destroyMethodName;

    public DisposableBeanAdaptor(String beanName, Object bean, BeanDefinition beanDefinition) {
        this.beanName = beanName;
        this.bean = bean;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        if(bean instanceof DisposableBean){
            ((DisposableBean)bean).destroy();
        }

        //避免同时继承自DisposableBean，且自定义方法与DisposableBean方法同名，销毁方法执行两次的情况
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            //执行自定义方法
            Method destroyMethod = ClassUtil.getPublicMethod(bean.getClass(), destroyMethodName);
            if (destroyMethod == null) {
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }
    }
}
