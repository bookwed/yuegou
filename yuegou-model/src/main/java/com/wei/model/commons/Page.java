package com.wei.model.commons;

/**
 * Created by dong on 2017/11/9.
 */
public class Page {
    private int pageNum = 1;
    private int pageSize = 2;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
