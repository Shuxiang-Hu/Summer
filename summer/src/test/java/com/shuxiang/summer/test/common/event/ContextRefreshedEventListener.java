package com.shuxiang.summer.test.common.event;


import com.shuxiang.summer.context.ApplicationListener;
import com.shuxiang.summer.context.event.ContextRefreshedEvent;

/**
 * @author derekyi
 * @date 2020/12/5
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println(this.getClass().getName());
	}
}
