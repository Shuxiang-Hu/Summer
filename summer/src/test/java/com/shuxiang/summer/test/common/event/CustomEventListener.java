package com.shuxiang.summer.test.common.event;

import com.shuxiang.summer.context.ApplicationListener;

/**
 * @author derekyi
 * @date 2020/12/5
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {

	@Override
	public void onApplicationEvent(CustomEvent event) {
		System.out.println(this.getClass().getName());
	}
}
