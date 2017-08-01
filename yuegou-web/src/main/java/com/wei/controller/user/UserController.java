package com.wei.controller.user;

import com.wei.util.Response;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

;import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

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

    @RequestMapping(value = "uploadFile")
    @ResponseBody
    public Response uploadFile(HttpServletRequest request,HttpServletResponse response){
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        //具体上传实现
        try {
            /*BufferedReader reader = null;
            InputStream is = request.getInputStream();
            byte[] b = new byte[1024];
            int len = -1;
            reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            String line = null;
            StringBuffer stringBuffer = new StringBuffer();
            while ((line = reader.readLine()) != null){
                stringBuffer.append(line);
            }
            is.close();
            System.out.println(stringBuffer.toString());*/

            /**
             * fileupload组件上传步骤：
             1、创建DiskFileItemFactory对象，设置缓冲区大小和临时文件目录。
             2、使用DiskFileItemFactory对象创建ServletFileUpload对象，并设置上传文件的大小限制。
             3、调用ServletFileUpload.parseRequest方法解析request对象，得到了一个保存了所有上传内容的List对象。
             4、对list进行迭代，每迭代一个FileItem对象，调用其isFormField方法判断是否是上传文件。
             4.1 true为普通表单字段，则调用getFiledName、getString方法得到字段名称和字段值。
             4.2 false为上传组件，则调用getInputStream方法得到数据输入流，从而读取上传数据。
             */
            File file = new File(request.getServletContext().getRealPath("/temp"));
            DiskFileItemFactory disk = new DiskFileItemFactory(1024*100,file);
            ServletFileUpload upload = new ServletFileUpload(disk);

            // 解决上传文件中文名称乱码
            upload.setHeaderEncoding("UTF-8");

            //设置文件总大小为10M
            upload.setSizeMax(1024*1024*10);

            List<FileItem> items = upload.parseRequest(request);

            for(FileItem item : items){

                if(item.isFormField()){
                    //非上传组件
                    System.out.println(item.getFieldName()+"==="+item.getName()+"==="+item.getString());
                }else{
                    //上传组件
                    System.out.println("上传组件===========");
                    System.out.println(item.getFieldName());        //input框的name值
                    String filename = item.getName();
                    filename = filename.substring(filename.lastIndexOf("\\")+1);    //文件名称
                    System.out.println("---------"+filename);
                    System.out.println("****"+item.getString("UTF-8")); //文件内容，文档信息是中文，会出现乱码

                    //FileOutputStream fos = new FileOutputStream("D:/uploads/"+name);
                    //IOUtils.copy(item.getInputStream(),fos);

                    //随机名称
                    String uuidName = UserController.getUUIDFileName(filename);

                    //随机目录
                    String randomDirectory = UserController.generateRandomDir(filename);

                    //上传路径
                    String filePath = "D:/uploads/"+randomDirectory;
                    System.out.println("xxxxxxxx====="+filePath);


                    File rd = new File("D:/uploads/",randomDirectory);
                    if(!rd.exists()){
                        rd.mkdirs();
                    }
                    IOUtils.copy(item.getInputStream(),new FileOutputStream(new File(rd,uuidName)));

                    // 删除临时文件
                    item.delete();
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Response().success("upload success");
    }

    @RequestMapping(value = "gotoUpload")
    public String gotoUploadPage(HttpServletRequest request,HttpServletResponse response){
        return "upload/upload";
    }

    public static String generateRandomDir(String uuidFileName) {
        int hashCode = uuidFileName.hashCode();
        int d1 = hashCode & 0xf;
        int d2 = (hashCode >>> 4) & 0xf;
        return "/"+d2+"/"+d1;
    }
    public static String getUUIDFileName(String filename) {
        int index = filename.lastIndexOf(".");
        if (index != -1) {
            return UUID.randomUUID() + filename.substring(index);
        } else {
            return UUID.randomUUID().toString();
        }
    }
}
