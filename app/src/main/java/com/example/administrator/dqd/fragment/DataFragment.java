package com.example.administrator.dqd.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.dqd.R;

public class DataFragment extends Fragment {

    //定义头像
    private View head;
    //定义侧滑菜单布局，即main_menu
    private View menu;
    private DrawerLayout drawerLayout;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.data_fragment, container, false);

        head = view.findViewById(R.id.leftText);
        menu = getActivity().findViewById(R.id.main_menu);
        drawerLayout = getActivity().findViewById(R.id.drawer_layout_main);

        //头像点击事件
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(menu);
            }
        });

        return view;
    }
}
