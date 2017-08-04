package com.wei.service.commons.impl;

;import com.wei.dao.commons.CommonFileDao;
import com.wei.model.commons.CommonFile;
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
    private CommonFileDao commonFileDao;

    public String findPath(String id) {
        return commonFileDao.selectPath(id);
    }

    public Integer insert(CommonFile commonFile) throws Exception{
        commonFileDao.insert(commonFile);
        return commonFile.getId();
    }
}
