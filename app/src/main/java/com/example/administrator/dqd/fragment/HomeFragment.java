package com.example.administrator.dqd.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.dqd.R;
import com.example.administrator.dqd.pager.BasePager;
import com.example.administrator.dqd.pager.NoScollViewPager;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private View view11;
    private View view12;
    private View view13;
    private ViewPager viewPager;
    private NoScollViewPager soccer_viewPager;
    private List<View> viewList;
    //定义进圈精选视图
    private View circle_left,circle_right;
    //定义进圈精选下划线
    private View xian1,xian2;
    //定义侧滑菜单布局，即main_menu
    private DrawerLayout drawerLayout;
    private View menu;
    private CircleFragment circleFragment;
    private SelectedFragment selectedFragment;
    private Fragment fragment;
    private android.support.v4.app.FragmentManager fragmentManager;
    private ListView listView;
    List<BasePager> listPager;
    private TabPageIndicator tabPageIndicator;
    String [] tabName ={"头条","热门","中超","懂球号","集锦","英超","西甲","意甲","德甲","五洲","深度","专题"};

    public void onCreate(){

    }

    @SuppressLint("InflateParams")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        viewPager = view.findViewById(R.id.home_viewpager);

        //indicator = viewPager.findViewById(R.id.soccer_indicator);
        LayoutInflater inflater1 = getLayoutInflater();
        View view1 = inflater1.inflate(R.layout.home_soccer_viewpager, null);
        View view2 = inflater1.inflate(R.layout.home_world_viewpager, null);
        View view3 = inflater1.inflate(R.layout.home_circle_viewpager, null);
        view11 = view.findViewById(R.id.xian1);
        view12 = view.findViewById(R.id.xian2);
        view13 = view.findViewById(R.id.xian3);

        soccer_viewPager = view1.findViewById(R.id.soccer_viewpager);

        View tv_soccer = view.findViewById(R.id.titleTextLeft);
        View tv_world = view.findViewById(R.id.titleText);
        View tv_circle = view.findViewById(R.id.titleTextRight);

        //定义头像
        View head = view.findViewById(R.id.leftText);
        menu = Objects.requireNonNull(getActivity()).findViewById(R.id.main_menu);
        drawerLayout = getActivity().findViewById(R.id.drawer_layout_main);




        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

        PagerAdapter pagerAdapter = new PagerAdapter() {

            @Override
            public boolean isViewFromObject(@NonNull View arg0, @NonNull Object arg1) {
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
                                    @NonNull Object object) {
                // TODO Auto-generated method stub
                container.removeView(viewList.get(position));
            }

            @NonNull
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                container.addView(viewList.get(position));


                return viewList.get(position);
            }
        };


        viewPager.setAdapter(pagerAdapter);

