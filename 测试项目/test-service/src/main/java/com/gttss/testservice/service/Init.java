package com.gttss.testservice.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @program: RfidLibrary
 * @description: 项目启动时需要执行的代码
 * @author: Mr.Dream
 * @create: 2022-03-25 14:03
 **/
@Component
public class Init implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
