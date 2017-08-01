package com.wei.controller.user;

import com.wei.util.Response;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

;import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description
 * Author ed
 * Created 2017-07-31 16:42
 */
@Controller
@RequestMapping(value = "/user/")
public class UserController {

    Logger logger = Logger.getLogger(UserController.class);


    @RequestMapping(value = "say")
    @ResponseBody
    public Response say(HttpServletRequest request, HttpServletResponse response,Integer num){
        logger.info("-------------------------------say execute--------------------");
        System.out.println("num"+num);
        System.out.println("asdfasd");
        return new Response().success("sdf");
    }

    public static void main(String[] args) {
        System.out.println("dev");
    }



}