//        //找到soccer下的listview
//        listView = view1.findViewById(R.id.soccer_listView);
//        listView.setAdapter(new MyListAdapter());


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if(position==0)
                {
                    view11.setVisibility(View.VISIBLE);
                    view12.setVisibility(View.INVISIBLE);
                    view13.setVisibility(View.INVISIBLE);


                }else if (position==1) {
                    view11.setVisibility(View.INVISIBLE);
                    view12.setVisibility(View.VISIBLE);
                    view13.setVisibility(View.INVISIBLE);
                }else if (position==2){

                    //获取进圈精选下划线视图
                    xian1 = (viewList.get(2)).findViewById(R.id.xian1);
                    xian2 = (viewList.get(2)).findViewById(R.id.xian2);



                    //实例化进圈和精选的视图
                    circle_left = (viewList.get(2)).findViewById(R.id.circle_left);
                    circle_right = (viewList.get(2)).findViewById(R.id.circle_right);

                    view11.setVisibility(View.INVISIBLE);
                    view12.setVisibility(View.INVISIBLE);
                    view13.setVisibility(View.VISIBLE);
                    //对进圈和精选的视图进行监听
                    initListen();
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //头像点击事件
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(menu);
            }
        });

        tv_soccer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });
        tv_world.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });
        tv_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
            }
        });



        initMenu();



        listPager = new ArrayList<BasePager>();
        for(int i=0;i<tabName.length;i++){
            BasePager basePager = new BasePager(getActivity(),tabName[i],i);
            listPager.add(basePager);
        }

        //为soccer_viewPager设置适配器
        soccer_viewPager.setAdapter(new MyViewPagerAdapter());
        TabPageIndicator tabPageIndicator = view1.findViewById(R.id.tabPageIndicator);
        //TabPageIndicator和ViewPager关联
        tabPageIndicator.setViewPager(soccer_viewPager);
        //以后监听页面的变化用TabPageIndicator



        return view;
    }

    public void initMenu() {
        //屏幕保持高亮，不被侧滑菜单遮盖
        //drawerLayout.setScrimColor(0x00ffffff);
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                //滑动过程中不断回调 slideOffset:0~1,使得主界面一同滑动
//                View content = drawerLayout.getChildAt(0);
//                float scale = 1 - slideOffset;//1~0
//                content.setTranslationX(drawerView.getMeasuredWidth() * (1 - scale));//0~width
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    ///监听方法
    private void initListen() {

        //默认选择进圈视图
        fragmentManager = getChildFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        if(xian1.getVisibility()== View.VISIBLE &&circleFragment==null){
            circleFragment = new CircleFragment();
            ft.add(R.id.fl_circle,circleFragment).commit();
        }//在此处设置默认，要考虑到viewpager切换回来的情况


        circle_left.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                FragmentTransaction ft = fragmentManager.beginTransaction();
                hideFragments(ft);
                ft.show(circleFragment).commit();
                //设置下划线显示隐藏
                xian1.setVisibility(View.VISIBLE);
                xian2.setVisibility(View.INVISIBLE);
            }
        });

        circle_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = fragmentManager.beginTransaction();
                hideFragments(ft);

                if(selectedFragment!=null){
                    ft.show(selectedFragment).commit();
                }else{
                    selectedFragment = new SelectedFragment();
                    ft.add(R.id.fl_circle,selectedFragment).commit();
                }
                //设置下划线显示隐藏
                xian1.setVisibility(View.INVISIBLE);
                xian2.setVisibility(View.VISIBLE);
            }
        });



    }

    public void hideFragments(FragmentTransaction ft){
        if(circleFragment!=null){
            ft.hide(circleFragment);
        }
        if(selectedFragment!=null){
            ft.hide(selectedFragment);
        }
    }


//    private class MyListAdapter extends BaseAdapter{
//
//        //一共有多少条数据需要显示
//        @Override
//        public int getCount() {
//            return 300;
//        }
//
//        //返回指定position位置对应的对象
//        @Override
//        public Object getItem(int position) {
//            return null;
//        }
//        //返回position对应的ID
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//        //获取一个view，原来显示ListView的数据，作为一个条目出现
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
////            TextView tv = new TextView(getActivity());
////            tv.setText("哈哈哈"+position);
////            return tv;
//
//
//            //进行优化，复用创建的视图，仅需传入数据即可
//            TextView tv;
//            if(convertView == null){
//                tv = new TextView(getActivity());
//            }else {
//                tv = (TextView) convertView;
//            }
//
//            tv.setText("哈哈哈"+position);
//            return tv;
//        }
//    }


    class MyViewPagerAdapter extends PagerAdapter{


        @Override
        public int getCount() {
            if(listPager==null){
                return 0;
            }else{
                return listPager.size();
            }
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            BasePager basePager = listPager.get(position);
            View rootView = basePager.rootView;
            //初始化数据
            basePager.initData();
            container.addView(rootView);
            return rootView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
           container.removeView((View) object);
        }


        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }


        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabName[position];
        }
    }

}


