package com.qf.sxy.day32_xutils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by sxy on 2016/11/1.
 */
public class MyAdapter extends BaseAdapter {

    private List<String> list ;
    private Context context;
    public MyAdapter(List<String> list, Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.layout,parent,false);
            viewHolder = new ViewHolder();
            //adapter中注入注解工具
            ViewUtils.inject(viewHolder,convertView);
//            viewHolder.tv = (TextView) convertView.findViewById(R.id.item_tv);
            convertView.setTag(viewHolder);
        }else{
           viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv.setText(list.get(position));

        return convertView;
    }


    class  ViewHolder{
        @ViewInject(R.id.item_tv)
        TextView tv;
    }

}
