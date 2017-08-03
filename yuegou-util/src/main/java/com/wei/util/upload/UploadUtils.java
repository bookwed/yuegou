package com.wei.util.upload;

import com.wei.util.ClasspathPropertiesUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

;

/**
 * Description
 * Author ed
 * Created 2017-08-03 10:39
 */
public class UploadUtils {

    static Logger logger = Logger.getLogger(UploadUtils.class);

    /**
     * fileupload组件上传步骤：
     1、创建DiskFileItemFactory对象，设置缓冲区大小和临时文件目录。
     2、使用DiskFileItemFactory对象创建ServletFileUpload对象，并设置上传文件的大小限制。
     3、调用ServletFileUpload.parseRequest方法解析request对象，得到了一个保存了所有上传内容的List对象。
     4、对list进行迭代，每迭代一个FileItem对象，调用其isFormField方法判断是否是上传文件。
     4.1 true为普通表单字段，则调用getFiledName、getString方法得到字段名称和字段值。
     4.2 false为上传组件，则调用getInputStream方法得到数据输入流，从而读取上传数据。
     */
    public static UploadDto uploadFile(HttpServletRequest request){

        //boolean isMultipart = ServletFileUpload.isMultipartContent(request);

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

        //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        String savePath = request.getServletContext().getRealPath("/WEB-INF/upload");


        File file = new File(request.getServletContext().getRealPath("/temp"));
        //1、创建一个DiskFileItemFactory工厂，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中
        DiskFileItemFactory disk = new DiskFileItemFactory(1024*100,file);  //缓存大小默认是10k，临时文件目录
        //2、创建一个文件上传解析器
        ServletFileUpload upload = new ServletFileUpload(disk);

        // 解决上传文件中文名称乱码
        upload.setHeaderEncoding("UTF-8");

        //upload.setSizeMax(1024*1024*10);    //设置文件总大小为10M
        //upload.setFileSizeMax(1024*1024);    //设置单个文件上传大小1M

        //监听文件上传进度
        upload.setProgressListener(new ProgressListener() {
            public void update(long pBytesRead, long pContentLength, int arg2) {
                System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
            }
        });

        UploadDto dto = null;
        try {
            List<FileItem> items = upload.parseRequest(request);
            for(FileItem item : items){
                if(item.isFormField()){
                    //非上传组件
                    //System.out.println(item.getFieldName()+"==="+item.getName()+"==="+item.getString());
                }else{
                    //上传组件
                    //System.out.println(item.getFieldName());        //input框的name值
                    String filename = item.getName();
                    filename = filename.substring(filename.lastIndexOf("\\")+1);    //文件名称
                    //System.out.println("****"+item.getString("UTF-8")); //文件内容，如果是文档，会显示文档内容；如果是图片、音频、视频等，会出现乱码

                    //得到上传文件的扩展名
                    String fileExtName = filename.substring(filename.lastIndexOf(".")+1);

                    //随机名称
                    String uuidName = getUUIDFileName(filename);

                    //随机目录
                    String randomDirectory = generateRandomDir(filename);

                    //上传目录
                    String uploadFilePath = ClasspathPropertiesUtil.getInstance().get("upload_file_path", String.class);

                    //文件夹不存在，新建
                    File rd = new File(uploadFilePath,randomDirectory);
                    if(!rd.exists()){
                        rd.mkdirs();
                    }

                    /*InputStream in = item.getInputStream();
                    FileOutputStream out = new FileOutputStream(new File(rd,uuidName));

                    byte buffer[] = new byte[1024];
                    int len = 0;
                    while ((len = in.read(buffer)) > 0){
                        out.write(buffer,0,len);
                    }

                    in.close();
                    out.close();*/

                    IOUtils.copy(item.getInputStream(),new FileOutputStream(new File(rd,uuidName)));

                    dto = new UploadDto(item.getSize(),filename,uuidName,(rd.getPath()+"\\"+uuidName));

                    // 删除临时文件
                    item.delete();
                }
            }
        } catch (FileUploadException e) {
            logger.error("upload file error---FileUploadException:"+e.getMessage());
        } catch (UnsupportedEncodingException e) {
            logger.error("upload file error---UnsupportedEncodingException:"+e.getMessage());
        } catch (FileNotFoundException e) {
            logger.error("upload file error---FileNotFoundException:"+e.getMessage());
        } catch (IOException e) {
            logger.error("upload file error---IOException:"+e.getMessage());
        }
        return dto;
    }

    /**
     * 生成随机目录
     * @param uuidFileName
     * @return
     */
    public static String generateRandomDir(String uuidFileName) {
        int hashCode = uuidFileName.hashCode();
        int d1 = hashCode & 0xf;
        int d2 = (hashCode >>> 4) & 0xf;
        return "/"+d2+"/"+d1;
    }

    /**
     * 生成随机文件名称
     * @param filename
     * @return
     */
    public static String getUUIDFileName(String filename) {
        int index = filename.lastIndexOf(".");
        if (index != -1) {
            return UUID.randomUUID() + filename.substring(index);
        } else {
            return UUID.randomUUID().toString();
        }
    }

    /**
     * 根据id下载
     */
    public void download(UploadDto dto, HttpServletRequest request,HttpServletResponse response){

        //文件路径和随机名称
        File file = new File(dto.getFilePath(),dto.getUuidName());
        if(file.exists()){
            String fileName = dto.getFileName();
            String mimeType = request.getServletContext().getMimeType(fileName);

            try {
                byte b[] = FileUtils.readFileToByteArray(file);
                response.getOutputStream().write(b);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //TODO 多文件上传
}
