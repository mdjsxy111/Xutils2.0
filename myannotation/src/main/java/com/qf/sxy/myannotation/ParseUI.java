package com.qf.sxy.myannotation;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by sxy on 2016/11/1.
 */
public class ParseUI {

    /**
     * 处理Activity的UI
     * Activity的注解
     */
    public static void handlerUI(final Activity activity) {
        //获取Activity中所有的成员变量
        Field[] fields = activity.getClass().getDeclaredFields();

        //对所有成员变量进行遍历
        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            //成员变量能被访问
            f.setAccessible(true);

            //判断是否含有UI注解
            if (f.isAnnotationPresent(UI.class)) {
                UI ui = f.getAnnotation(UI.class);

                Log.e("AAA","=="+ui.id());
                Log.e("AAA","=="+ui.click());
                //找出带有Ui注解的控件
                View viewId = activity.findViewById(ui.id());

                try {
                    /**
                     * View和Activity的成员变量进行绑定
                     */
                    f.set(activity, viewId);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                try {
                    /**
                     *获取指定的方法
                     * 参数1:方法名称
                     * 参数2:方法类型
                     */
                   final Method method =  activity.getClass().getDeclaredMethod(ui.click(), View.class);
                    viewId.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(method!=null){
                                try {
                                    /**
                                     * 方法的调用
                                     * 参数1:方法作用到那个类
                                     * 参数2:方法的参数
                                     */
                                    method.invoke(activity,v);
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }

            }


        }


    }
}
