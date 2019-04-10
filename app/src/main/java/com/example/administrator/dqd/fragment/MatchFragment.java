package com.example.administrator.dqd.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.dqd.R;

public class MatchFragment extends Fragment {

    private RadioGroup rg_match;
    private MatchMatchFragment matchMatchFragment;
    private MatchScoreFragment matchScoreFragment;
    private RadioButton rb_match_match;
    private RadioButton rb_match_score;
    //定义头像
    private View head;
    //定义侧滑菜单布局，即main_menu
    private View menu;
    private DrawerLayout drawerLayout;
    private ImageView screen;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.match_fragment, container, false);

        rg_match= view.findViewById(R.id.rg_match);
        rb_match_match=view.findViewById(R.id.rb_match_match);
        rb_match_score=view.findViewById(R.id.rb_match_score);

        head = view.findViewById(R.id.leftText);
        menu = getActivity().findViewById(R.id.main_menu);
        drawerLayout = getActivity().findViewById(R.id.drawer_layout_main);
        screen = view.findViewById(R.id.right_image);

        initView();

        initData();

        initListen();

        //头像点击事件
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(menu);
            }
        });

        //筛选漏斗点击事件
        screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"点击了筛选漏斗",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void initView() {

    }

    private void initData() {
        matchMatchFragment=new MatchMatchFragment();
        matchScoreFragment=new MatchScoreFragment();
    }

    private void initListen() {
        //RadioGroup的选择事件
        rg_match.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Fragment fragment = null;
                switch(checkedId){
                    case R.id.rb_match_match:
                        fragment = matchMatchFragment;
                        screen.setVisibility(screen.INVISIBLE);
                        break;
                    case R.id.rb_match_score:
                        fragment = matchScoreFragment;
                        screen.setVisibility(screen.VISIBLE);
                        break;
                }
                //实现Fragment切换方法
               switchFragment(fragment);
            }

            private void switchFragment(Fragment fragment) {
                FragmentManager fragmentManager = getChildFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fl_match,fragment).commit();
            }

        });
        //默认选择首页页面
        rg_match.check(R.id.rb_match_match);
    }

}
