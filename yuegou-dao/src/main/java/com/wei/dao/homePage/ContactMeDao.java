package com.wei.dao.homePage;

import com.wei.model.homePage.Contactme;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ContactMeDao extends Mapper<Contactme> {
}