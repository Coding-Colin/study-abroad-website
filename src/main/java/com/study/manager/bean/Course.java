package com.study.manager.bean;

import java.util.Date;

/**
 * 课程bean
 */
public class Course {

    public Integer id;
    public String cname;//课程名称
    public String cintroduce;//课程简介
    public Date cdate;//开课时间
    public Integer cprice;//价格
    public String cpic;//图片
    public Integer tid;//老师id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCintroduce() {
        return cintroduce;
    }

    public void setCintroduce(String cintroduce) {
        this.cintroduce = cintroduce;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public Integer getCprice() {
        return cprice;
    }

    public void setCprice(Integer cprice) {
        this.cprice = cprice;
    }

    public String getCpic() {
        return cpic;
    }

    public void setCpic(String cpic) {
        this.cpic = cpic;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }
}
