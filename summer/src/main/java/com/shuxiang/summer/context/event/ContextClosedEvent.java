package com.shuxiang.summer.context.event;

import com.shuxiang.summer.context.ApplicationContext;

public class ContextClosedEvent extends ApplicationContextEvent {

	public ContextClosedEvent(ApplicationContext source) {
		super(source);
	}
}
