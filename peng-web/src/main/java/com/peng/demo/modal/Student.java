package com.peng.demo.modal;

import java.util.Date;

/**
 * @Auther: daipeng
 * @Date: 2019/5/21 20:56
 * @Description:
 */
public class Student {

    private Integer stuId;

    private String stuName;

    private Integer stuAge;

    private Date stuDate;



    public Student(Integer stuId, String stuName, Integer stuAge, Date stuDate) {
        this.stuId = stuId;
        this.stuName = stuName;
        this.stuAge = stuAge;
        this.stuDate = stuDate;
    }

    public Student() {
    }

    public Date getStuDate() {
        return stuDate;
    }

    public void setStuDate(Date stuDate) {
        this.stuDate = stuDate;
    }

    public Integer getStuAge() {
        return stuAge;
    }

    public void setStuAge(Integer stuAge) {
        this.stuAge = stuAge;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }
}
