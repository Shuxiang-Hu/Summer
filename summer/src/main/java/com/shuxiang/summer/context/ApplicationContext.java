package com.shuxiang.summer.context;

import com.shuxiang.summer.beans.factory.HierarchicalBeanFactory;
import com.shuxiang.summer.beans.factory.ListableBeanFactory;
import com.shuxiang.summer.core.io.ResourceLoader;

public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader,ApplicationEventPublisher {

}