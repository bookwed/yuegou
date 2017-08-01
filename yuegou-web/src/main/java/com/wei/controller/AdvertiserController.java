package com.wei.controller;

;import com.wei.util.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description
 * Author ed
 * Created 2017-07-28 17:13
 */
@RestController
public class AdvertiserController {


    @RequestMapping(value = "/add")
    public Response createObj(@RequestBody Object object){

        boolean flag = true;//调用service
        if(flag){
            return new Response().success(new Object());
        }else{
            return new Response().failure("error");
        }
    }
}
