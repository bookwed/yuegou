package com.wei.model.homePage;

import com.wei.model.commons.Page;

/**
 * Created by dong on 2017/12/5.
 */
public class TalkQuery extends Page {
    private String title;
    private String type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
