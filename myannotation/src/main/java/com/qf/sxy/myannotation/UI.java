package com.qf.sxy.myannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by sxy on 2016/11/1.
 *
 * 自定义一个注解工具
 * 创建一个类 改成@interface形式(注解形式)
 *
 */
//生命周期  注解的保存范围  运行时编译时
@Retention(RetentionPolicy.RUNTIME)
//设置注解的使用范围
@Target(value = ElementType.FIELD)
public @interface UI {
    //用于接收控件的id
    int id();
    //用于接收控件的点击事件
    String click();
}
