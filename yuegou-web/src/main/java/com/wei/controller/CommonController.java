package com.wei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dong on 2017/12/5.
 */
@Controller
@RequestMapping(value = "/")
public class CommonController {
    /**
     * @param request
     * @throws Exception
     */
    @RequestMapping("jianli")
    public String jianli(HttpServletRequest request, HttpServletResponse response){
        return "wz/jianli";
    }
}
