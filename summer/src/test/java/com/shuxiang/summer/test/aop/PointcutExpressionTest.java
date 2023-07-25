package com.shuxiang.summer.test.aop;

import com.shuxiang.summer.aop.AspectJExpressionPointcut;
import com.shuxiang.summer.test.service.HelloService;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertTrue;

public class PointcutExpressionTest {

	@Test
	public void testPointcutExpression() throws Exception {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.shuxiang.summer.test.service.HelloService.*(..))");
		Class<HelloService> clazz = HelloService.class;
		Method method = clazz.getDeclaredMethod("sayHello");

		assertTrue(pointcut.matches(clazz));
		assertTrue(pointcut.matches(method, clazz));

	}
}