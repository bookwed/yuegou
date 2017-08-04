package com.wei.dao.commons;

import com.wei.model.commons.CommonFile;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * Description ${DESC}
 * Author ed
 * Created 2017-08-03 17:25
 */
public interface CommonFileMapper extends Mapper<CommonFile> {
    @Select("select file_path from common_file where id = #{id}")
    String selectPath(String id);
}
