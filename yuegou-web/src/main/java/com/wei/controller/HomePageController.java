package com.wei.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.wei.model.homePage.Movie;
import com.wei.model.homePage.MovieDownload;
import com.wei.model.homePage.MovieQuery;
import com.wei.service.homePage.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by dong on 2017/11/4.
 */
@Controller
@RequestMapping(value = "/homepage/")
public class HomePageController {

    @Autowired
    private MovieService movieService;

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

    public static void main(String[] args) {
        System.out.printf("asdfghi".substring(0,3));
    }

    /**
     * 首页电影前3条
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "getMoviesTop3")
    public ModelAndView getMoviesTop3(HttpServletRequest request, HttpServletResponse response){
        try{
            List<Movie> movieList = movieService.selectMovieTop3(); //TODO 此处只查询3条记录，图 片路径是拼接之后的完整路径
            if(!CollectionUtils.isEmpty(movieList)){
                for(Movie movie:movieList){
                    if(movie.getPlot().length() > 45){
                        movie.setPlot(movie.getPlot().substring(0,45)+"...");
                    }
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
    @RequestMapping(value = "getMovies")
    public ModelAndView getMovies(HttpServletRequest request, HttpServletResponse response ,MovieQuery movieQuery){
        try{

            /*String pageNum = request.getParameter("pageNum")==null?"1":request.getParameter("pageNum");
            String pageSize = request.getParameter("pageSize")==null?"2":request.getParameter("pageSize");
            //todo 设置参数
            PageInfo<Movie> pageInfo = movieService.selectMovieAll(Integer.parseInt(pageNum),Integer.parseInt(pageSize));*/

            String title = request.getParameter("title");
            System.out.printf("title===" + title);
            PageInfo<Movie> pageInfo = movieService.selectMovieAll(movieQuery);


            List<Movie> movieList = pageInfo.getList();

            ModelAndView productView = new ModelAndView();
            productView.addObject("results", pageInfo);
            productView.setViewName("wz/movieList");
            return productView;
        }catch (Exception e) {

        }
        return null;
    }

    /**
     * 根据id获取电影详情
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "getMovieById")
    public ModelAndView getMovieById(HttpServletRequest request, HttpServletResponse response){
        try{
            String id = request.getParameter("id");
            System.out.printf("id:"+id);

            /*Map map = new HashMap();
            map.put("id",1);
            map.put("title","[牺牲复活者][HD-1080P-MKV][韩语中字][1.84GB][2017]");
            map.put("img","http://i2.cfimg.com/509344/334fd24497fe1048.jpg");
            map.put("plot","电影改编自小说《完整的审判》，是一部奇幻惊悚电影。该片讲述的是全世界已经死去的人突然复活，去找杀害自己的人复仇的故事。其中就有一位母亲时隔七年死而复生，去找自己当检察官的儿子复仇，试图杀掉自己的儿子。...");
            map.put("douban","https://movie.douban.com/subject/26386034/");
            map.put("director","大卫");
            map.put("screenwriter","库尔特·约恩斯塔德");    //todo 多个
            map.put("mainActor","查理兹·塞隆 / 詹姆斯·麦卡沃伊");    //todo 主演
            map.put("updateTime",new Date());
            map.put("publishTime",new Date());

            List<Map<String,Object>> downloadList = new ArrayList<Map<String, Object>>();
            Map<String,Object> map1 = new HashMap<String, Object>();
            map1.put("downloadAddrx","ed2k://|file|罪恶黑名单.The.Blacklist.S05E06.中英字幕.HDTVrip.720P-人人影视.mp4|520391325|c944727eb739f386183b36fc37ad22ee|h=3qpntcbjxxejbokhn6u34cmc3p5vt3z5|/");
            map1.put("downloadName","《罪恶黑名单 [第五季]》 1280p BD高清版 第1集");
            Map<String,Object> map2 = new HashMap<String, Object>();
            map2.put("downloadAddrx","ed2k://|file|罪恶黑名单.The.Blacklist.S05E06.中英字幕.HDTVrip.720P-人人影视.mp4|520391325|c944727eb739f386183b36fc37ad22ee|h=3qpntcbjxxejbokhn6u34cmc3p5vt3z5|/");
            map2.put("downloadName","《罪恶黑名单 [第五季]》 1280p BD高清版 第2集");

            downloadList.add(map1);
            downloadList.add(map2);
            map.put("downloadList",downloadList);*/

            //电影基本信息
            Movie movie = movieService.getMovieById(Integer.valueOf(id));

            //下载地址
            List<MovieDownload> downloads = movieService.selectMovieDownLoadAll(Integer.valueOf(id));

            ModelAndView productView = new ModelAndView();
            productView.addObject("result", movie);
            productView.addObject("downloadList",downloads);

            productView.setViewName("wz/movieDetail");
            return productView;
        }catch (Exception e){

        }
        return null;
    }

}
