package com.shuxiang.summer.test.ioc;

import com.shuxiang.summer.context.support.ClassPathXmlApplicationContext;
import com.shuxiang.summer.test.common.event.CustomEvent;
import org.junit.Test;

public class EventAndEventListenerTest {

	@Test
	public void testEventListener() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:event-and-event-listener.xml");
		applicationContext.publishEvent(new CustomEvent(applicationContext));

		applicationContext.registerShutdownHook();//或者applicationContext.close()主动关闭容器;
	}
}