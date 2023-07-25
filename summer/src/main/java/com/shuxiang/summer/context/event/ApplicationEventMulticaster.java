package com.shuxiang.summer.context.event;

import com.shuxiang.summer.context.ApplicationEvent;
import com.shuxiang.summer.context.ApplicationListener;

public interface ApplicationEventMulticaster {
    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);
}
