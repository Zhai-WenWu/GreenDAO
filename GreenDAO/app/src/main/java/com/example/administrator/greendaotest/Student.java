package com.example.administrator.greendaotest;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018/9/3.
 */

@Entity
public class Student {
    @Id
    private Long id;
    private String name;
    private int age;
    private String num;
    private String sex;
    private int i;
    @Generated(hash = 540429298)
    public Student(Long id, String name, int age, String num, String sex, int i) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.num = num;
        this.sex = sex;
        this.i = i;
    }
    @Generated(hash = 1556870573)
    public Student() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getNum() {
        return this.num;
    }
    public void setNum(String num) {
        this.num = num;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public int getI() {
        return this.i;
    }
    public void setI(int i) {
        this.i = i;
    }

}
