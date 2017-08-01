package com.wei.service.impl;

;import com.wei.service.TestService;

/**
 * Description
 * Author ed
 * Created 2017-07-25 17:52
 */
public class TestServiceImpl implements TestService {

    public String sayHello(String name) {
        return "hello"+name;
    }
}
