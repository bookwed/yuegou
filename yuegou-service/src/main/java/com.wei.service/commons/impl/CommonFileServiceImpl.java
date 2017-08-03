package com.wei.service.commons.impl;

;import com.wei.dao.commons.CommonFileMapper;
import com.wei.service.commons.CommonFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description
 * Author ed
 * Created 2017-08-03 17:32
 */
@Service
public class CommonFileServiceImpl implements CommonFileService{

    @Autowired
    private CommonFileMapper commonFileMapper;

    public String findPath(String id) {
        return commonFileMapper.selectPath(id);
    }
}
