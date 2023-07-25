package com.shuxiang.summer.context.support;

import com.shuxiang.summer.beans.BeansException;
import com.shuxiang.summer.beans.factory.ConfigurableListableBeanFactory;
import com.shuxiang.summer.beans.factory.support.DefaultListableBeanFactory;

public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{

    DefaultListableBeanFactory beanFactory;

    @Override
    public ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    @Override
    protected final void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory defaultListableBeanFactory = createBeanFactory();
        loadBeanDefinitions(defaultListableBeanFactory);
        this.beanFactory = defaultListableBeanFactory;
    }

    /**
     * 创建bean工厂
     *
     * @return
     */
    protected DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }


    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory defaultListableBeanFactory);
}
