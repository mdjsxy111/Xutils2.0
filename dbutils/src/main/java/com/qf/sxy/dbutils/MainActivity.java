package com.qf.sxy.dbutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.DbModelSelector;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.db.table.DbModel;
import com.lidroid.xutils.exception.DbException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DbUtils.DbUpgradeListener{


    private DbUtils dbUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 创建数据库
         * 参数1:上下文
         * 参数2:路径
         * 参数3:数据库名称
         * 参数4:数据库版本\
         * 参数5:数据库版本发生改变的监听
         */
        dbUtils = DbUtils.create(MainActivity.this,getCacheDir().getAbsolutePath()
        ,"db_utils.db",1,this);
    }

    @Override
    public void onUpgrade(DbUtils db, int oldVersion, int newVersion) {
        Log.e("AAA","==oldVersion>"+oldVersion);
        Log.e("AAA","==newVersion>"+newVersion);
    }

    /**
     * 数据库版本升级
     * @param view
     */
    public void btnUpClick(View view) {

        dbUtils = DbUtils.create(MainActivity.this,getCacheDir().getAbsolutePath()
                ,"db_utils.db",2,this);
    }

    /**
     * 创建数据库表
     * @param view
     */
    public void createClick(View view) {
        try {
            dbUtils.createTableIfNotExist(Student.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加数据
     * @param view
     */
    public void insertClick(View view) {

        List<Student> list = new ArrayList<>();
        for(int i=0;i<100;i++){
            Student student = new Student();
            if(i%2==0){
                student.setName("毛哥"+i);
                student.setAge(i+19);
                student.setId(i);
                student.setSex("男");
            }
            if(i%2==1){
                student.setName("毛姐"+i);
                student.setAge(i+19);
                student.setId(i);
                student.setSex("女");
            }
            list.add(student);
//            try {
//                //向数据库里添加数据
//                dbUtils.save(student);
//            } catch (DbException e) {
//                e.printStackTrace();
//            }
        }

        try {
            /**
             * 必须所有数据全部符合要求  否则一条都不添加 (事务)
             */
            dbUtils.saveAll(list);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询数据
     * @param view
     */
    public void queryClick(View view) {

        try {

            /**
             *  .select("sex","name","age","_id")  可变字段项   查询的字段有哪些
             *  .where("_id",">=","10")); 参数1:字段项  参数2:条件   参数3:值
             */
            List<DbModel> list = dbUtils.findDbModelAll(DbModelSelector.from(Student.class)
                    .select("sex","name","age","_id")
                    .where("_id",">=","10"));
            for(int i=0;i<list.size();i++){
                Log.e("AA","=sex="+list.get(i).getString("sex"));
                Log.e("AA","=name="+list.get(i).getString("name"));
                Log.e("AA","=age="+list.get(i).getString("age"));
                Log.e("AA","=_id="+list.get(i).getString("_id"));
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除数据
     * @param view
     */
    public void deleteClick(View view) {

//        try {
//            //表示删除Student表中所有数据
//            dbUtils.deleteAll(Student.class);
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
        /////////////////////////////////
//        List<Student> list = new ArrayList<>();
//        try {
//            /**
//             * 表示 删除年龄大于50的
//             */
//            list = dbUtils.findAll(Selector.from(Student.class).where("age",">=","50"));
//            dbUtils.deleteAll(list);
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
//////////////////////////////////////
//        try {
//            /**
//             * 表示删除某一id的数据
//             */
//            dbUtils.deleteById(Student.class,11);
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
        /////////////////////////////
        try {
            /**]
             * 根据某一条件进行删除数据
             */
            dbUtils.delete(Student.class, WhereBuilder.b("sex","=","男").and("age",">","32"));
        } catch (DbException e) {
            e.printStackTrace();
        }

    }

    /**
     * 更改的方法
     * @param view
     */
    public void updateClick(View view) {
//        try {
//            Student student;
//            student = dbUtils.findById(Student.class,"13");
//            student.setSex("女");
//            student.setAge(88);
//            student.setName("芙蓉姐姐");
//            dbUtils.update(student,"name","age","sex");
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
        /////////////////////////////////
//        Student student = new Student();
//        student.setName("凤姐");
//        student.setAge(55);
//        student.setSex("男");
//        try {
//            dbUtils.update(student,WhereBuilder.b("_id","=","13"),"name","age","sex");
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
///////////////////////////////////////////
        List<Student> list = new ArrayList<>();
        try {
            list = dbUtils.findAll(Selector.from(Student.class).where("age",">=","30"));
            for(int i=0;i<list.size();i++){
                list.get(i).setName("毛哥");
            }
            dbUtils.updateAll(list,"name");
        } catch (DbException e) {
            e.printStackTrace();
        }

    }
}
