package com.wei.dao.homePage;

import com.wei.model.homePage.Movie;
import com.wei.model.homePage.MovieQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by dong on 2017/11/7.
 */
@Repository
public interface MovieDao extends Mapper<Movie> {

    List<Movie> top3();


    List<Movie> selectAllByPage(@Param(value = "movieQuery") MovieQuery movieQuery);
}
