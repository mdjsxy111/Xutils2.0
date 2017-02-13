package com.qf.sxy.dbutils;

import com.lidroid.xutils.db.annotation.Check;
import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.NotNull;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by sxy on 2016/11/1.
 */
//创建数据库表的名称
//note:必须是字母开始
@Table(name = "student_1618")
public class Student {

    @Column(column = "_id")//将Student类中的id  映射到数据库表中的_id
    @Id(column = "_id") //@Id将Id映射成数据库表中主键
    @NotNull//设置不能为空
    private int id;

    @Column(column = "name")
    @NotNull
//    @Unique
    private String name;


    @Column(column = "age")
    @Check("age>18")//约束条件   满足该条件  才能将这个数据添加到数据库表中
    private int age;


    @Column(column = "sex")
    @Check("sex in('男','女')")
    private String sex;


    public Student() {
    }

    public Student(int id, String name, int age, String sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
