package com.wei.controller.user;

import com.wei.model.user.UserInfo;
import com.wei.service.user.UserInfoService;
import com.wei.util.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

;import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;

/**
 * Description
 * Author ed
 * Created 2017-07-31 16:42
 */
@Controller
@RequestMapping(value = "/user/")
public class UserController {

    Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "register")
    @ResponseBody
    public Response register(HttpServletRequest request,UserInfo userInfo){
        try {
            //TODO 校验，邮箱、用户名

            Integer id = userInfoService.insert(userInfo);
            if(id > 0){
                request.getSession().setAttribute("currentUser",userInfo);
                return new Response().success(userInfo);
            }else{
                return new Response().failure("注册失败，请联系管理员。");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @RequestMapping(value = "login")
    @ResponseBody
    public Response login(UserInfo userInfo){
        try {
            UserInfo info = userInfoService.selectOne(userInfo);
            if(null != info){
                return new Response().success();
            }else{
                return new Response().failure();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "toLogin")
    public String toLogin(){
        return "login";
    }
}
