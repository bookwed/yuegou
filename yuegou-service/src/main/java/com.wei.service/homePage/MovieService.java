package com.wei.service.homePage;

import com.github.pagehelper.PageInfo;
import com.wei.model.homePage.Movie;
import com.wei.model.homePage.MovieDownload;
import com.wei.model.homePage.MovieQuery;

import java.util.List;

/**
 * Created by dong on 2017/11/7.
 */
public interface MovieService {

    String insert(Movie movie);

    /**
     * 电影，前3条
     * @return
     */
    List<Movie> selectMovieTop3();

    /**
     * 电影列表
     * @return
     */
    PageInfo<Movie> selectMovieAll(MovieQuery movieQuery);

    /**
     * 根据id获取电影对象
     * @return
     */
    Movie getMovieById(String id);

    /**
     * 根据电影id获取所有下载信息
     * @param movieId
     * @return
     */
    List<MovieDownload> selectMovieDownLoadAll(String movieId);

    /**
     * 根据addressCode获取下载信息
     * @param addressCode
     * @return
     */
    MovieDownload selectMovieDownloadByAddressCode(String addressCode);
}
