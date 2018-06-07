package com.wei.model.homePage;

import java.util.Date;
import javax.persistence.*;

@Table(name = "talk")
public class Talk {
    @Id
    private String id;

    private String title;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 99随便说说
     */
    private String type;

    private String content;

    public enum TALKENUM{
        OSCAR("99","随便说说");

        private String code;
        private String msg;

        private TALKENUM(String code,String msg){
            this.code = code;
            this.msg = msg;
        }

        public static String getName(String code){
            for (Talk.TALKENUM t : Talk.TALKENUM.values()){
                if(t.getCode().equals(code)){
                    return t.getMsg();
                }
            }
            return null;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
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

    /**
     * 获取99随便说说
     *
     * @return type - 99随便说说
     */
    public String getType() {
        return type;
    }

    /**
     * 设置99随便说说
     *
     * @param type 99随便说说
     */
    public void setType(String type) {
        this.type = type;
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