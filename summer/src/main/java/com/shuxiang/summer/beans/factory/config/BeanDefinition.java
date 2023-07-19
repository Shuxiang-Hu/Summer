package com.shuxiang.summer.beans.factory.config;

import com.shuxiang.summer.beans.PropertyValue;
import com.shuxiang.summer.beans.PropertyValues;

public class BeanDefinition {

    private Class beanClass;
    private PropertyValues propertyValues;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }


    public Class getBeanClass() {
        return beanClass;
    }

    public PropertyValue[] getPropertyValues() {
        return propertyValues.getPropertyValues();
    }
}
