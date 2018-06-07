package com.wei.dao.homePage;

import com.wei.model.homePage.Talk;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface TalkDao extends Mapper<Talk> {
}