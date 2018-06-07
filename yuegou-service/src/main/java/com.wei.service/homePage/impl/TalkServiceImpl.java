package com.wei.service.homePage.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wei.dao.homePage.TalkDao;
import com.wei.model.homePage.Talk;
import com.wei.model.homePage.TalkQuery;
import com.wei.service.homePage.TalkService;
import com.wei.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * Created by dong on 2017/12/5.
 */
@Service
public class TalkServiceImpl implements TalkService{

    @Autowired
    private TalkDao talkDao;

    public String add(Talk talk) {
        talk.setId(CommonUtils.getUUID());
        talk.setCreateTime(new Date());
        talkDao.insert(talk);
        return talk.getId();
    }

    public PageInfo<Talk> selectTalkAll(TalkQuery talkQuery){
        PageHelper.startPage(talkQuery.getPageNum(),talkQuery.getPageSize());
        Example example = new Example(Talk.class);

        List<Talk> list = talkDao.selectByExample(example);
        PageInfo<Talk> pageInfo = new PageInfo<Talk>(list);
        return pageInfo;
    }

    public Talk getTalkById(String id) {
        Talk talk = talkDao.selectByPrimaryKey(id);
        return talk;
    }


}
