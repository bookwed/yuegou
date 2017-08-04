package com.wei.service.commons;

import com.wei.model.commons.CommonFile;

/**
 * Description ${DESC}
 * Author ed
 * Created 2017-08-03 17:31
 */
public interface CommonFileService {

    String findPath(String id);

    Integer insert(CommonFile commonFile) throws Exception;
}
