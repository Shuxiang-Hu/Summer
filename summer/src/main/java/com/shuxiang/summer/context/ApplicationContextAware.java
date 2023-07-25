package com.shuxiang.summer.context;

import com.shuxiang.summer.beans.factory.Aware;

public interface ApplicationContextAware extends Aware {

    public void setApplicationContext(ApplicationContext applicationContext);
}
