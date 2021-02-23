package com.study.manager.bean;

/**
 * 学校bean
 */
public class School {

    public Integer id;//唯一标识
    public String sname;//学校名称
    public String saddress;//学校地址
    public String spic;//学校图片
    public Integer smoney;//学费

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSaddress() {
        return saddress;
    }

    public void setSaddress(String saddress) {
        this.saddress = saddress;
    }

    public String getSpic() {
        return spic;
    }

    public void setSpic(String spic) {
        this.spic = spic;
    }

    public Integer getSmoney() {
        return smoney;
    }

    public void setSmoney(Integer smoney) {
        this.smoney = smoney;
    }
}
