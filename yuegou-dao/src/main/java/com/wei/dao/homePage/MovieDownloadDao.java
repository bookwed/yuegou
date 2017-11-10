package com.wei.dao.homePage;

import com.wei.model.homePage.MovieDownload;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface MovieDownloadDao extends Mapper<MovieDownload> {
}