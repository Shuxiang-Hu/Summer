package com.shuxiang.summer.beans.factory;

public interface InitializingBean {

	void afterPropertiesSet() throws Exception;
}