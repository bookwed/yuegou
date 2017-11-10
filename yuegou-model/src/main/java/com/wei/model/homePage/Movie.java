package com.wei.model.homePage;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "movie")
public class Movie implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 短标题
     */
    @Column(name = "short_name")
    private String shortName;

    /**
     * 完整标题
     */
    @Column(name = "full_name")
    private String fullName;

    /**
     * 导演
     */
    private String director;

    /**
     * 编剧
     */
    private String screenwriter;

    /**
     * 主演
     */
    @Column(name = "mainActor")
    private String mainactor;

    /**
     * 一句话介绍
     */
    private String oneword;

    /**
     * 图片地址
     */
    private String pic;

    /**
     * 发布时间
     */
    @Column(name = "publishTime")
    private Date publishtime;

    /**
     * 更新时间
     */
    @Column(name = "updateTime")
    private Date updatetime;

    /**
     * 豆瓣路径
     */
    private String douban;

    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private Date createtime;

    /**
     * 剧情介绍
     */
    private String plot;

    /**
     * 下载地址
     */
    @Column(name = "downloadAddress")
    private String downloadaddress;

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
     * 获取短标题
     *
     * @return short_name - 短标题
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * 设置短标题
     *
     * @param shortName 短标题
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * 获取完整标题
     *
     * @return full_name - 完整标题
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置完整标题
     *
     * @param fullName 完整标题
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 获取导演
     *
     * @return director - 导演
     */
    public String getDirector() {
        return director;
    }

    /**
     * 设置导演
     *
     * @param director 导演
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * 获取编剧
     *
     * @return screenwriter - 编剧
     */
    public String getScreenwriter() {
        return screenwriter;
    }

    /**
     * 设置编剧
     *
     * @param screenwriter 编剧
     */
    public void setScreenwriter(String screenwriter) {
        this.screenwriter = screenwriter;
    }

    /**
     * 获取主演
     *
     * @return mainActor - 主演
     */
    public String getMainactor() {
        return mainactor;
    }

    /**
     * 设置主演
     *
     * @param mainactor 主演
     */
    public void setMainactor(String mainactor) {
        this.mainactor = mainactor;
    }

    /**
     * 获取一句话介绍
     *
     * @return oneword - 一句话介绍
     */
    public String getOneword() {
        return oneword;
    }

    /**
     * 设置一句话介绍
     *
     * @param oneword 一句话介绍
     */
    public void setOneword(String oneword) {
        this.oneword = oneword;
    }

    /**
     * 获取图片地址
     *
     * @return pic - 图片地址
     */
    public String getPic() {
        return pic;
    }

    /**
     * 设置图片地址
     *
     * @param pic 图片地址
     */
    public void setPic(String pic) {
        this.pic = pic;
    }

    /**
     * 获取发布时间
     *
     * @return publishTime - 发布时间
     */
    public Date getPublishtime() {
        return publishtime;
    }

    /**
     * 设置发布时间
     *
     * @param publishtime 发布时间
     */
    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    /**
     * 获取更新时间
     *
     * @return updateTime - 更新时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置更新时间
     *
     * @param updatetime 更新时间
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 获取豆瓣路径
     *
     * @return douban - 豆瓣路径
     */
    public String getDouban() {
        return douban;
    }

    /**
     * 设置豆瓣路径
     *
     * @param douban 豆瓣路径
     */
    public void setDouban(String douban) {
        this.douban = douban;
    }

    /**
     * 获取创建时间
     *
     * @return createTime - 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间
     *
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取剧情介绍
     *
     * @return plot - 剧情介绍
     */
    public String getPlot() {
        return plot;
    }

    /**
     * 设置剧情介绍
     *
     * @param plot 剧情介绍
     */
    public void setPlot(String plot) {
        this.plot = plot;
    }

    /**
     * 获取下载地址
     *
     * @return downloadAddress - 下载地址
     */
    public String getDownloadaddress() {
        return downloadaddress;
    }

    /**
     * 设置下载地址
     *
     * @param downloadaddress 下载地址
     */
    public void setDownloadaddress(String downloadaddress) {
        this.downloadaddress = downloadaddress;
    }
}