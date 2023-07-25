package com.shuxiang.summer.beans.factory;

import com.shuxiang.summer.beans.BeansException;

public interface DisposableBean {

    void destroy() throws Exception;
}
