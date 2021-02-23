package com.study.manager.bean;

/**
 * 教师bean
 */
public class Teacher {

    public Integer id;
    public String tname;//姓名
    public String tsubject;//学科
    public String thonor;//荣誉
    public String tpic;//照片

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTsubject() {
        return tsubject;
    }

    public void setTsubject(String tsubject) {
        this.tsubject = tsubject;
    }

    public String getThonor() {
        return thonor;
    }

    public void setThonor(String thonor) {
        this.thonor = thonor;
    }

}
