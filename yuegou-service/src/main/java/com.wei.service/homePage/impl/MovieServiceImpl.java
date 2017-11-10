package com.wei.service.homePage.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wei.dao.homePage.MovieDao;
import com.wei.dao.homePage.MovieDownloadDao;
import com.wei.model.homePage.Movie;
import com.wei.model.homePage.MovieDownload;
import com.wei.model.homePage.MovieQuery;
import com.wei.service.homePage.MovieService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by dong on 2017/11/7.
 */
@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private MovieDownloadDao movieDownloadDao;


    public List<Movie> selectMovieTop3() {
        /*PageHelper.startPage(2,5);  //第一个参数是当前页数，第二个参数是每页显示多少条数据
        Condition condition = new Condition(Movie.class);

        condition.setOrderByClause("createTime desc");

        List<Movie> list = movieDao.selectByExample(condition);*/

        /* 分页
        int offset = 100;
        int limit = 25;
        RowBounds rowBounds = new RowBounds(offset, limit);
        List<Movie> list2 = movieDao.selectByExampleAndRowBounds(condition,rowBounds);

        //查询单个对象
        VenueTp query = new VenueTp();
        query.setId(id);
        VenueTp bean = venueTpMapper.selectOne(query);
        */

        /*PageHelper.startPage(1,20);
        movieDao.select(null);*/

        /**
         *  PageHelper.startPage(query.getPage(), query.getPerPage());
            Example example = new Example(VenueTp.class);
         */

        List<Movie> list = movieDao.top3();
        return list;
    }
    //分页查询
    public PageInfo<Movie> selectMovieAll(MovieQuery movieQuery) {
        PageHelper.startPage(movieQuery.getPageNum(),movieQuery.getPageSize());
        Example example = new Example(Movie.class);
        //Example.Criteria criteria  = example.or();
        example.or().andLike("shortName","%"+movieQuery.getTitle()+"%");
        example.or().andLike("fullName","%"+movieQuery.getTitle()+"%");

        List<Movie> list = movieDao.selectByExample(example);

        PageInfo<Movie> pageInfo = new PageInfo<Movie>(list);
        return pageInfo;
    }

    public Movie getMovieById(Integer id) {
        Movie movie = movieDao.selectByPrimaryKey(id);
        return movie;
    }

    public List<MovieDownload> selectMovieDownLoadAll(Integer movieId) {
        Example example = new Example(MovieDownload.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("movieId",movieId);
        //criteria.andNotEqualTo("stage",0);    //不等于
        //criteria.andLike("title","zhangsan");   //模糊查找

        List<MovieDownload> list = movieDownloadDao.selectByExample(example);
        return list;
    }


}
