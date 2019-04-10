package com.example.administrator.dqd.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.dqd.R;

import java.util.ArrayList;
import java.util.List;

public class LotteryFragment extends Fragment {

    private View view1,view2,view3,view11,view12,view13;//1，2，3为三个pager，11,12,13为下划线
    private View tv_lottery,tv_score,tv_soccer;
    private ViewPager viewPager;
    private List<View> viewList;
    //定义头像
    private View head;
    //定义侧滑菜单布局，即main_menu
    private View menu;
    private DrawerLayout drawerLayout;
    private ImageView screen;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lottery_fragment, container, false);

        viewPager = view.findViewById(R.id.lottery_viewpager);
        LayoutInflater inflater1=getLayoutInflater();
        view1 = inflater1.inflate(R.layout.lottery_lottery_viewpager, null);
        view2 = inflater1.inflate(R.layout.lottery_score_viewpager,null);
        view3 = inflater1.inflate(R.layout.lottery_soccer_viewpager, null);
        view11 = view.findViewById(R.id.xian1);
        view12 = view.findViewById(R.id.xian2);
        view13 = view.findViewById(R.id.xian3);
        tv_lottery = view.findViewById(R.id.titleTextLeft);
        tv_score = view.findViewById(R.id.titleText);
        tv_soccer = view.findViewById(R.id.titleTextRight);
        screen = view.findViewById(R.id.right_image);

        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);


        PagerAdapter pagerAdapter = new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return viewList.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                container.addView(viewList.get(position));


                return viewList.get(position);
            }
        };


        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                if(position==0)
                {
                    view11.setVisibility(view11.VISIBLE);
                    view12.setVisibility(view12.INVISIBLE);
                    view13.setVisibility(view13.INVISIBLE);
                    screen.setVisibility(screen.INVISIBLE);
                }else if (position==1) {
                    view11.setVisibility(view11.INVISIBLE);
                    view12.setVisibility(view12.VISIBLE);
                    view13.setVisibility(view13.INVISIBLE);
                    screen.setVisibility(screen.VISIBLE);
                }else if (position==2){
                    view11.setVisibility(view11.INVISIBLE);
                    view12.setVisibility(view12.INVISIBLE);
                    view13.setVisibility(view13.VISIBLE);
                    screen.setVisibility(screen.INVISIBLE);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        //设置懂彩的点击监听事件
        tv_lottery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });
        //设置比分的点击监听事件
        tv_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });
        //设置聊球的点击监听事件
        tv_soccer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
            }
        });

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

        //筛选漏斗点击事件
        screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"点击了筛选漏斗",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
