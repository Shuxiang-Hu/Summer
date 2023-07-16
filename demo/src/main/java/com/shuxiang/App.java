package com.shuxiang;

import com.shuxiang.service.impl.UserServiceImpl;
import com.shuxiang.service.UserService;
import com.shuxiang.summer.BeanDefinition;
import com.shuxiang.summer.BeanFactory;

public class App {

    public static void main(String[] args) {
        //create instance
        UserService userService = new UserServiceImpl();

        //reigster bean
        BeanDefinition beanDefinition = new BeanDefinition(userService);
        BeanFactory beanFactory = new BeanFactory();
        String userServiceBeanName = "userService";
        beanFactory.registerBeanDefinition(userServiceBeanName,beanDefinition);


        //get bean and use
        BeanDefinition bean = beanFactory.getBean(userServiceBeanName);
        UserService beanObj = (UserService) bean.getBean();
        beanObj.queryUserInfo();
    }
}
