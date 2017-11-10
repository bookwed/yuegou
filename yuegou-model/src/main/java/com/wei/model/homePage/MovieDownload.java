package com.wei.model.homePage;

import java.util.Date;
import javax.persistence.*;

@Table(name = "movie_download")
public class MovieDownload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 电影id
     */
    @Column(name = "movie_id")
    private Integer movieId;

    /**
     * 排序
     */
    @Column(name = "order_no")
    private Integer orderNo;

    /**
     * 下载标题
     */
    @Column(name = "download_name")
    private String downloadName;

    /**
     * 下载地址
     */
    @Column(name = "download_address")
    private String downloadAddress;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取电影id
     *
     * @return movie_id - 电影id
     */
    public Integer getMovieId() {
        return movieId;
    }

    /**
     * 设置电影id
     *
     * @param movieId 电影id
     */
    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    /**
     * 获取排序
     *
     * @return order_no - 排序
     */
    public Integer getOrderNo() {
        return orderNo;
    }

    /**
     * 设置排序
     *
     * @param orderNo 排序
     */
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取下载标题
     *
     * @return download_name - 下载标题
     */
    public String getDownloadName() {
        return downloadName;
    }

    /**
     * 设置下载标题
     *
     * @param downloadName 下载标题
     */
    public void setDownloadName(String downloadName) {
        this.downloadName = downloadName;
    }

    /**
     * 获取下载地址
     *
     * @return download_address - 下载地址
     */
    public String getDownloadAddress() {
        return downloadAddress;
    }

    /**
     * 设置下载地址
     *
     * @param downloadAddress 下载地址
     */
    public void setDownloadAddress(String downloadAddress) {
        this.downloadAddress = downloadAddress;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}