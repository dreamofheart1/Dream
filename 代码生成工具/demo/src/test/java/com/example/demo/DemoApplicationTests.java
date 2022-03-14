package com.example.demo;

import com.example.demo.Controller.DaoSupport;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

//@SpringBootTest
class DemoApplicationTests {
    @Resource
    private DaoSupport daoSupport;

    @Test
    void contextLoads() throws Exception {
        daoSupport.findTableMetaData("t_sys_user");
    }
    @Test
    public void tf(){

        System.out.println(String.class.toString());
        System.out.println(int.class.toString());
        System.out.println(Integer.class.toString());

    }

}
