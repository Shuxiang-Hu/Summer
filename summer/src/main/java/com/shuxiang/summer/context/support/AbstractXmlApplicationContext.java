package com.shuxiang.summer.context.support;

import com.shuxiang.summer.beans.factory.support.DefaultListableBeanFactory;
import com.shuxiang.summer.beans.factory.xml.XmlBeanDefinitionReader;

public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory defaultListableBeanFactory) {
        XmlBeanDefinitionReader xmlBeanDefinitionReader =
                new XmlBeanDefinitionReader(defaultListableBeanFactory, this);
        String[] configLocations = getConfigLocations();
        if(configLocations!=null){
            xmlBeanDefinitionReader.loadBeanDefinitions(configLocations);
        }

    }

    protected abstract String[] getConfigLocations();
 }
