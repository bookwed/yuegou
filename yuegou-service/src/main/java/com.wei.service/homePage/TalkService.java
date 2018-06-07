package com.wei.service.homePage;

import com.github.pagehelper.PageInfo;
import com.wei.model.homePage.Talk;
import com.wei.model.homePage.TalkQuery;

/**
 * Created by dong on 2017/12/5.
 */
public interface TalkService {

    public String add(Talk talk);

    PageInfo<Talk> selectTalkAll(TalkQuery talkQuery);

    Talk getTalkById(String id);
}
