package com.wei.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.wei.model.homePage.*;
import com.wei.service.homePage.ContactMeService;
import com.wei.service.homePage.MovieService;
import com.wei.service.homePage.TalkService;
import com.wei.util.ClasspathPropertiesUtil;
import com.wei.util.CommonUtils;
import com.wei.util.MD5Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Created by dong on 2017/11/4.
 */
@Controller
@RequestMapping(value = "/homepage/")
public class HomePageController {

    Logger logger = Logger.getLogger(HomePageController.class);

    @Autowired
    private MovieService movieService;

    @Autowired
    private ContactMeService contactMeService;

    @Autowired
    private TalkService talkService;

    private static String MY_DOMAIN = ClasspathPropertiesUtil.getInstance().get("my_domain", String.class);

    /**
     * 暂时不用
     * @return
     */
    @RequestMapping(value = "getMovie",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getMovie(){
        try{
            Map<String,Object> movies = new HashMap<String, Object>();
            movies.put("img","http://localhost:8080/ft5_69_splash/images/moonboy.jpg");
            movies.put("title","月光男孩");
            movies.put("intro","奇伦的母亲宝拉吸毒成瘾根本无心照顾孩子，奇伦从小在孤独中长大...");


            List list = new ArrayList();
            list.add(movies);

            ObjectMapper mapper = new ObjectMapper(); //转换器
            String json = mapper.writeValueAsString(list);
            return json;
        }catch (Exception e){

        }
        return "";
    }

    /**
     * 首页电影前3条
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
        try{
            List<Movie> movieList = movieService.selectMovieTop3(); //TODO 此处只查询3条记录，图 片路径是拼接之后的完整路径
            if(!CollectionUtils.isEmpty(movieList)){
                for(Movie movie:movieList){
                    if(movie.getPlot().length() > 45){
                        movie.setPlot(movie.getPlot().substring(0,45)+"...");
                    }
                    movie.setPic(MY_DOMAIN+movie.getPic());
                }
            }

            ModelAndView productView = new ModelAndView();
            productView.addObject("results", movieList);
            productView.setViewName("wz/index");
            return productView;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得电影列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "movies")
    public ModelAndView movies(HttpServletRequest request, HttpServletResponse response , MovieQuery movieQuery){
        try{
            PageInfo<Movie> pageInfo = movieService.selectMovieAll(movieQuery);
            ModelAndView productView = new ModelAndView();
            if(!CollectionUtils.isEmpty(pageInfo.getList())){
                for(Movie movie:pageInfo.getList()){
                    if(movie.getPlot().length() > 200){
                        movie.setPlot(movie.getPlot().substring(0,200)+"......");
                    }
                    movie.setPic(MY_DOMAIN+movie.getPic());
                }
            }
            productView.addObject("results", pageInfo);
            productView.addObject("typeStr",Movie.MOVIEENUM.getName(movieQuery.getType()));
            productView.setViewName("wz/movieList");
            return productView;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据id获取电影详情
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "movieId/{id}")
    public ModelAndView movieId(HttpServletRequest request, HttpServletResponse response,@PathVariable String id){
        try{
            //String id = request.getParameter("id");

            //电影基本信息
            Movie movie = movieService.getMovieById(id);
            movie.setPic(MY_DOMAIN+movie.getPic());

            //下载地址
            List<MovieDownload> downloads = movieService.selectMovieDownLoadAll(id);

            ModelAndView productView = new ModelAndView();
            productView.addObject("result", movie);
            productView.addObject("downloadList",downloads);
            productView.addObject("typeStr",Movie.MOVIEENUM.getName(movie.getMovieType()));
            productView.setViewName("wz/movieDetail");
            return productView;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 跳转到“联系我”
     * @return
     */
    @RequestMapping(value = "toContactMe")
    public String toContactMe(){
        return "wz/contactMe";
    }

    @RequestMapping(value = "sendMessage",method = RequestMethod.POST)
    @ResponseBody
    public String sendMessage(HttpServletRequest request, HttpServletResponse response ,Contactme contactme){
        try{
            Map map = new HashMap();
            ObjectMapper mapper = new ObjectMapper(); //转换器
            if(StringUtils.isEmpty(contactme.getEmail())){
                map.put("id",0);
                map.put("result","邮件地址不能为空");
                String json = mapper.writeValueAsString(map);
                return json;
            }
            if(StringUtils.isEmpty(contactme.getContent())){
                map.put("id",0);
                map.put("result","留言内容不能为空");
                String json = mapper.writeValueAsString(map);
                return json;
            }
            int id = contactMeService.add(contactme);
            map.put("id",id);
            map.put("result","留言成功");
            String json = mapper.writeValueAsString(map);
            return json;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据随机码进行下载
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "downMovie")
    public String downMovie(HttpServletRequest request, HttpServletResponse response,String randomCode){
        InputStream inputStream=null;
        OutputStream outputStream=null;
        try{
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/x-bittorrent;charset=utf-8");
            //todo 根据randomCode找到详情信息给filename赋值；code的生成规则：wed+名称+10位随机码，然后md5加密
            String fileName = "";
            MovieDownload download = movieService.selectMovieDownloadByAddressCode(randomCode);
            if(null != download){
                fileName = download.getDownloadName();
            }
            fileName = fileName+".torrent";

            //解决中文文件名显示问题
            response.addHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("gb2312"),"ISO8859-1"));

            String path =ClasspathPropertiesUtil.getInstance().get("movie_path", String.class);
            byte[] bytes = new byte[1024];

            File file=new File(path,fileName);
            if(file.exists()){
                inputStream = new FileInputStream(file);
                outputStream = response.getOutputStream();
                int length = 0;
                while ((length = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, length);
                }
                outputStream.flush();
            }else{
                logger.error("-------downMovie-----所下载的文件不存在");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭输入输出流
            if(inputStream!=null) {
                try{
                    inputStream.close();
                }catch (Exception e){
                }
            }
            if(outputStream!=null) {
                try{
                    outputStream.close();
                }catch (Exception e){
                }
            }
        }
        return null;
    }

    /**
     * 跳转到“新增页”
     * @return
     */
    @RequestMapping(value = "toAdd")
    public String toAdd(HttpServletRequest request){
        String password = request.getParameter("password");
        if(password.equals("xusujuan521")){
            return "wz/add";
        }else{
            return "wz/error";
        }
    }

    /**
     * 新增电影
     * @param request
     * @param response
     * @param picFile
     * @param movie
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public String add(HttpServletRequest request, HttpServletResponse response,@RequestParam("picFile") MultipartFile picFile,Movie movie){
        String picPath = ClasspathPropertiesUtil.getInstance().get("pic_path", String.class);
        File saveFile = new File(picPath+picFile.getOriginalFilename());

        String my_domain = ClasspathPropertiesUtil.getInstance().get("my_domain", String.class);

        try{
            //上传
            picFile.transferTo(saveFile);

            //保存
            movie.setPic("/wed/images/"+picFile.getOriginalFilename());

            String id = movieService.insert(movie);
            Map map = new HashMap();
            ObjectMapper mapper = new ObjectMapper(); //转换器
            map.put("id",id);
            map.put("result","保存成功");
            String json = mapper.writeValueAsString(map);
            return json;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 跳转到“talk新增页”
     * @return
     */
    @RequestMapping(value = "toAddTalk")
    public String toAddTalk(HttpServletRequest request){
        String password = request.getParameter("password");
        if(password.equals("xusujuan521")){
            return "wz/addTalk";
        }else{
            return "wz/error";
        }
    }

    /**
     * 新增talk
     * @param request
     * @param response
     * @param talk
     * @return
     */
    @RequestMapping("addTalk")
    @ResponseBody
    public String addTalk(HttpServletRequest request, HttpServletResponse response,Talk talk){
        try {
            String id = talkService.add(talk);
            Map map = new HashMap();
            ObjectMapper mapper = new ObjectMapper(); //转换器
            map.put("id",id);
            map.put("result","保存成功");
            String json = mapper.writeValueAsString(map);
            return json;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "talks")
    public ModelAndView talks(HttpServletRequest request, HttpServletResponse response,TalkQuery talkQuery){
        try{
            PageInfo<Talk> pageInfo = talkService.selectTalkAll(talkQuery);
            ModelAndView productView = new ModelAndView();

            productView.addObject("results", pageInfo);
            productView.addObject("typeStr",Talk.TALKENUM.getName(talkQuery.getType()));
            productView.setViewName("wz/talkList");
            return productView;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "talkId/{id}")
    public ModelAndView talkId(HttpServletRequest request, HttpServletResponse response,@PathVariable String id){
        try{
            //文章基本信息
            Talk talk = talkService.getTalkById(id);

            ModelAndView productView = new ModelAndView();
            productView.addObject("result", talk);
            productView.addObject("typeStr", Talk.TALKENUM.getName(talk.getType()));
            productView.setViewName("wz/talkDetail");
            return productView;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception{
        //生成下载code
        String tep = "wed";
        String fileName = "[勇敢的心][Braveheart (1995)][BD-720P-MP4][中英双字][豆瓣8.8分][3.0GB].mp4";
        String random = CommonUtils.getRandomString(20);    //10位随机码
        System.out.println(MD5Util.md5Encode(tep+fileName+random));

        /*ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println("current:"+classLoader);
        System.out.println("current:"+classLoader.getParent());
        System.out.println("current:"+classLoader.getParent().getParent());*/
    }
}
