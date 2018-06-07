package com.wei.model.homePage;

import java.util.Date;
import javax.persistence.*;

@Table(name = "contactMe")
public class Contactme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String title;

    @Column(name = "createTime")
    private Date createtime;

    /**
     * 是否回复
     */
    @Column(name = "isReply")
    private Integer isreply;

    private String content;

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
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return createTime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * @param createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取是否回复
     *
     * @return isReply - 是否回复
     */
    public Integer getIsreply() {
        return isreply;
    }

    /**
     * 设置是否回复
     *
     * @param isreply 是否回复
     */
    public void setIsreply(Integer isreply) {
        this.isreply = isreply;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }
}