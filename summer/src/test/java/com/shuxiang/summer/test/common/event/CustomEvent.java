package com.shuxiang.summer.test.common.event;


import com.shuxiang.summer.context.ApplicationContext;
import com.shuxiang.summer.context.event.ApplicationContextEvent;

public class CustomEvent extends ApplicationContextEvent {

	public CustomEvent(ApplicationContext source) {
		super(source);
	}
}
