package com.wei.controller.commons;

;import com.wei.model.commons.CommonFile;
import com.wei.service.commons.CommonFileService;
import com.wei.util.Response;
import com.wei.util.upload.UploadDto;
import com.wei.util.upload.UploadUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Description
 * Author ed
 * Created 2017-08-03 17:35
 */
@Controller
@RequestMapping(value = "/files/")
public class CommonFileController {

    Logger logger = Logger.getLogger(CommonFileController.class);

    @Autowired
    private CommonFileService commonFileService;

    /**
     * demo
     * @param id
     * @return
     */
    @RequestMapping(value = "findPath/{id}")
    @ResponseBody
    public Response findPath(@PathVariable("id") String id){
        String path = commonFileService.findPath(id);
        return new Response().success(path);
    }

    @RequestMapping(value = "add")
    @ResponseBody
    public Response add(CommonFile commonFile){
        try {
            Integer id = commonFileService.insert(commonFile);
            return new Response().success(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response().failure();
    }


    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
    @ResponseBody
    public Response uploadFile(MultipartHttpServletRequest request){
        try{
            List list = new ArrayList();
            //List<MultipartFile> files = request.getFiles("files");
            Iterator<String> iterator = request.getFileNames();
            //判断上传的文件不为空
            if (!iterator.hasNext()) {
                throw new RuntimeException("上传文件为空");
            }

            Integer emptyFile = 0;
            while (iterator.hasNext()){
                String fileName = iterator.next();
                MultipartFile file = request.getFile(fileName);
                //如果提交的是空文件，日志记录，不作处理
                if (file.isEmpty()) {
                    emptyFile++;
                    logger.info(" file is empty,file name is " + file.getName() + ",skip");
                    continue;
                }
                //TODO 上传
            }

        }catch (Exception e){

        }

        return new Response().success();
    }

    @RequestMapping(value = "/uploadMultipleFile",method = RequestMethod.POST)
    @ResponseBody
    public Response uploadMultipleFile(MultipartHttpServletRequest request){
        String path = request.getServletContext().getRealPath("upload");
        File file  = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        Iterator<String> iterator = request.getFileNames();
        //判断上传的文件不为空
        if (!iterator.hasNext()) {
            throw new RuntimeException("上传文件为空");
        }

        Integer emptyFile = 0;
        while (iterator.hasNext()){
            String fileName = iterator.next();
            MultipartFile file2 = request.getFile(fileName);
            //如果提交的是空文件，日志记录，不作处理
            if (file2.isEmpty()) {
                emptyFile++;
                logger.info(" file is empty,file name is " + file2.getName() + ",skip");
                continue;
            }
            //TODO 上传
        }


        return new Response().success();
    }

    public Response uploadPicture(){
        return new Response().success();
    }

    @RequestMapping(value = "gotoUpload")
    public String gotoUploadPage(HttpServletRequest request, HttpServletResponse response){
        return "upload/upload";
    }

    @RequestMapping(value = "uploadFile")
    @ResponseBody
    public Response uploadFile(HttpServletRequest request,HttpServletResponse response){
        UploadDto dto = UploadUtils.uploadFile(request);
        try {
            //保存到数据库
            CommonFile commonFile = new CommonFile();
            commonFile.setRealName(dto.getFileName());
            commonFile.setUuidName(dto.getUuidName());
            commonFile.setFilePath(dto.getFilePath());
            commonFile.setFileSize(dto.getFileSize());
            commonFileService.insert(commonFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
