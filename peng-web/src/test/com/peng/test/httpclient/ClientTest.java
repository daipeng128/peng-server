package com.peng.test.httpclient;

import org.junit.Test;

/**
 * @Auther: daipeng
 * @Date: 2019/5/20 09:01
 * @Description:
 */
public class ClientTest {

    @Test
    public void test(){

      String json =  ClientUtil.doGet("http://www.weather.com.cn/data/sk/101010700.html");

        System.out.println(json);



    }
}
