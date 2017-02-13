package com.qf.sxy.day32_xutils;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewUtilsFragment extends Fragment {


    public ViewUtilsFragment() {
        // Required empty public constructor
    }

    @ViewInject(R.id.fg_tv)
    private TextView tv;

    @ViewInject(R.id.lv)
    private ListView lv;

    private List<String> list = new ArrayList<>();

    private MyAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_view_utils, container, false);

        //在Fragment中注入注解工具
        ViewUtils.inject(this,view);

        tv.setText("AAAA");

        //获取数据源
        for (int i = 0; i < 20; i++) {
            list.add("===猴子请来的救兵===="+i);
        }

        adapter = new MyAdapter(list,getActivity());
        lv.setAdapter(adapter);

//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });

        return view;
    }

    @OnItemClick(R.id.lv)//note :参数和之前保持一致
    public void MyOnItemClick(AdapterView<?> parent, View view, int position, long id){
        Log.e("AAA","=AAA=>"+position);

    }

}
