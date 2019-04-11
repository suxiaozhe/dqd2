package com.example.administrator.dqd.fragment;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.dqd.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private View view1,view2,view3,view11,view12,view13;
    private ViewPager viewPager;
    private List<View> viewList;
    private View tv_soccer,tv_world,tv_circle;
    //定义进圈精选视图
    private View circle_left,circle_right;
    //定义进圈精选下划线
    private View xian1,xian2;
    //定义头像
    private View head;
    //定义侧滑菜单布局，即main_menu
    private DrawerLayout drawerLayout;
    private View menu;
    private CircleFragment circleFragment;
    private SelectedFragment selectedFragment;
    private Fragment fragment;
    private android.support.v4.app.FragmentManager fragmentManager;


    public void onCreate(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        viewPager = view.findViewById(R.id.home_viewpager);
        LayoutInflater inflater1=getLayoutInflater();
        view1 = inflater1.inflate(R.layout.home_soccer_viewpager, null);
        view2 = inflater1.inflate(R.layout.home_world_viewpager,null);
        view3 = inflater1.inflate(R.layout.home_circle_viewpager, null);
        view11 = view.findViewById(R.id.xian1);
        view12 = view.findViewById(R.id.xian2);
        view13 = view.findViewById(R.id.xian3);

        tv_soccer=view.findViewById(R.id.titleTextLeft);
        tv_world=view.findViewById(R.id.titleText);
        tv_circle=view.findViewById(R.id.titleTextRight);

        head = view.findViewById(R.id.leftText);
        menu = getActivity().findViewById(R.id.main_menu);
        drawerLayout = getActivity().findViewById(R.id.drawer_layout_main);


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
                }else if (position==1) {
                    view11.setVisibility(view11.INVISIBLE);
                    view12.setVisibility(view12.VISIBLE);
                    view13.setVisibility(view13.INVISIBLE);
                }else if (position==2){

                    //获取进圈精选下划线视图
                    xian1 = (viewList.get(2)).findViewById(R.id.xian1);
                    xian2 = (viewList.get(2)).findViewById(R.id.xian2);



                    //实例化进圈和精选的视图
                    circle_left = (viewList.get(2)).findViewById(R.id.circle_left);
                    circle_right = (viewList.get(2)).findViewById(R.id.circle_right);

                    view11.setVisibility(view11.INVISIBLE);
                    view12.setVisibility(view12.INVISIBLE);
                    view13.setVisibility(view13.VISIBLE);
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


        return view;
    }

    public void initMenu() {
        //屏幕保持高亮，不被侧滑菜单遮盖
        //drawerLayout.setScrimColor(0x00ffffff);
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //滑动过程中不断回调 slideOffset:0~1,使得主界面一同滑动
//                View content = drawerLayout.getChildAt(0);
//                float scale = 1 - slideOffset;//1~0
//                content.setTranslationX(drawerView.getMeasuredWidth() * (1 - scale));//0~width
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

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

        if(xian1.getVisibility()==xian1.VISIBLE&&circleFragment==null){
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
                xian1.setVisibility(xian1.VISIBLE);
                xian2.setVisibility(xian2.INVISIBLE);
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
                xian1.setVisibility(xian1.INVISIBLE);
                xian2.setVisibility(xian2.VISIBLE);
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

}
