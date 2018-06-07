package com.wei.model.homePage;


import com.wei.model.commons.Page;

/**
 * Created by dong on 2017/11/9.
 */
public class MovieQuery extends Page {
    private String title;
    private String type;
    private Integer isShow;
    private int startNum;

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

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public int getStartNum() {
        return startNum;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }
}
