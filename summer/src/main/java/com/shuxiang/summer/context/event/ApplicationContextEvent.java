package com.shuxiang.summer.context.event;

import com.shuxiang.summer.context.ApplicationContext;
import com.shuxiang.summer.context.ApplicationEvent;

public abstract class ApplicationContextEvent extends ApplicationEvent {
    public ApplicationContextEvent(ApplicationContext source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
