package com.example.administrator.dqd.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.dqd.R;

public class A_tailFragment extends Fragment {

    TextView tv;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a_tail_fragment, container, false);

        tv = view.findViewById(R.id.a_tail_tv);
//        //定义底部标签图片大小和位置
//        Drawable drawable=getResources().getDrawable(R.drawable.lib_icon_empty_at);
//        //当这个图片被绘制时，给它绑定一个矩形
//        drawable.setBounds(0,0,120,120);
//        //设置图片在文字的哪个方向
//        tv.setCompoundDrawables(null,drawable,null,null);

        return view;
    }
}
