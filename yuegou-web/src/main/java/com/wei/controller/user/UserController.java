package com.wei.controller.user;

import com.wei.util.Response;
import com.wei.util.upload.UploadDto;
import com.wei.util.upload.UploadUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Encoder;

;import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

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
        return new Response().success("sdf");
    }

    @RequestMapping(value="createQrcode")
    @ResponseBody
    public Response createQrcode(String name){

        return new Response().success("");
    }

    @RequestMapping(value = "gotoUpload")
    public String gotoUploadPage(HttpServletRequest request,HttpServletResponse response){
        return "upload/upload";
    }

    @RequestMapping(value = "uploadFile")
    @ResponseBody
    public Response uploadFile(HttpServletRequest request,HttpServletResponse response){
        UploadDto dto = UploadUtils.uploadFile(request);
        return new Response().success(dto.getFilePath());
    }

    @RequestMapping(value = "downloadFile/{id}")
    public void downloadFile(HttpServletRequest request,HttpServletResponse response,@PathVariable("id") String id){
        System.out.println(id);
        //文件路径和随机名称
        File file = new File("D:\\uploads\\0\\9\\76705a9b-5483-4b5d-b584-f8466b222bd9.sql");
        if(file.exists()){
            String fileName = "ss.sql";
            String mimeType = request.getServletContext().getMimeType(fileName);
            response.setContentType(mimeType);
            try {
                String agent = request.getHeader("user-agent");
                if (agent.contains("MSIE")) {
                    // IE浏览器
                    fileName = URLEncoder.encode(fileName, "utf-8");
                } else if (agent.contains("Firefox")) {
                    // 火狐浏览器
                    BASE64Encoder base64Encoder = new BASE64Encoder();
                    fileName = "=?utf-8?B?"
                            + base64Encoder.encode(fileName.getBytes("utf-8"))
                            + "?=";
                } else {
                    // 其它浏览器
                    fileName = URLEncoder.encode(fileName, "utf-8");
                }
                response.setHeader("content-disposition",
                        "attachment;filename=" + fileName);

                byte b[] = FileUtils.readFileToByteArray(file);
                response.getOutputStream().write(b);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
