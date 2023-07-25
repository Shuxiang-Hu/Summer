package com.shuxiang.summer.context.support;

import com.shuxiang.summer.beans.BeansException;
import com.shuxiang.summer.beans.factory.config.BeanFactoryPostProcessor;
import com.shuxiang.summer.beans.factory.config.BeanPostProcessor;
import com.shuxiang.summer.context.ApplicationEvent;
import com.shuxiang.summer.context.ApplicationListener;
import com.shuxiang.summer.context.ConfigurableApplicationContext;
import com.shuxiang.summer.context.event.ApplicationEventMulticaster;
import com.shuxiang.summer.context.event.ContextClosedEvent;
import com.shuxiang.summer.context.event.ContextRefreshedEvent;
import com.shuxiang.summer.context.event.SimpleApplicationEventMulticaster;
import com.shuxiang.summer.core.io.DefaultResourceLoader;
import com.shuxiang.summer.beans.factory.ConfigurableListableBeanFactory;

import java.util.Collection;
import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {


    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    public abstract ConfigurableListableBeanFactory getBeanFactory();

    @Override
    public Object getBean(String beanName) {

        return getBeanFactory().getBean(beanName);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name,requiredType);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public void refresh() throws BeansException {
        //创建 beanfactory 加载 bean definition
        refreshBeanFactory();
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        //添加ApplicationContextAwareProcessor，让继承自ApplicationContextAware的bean能感知bean
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
        //在 bean 的实例化之前，执行BeanFactoryPostProcessors
        invokeBeanFactoryPostProcessors(beanFactory);

        //BeanPostProcessor需要提前与其他bean实例化之前注册
        registerBeanPostProcessors(beanFactory);


        //初始化事件发布者
        initApplicationEventMulticaster();

        //注册事件监听器
        registerListeners();

        //提前实例化单例bean
        beanFactory.preInstantiateSingletons();

        finishRefresh();
    }

    protected void initApplicationEventMulticaster(){
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster=
                new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.addSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME,applicationEventMulticaster);
    }

    protected void registerListeners(){
        Collection<ApplicationListener> listeners =
                getBeanFactory().getBeansOfType(ApplicationListener.class).values();

        for(ApplicationListener listener : listeners){
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    protected void finishRefresh(){
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        this.applicationEventMulticaster.multicastEvent(event);
    }

    protected void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {

        Map<String, BeanPostProcessor> beanPostProcessorMap =
                getBeanFactory().getBeansOfType(BeanPostProcessor.class);

        for(BeanPostProcessor beanPostProcessor: beanPostProcessorMap.values()){
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    protected void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap =
                getBeanFactory().getBeansOfType(BeanFactoryPostProcessor.class);

        for(BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values() ){
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }

    }

    /**
     * 创建BeanFactory，并加载BeanDefinition
     *
     * @throws BeansException
     */
    protected abstract void refreshBeanFactory() throws BeansException;
    @Override
    public void close() {
        doClose();
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                close();
            }
        });
    }

    protected void doClose() {
        publishEvent(new ContextClosedEvent(this));
        destroyBeans();
    }

    protected void destroyBeans() {
        getBeanFactory().destroySingletons();
    }
}
