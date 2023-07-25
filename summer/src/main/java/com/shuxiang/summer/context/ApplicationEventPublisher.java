package com.shuxiang.summer.context;

public interface ApplicationEventPublisher {

	/**
	 * 发布事件
	 *
	 * @param event
	 */
	void publishEvent(ApplicationEvent event);
}
