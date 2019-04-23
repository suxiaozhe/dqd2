package com.example.administrator.dqd.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.dqd.R;
import com.example.administrator.dqd.match.fragment.FollowFragment;
import com.example.administrator.dqd.match.fragment.Fragment10;
import com.example.administrator.dqd.match.fragment.Fragment11;
import com.example.administrator.dqd.match.fragment.Fragment3;
import com.example.administrator.dqd.match.fragment.Fragment4;
import com.example.administrator.dqd.match.fragment.Fragment5;
import com.example.administrator.dqd.match.fragment.Fragment6;
import com.example.administrator.dqd.match.fragment.Fragment7;
import com.example.administrator.dqd.match.fragment.Fragment8;
import com.example.administrator.dqd.match.fragment.Fragment9;
import com.example.administrator.dqd.match.fragment.ImportantFragment;
import com.example.administrator.dqd.match.fragment.LotteryFragment;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

public class MatchMatchFragment extends MatchFragment{

    ViewPager match_viewPager;
    TabPageIndicator tabPageIndicator;
    List<Fragment> fragmentList;
    ImportantFragment importantFragment;
    FollowFragment followFragment;
    LotteryFragment lotteryFragment;
    Fragment3 fragment3;
    Fragment4 fragment4;
    Fragment5 fragment5;
    Fragment6 fragment6;
    Fragment7 fragment7;
    Fragment8 fragment8;
    Fragment9 fragment9;
    Fragment10 fragment10;
    Fragment11 fragment11;
    String [] tabName ={"头条","热门","中超","懂球号","集锦","英超","西甲","意甲","德甲","五洲","深度","专题"};
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.match_match_fragment, container, false);

        match_viewPager = view.findViewById(R.id.match_viewPager);
        fragmentList = new ArrayList<Fragment>();
        followFragment = new FollowFragment();
        importantFragment = new ImportantFragment();
        lotteryFragment = new LotteryFragment();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();
        fragment5 = new Fragment5();
        fragment6 = new Fragment6();
        fragment7 = new Fragment7();
        fragment8 = new Fragment8();
        fragment9 = new Fragment9();
        fragment10 = new Fragment10();
        fragment11 = new Fragment11();
        fragmentList.add(importantFragment);
        fragmentList.add(followFragment);
        fragmentList.add(lotteryFragment);;
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);
        fragmentList.add(fragment5);
        fragmentList.add(fragment6);
        fragmentList.add(fragment7);
        fragmentList.add(fragment8);
        fragmentList.add(fragment9);
        fragmentList.add(fragment10);
        fragmentList.add(fragment11);

        FragmentManager fm = getChildFragmentManager();

        match_viewPager.setAdapter(new MyFragmentPagerAdapter(fm,fragmentList));
//        match_viewPager.setCurrentItem(0);

        TabPageIndicator tabPageIndicator = view.findViewById(R.id.match_tabPageIndicator);
        tabPageIndicator.setViewPager(match_viewPager);



        return view;
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList;
        private FragmentManager fragmentManager;

        public MyFragmentPagerAdapter(FragmentManager fm,List<Fragment> list) {
            super(fm);
            this.fragmentList = list;
            this.fragmentManager = fm;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabName[position];
        }
    }

}
