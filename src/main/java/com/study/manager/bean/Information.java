package com.study.manager.bean;

import java.util.Date;
import java.util.List;

/**
 * 资讯bean
 */
public class Information {

    public Integer id;
    public String title;//题目
    public String content;//内容
    public Date idate;//发布日期

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getIdate() {
        return idate;
    }

    public void setIdate(Date idate) {
        this.idate = idate;
    }
}
