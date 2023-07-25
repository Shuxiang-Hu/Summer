package com.shuxiang.summer.context.event;

import com.shuxiang.summer.context.ApplicationContext;

public class ContextRefreshedEvent extends ApplicationContextEvent {

	public ContextRefreshedEvent(ApplicationContext source) {
		super(source);
	}
}
