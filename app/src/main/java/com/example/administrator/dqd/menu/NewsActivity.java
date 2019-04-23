package com.example.administrator.dqd.menu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.dqd.R;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends Activity {

    private ImageView title_back;
    private LinearLayout linearLayout;
    private float  mPosX, mPosY,mCurPosX,mCurPosY;
    ViewPager viewPager;
    TextView community_news,shop_news;
    List<View> viewList;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_news);

        status();

        initView();

        initData();

        initAdapter();

        initListen();

    }

    private void initAdapter() {

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

    }

    private void initListen() {
        //此方法可以监听滑动，实现右滑退出，但是无法检测速度来判定是否启动退出
        setGestureListener();

        //设置页面滑动监听
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        community_news.setTextColor(NewsActivity.this.getResources().getColor(R.color.green));
                        shop_news.setTextColor(NewsActivity.this.getResources().getColor(R.color.black));
                        break;
                    case 1:
                        community_news.setTextColor(NewsActivity.this.getResources().getColor(R.color.black));
                        shop_news.setTextColor(NewsActivity.this.getResources().getColor(R.color.green));

                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {

    }

    private void initView() {

        title_back = findViewById(R.id.title_back);
        viewPager = findViewById(R.id.viewPager_news);
        community_news = findViewById(R.id.community_news);
        shop_news = findViewById(R.id.shop_news);

        View view0,view1;
        view0 = View.inflate(NewsActivity.this,R.layout.community_news_fragment,null);
        view1 = View.inflate(NewsActivity.this,R.layout.shop_news_fragment,null);
        viewList = new ArrayList<View>();
        viewList.add(view0);
        viewList.add(view1);

    }

    private void status() {
        Window window =NewsActivity.this.getWindow();
        //设置透明状态栏，这样才能让ContentView向上
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //设置flag调用setStatusBarColor来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        //window.setStatusBarColor(R.color.green);

        ViewGroup mContentView = NewsActivity.this.findViewById(Window.ID_ANDROID_CONTENT);

        View mChildView = mContentView.getChildAt(0);
        if(mChildView != null){

            //!!!是设置ContentView的第一个子view的FitsSystemWindow,使其不为系统View预留空间
            ViewCompat.setFitsSystemWindows(mChildView,false);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setGestureListener(){

        linearLayout = findViewById(R.id.news_linearLayout);

        linearLayout.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        mPosX = event.getX();
                        mPosY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mCurPosX = event.getX();
                        mCurPosY = event.getY();

                        break;
                    case MotionEvent.ACTION_UP:
                        if (mCurPosX - mPosX > 0
                                && (Math.abs(mCurPosX - mPosX) > 200)) {
                            //向右滑动

                            if((Math.abs(mCurPosY - mPosY) < 200)){
                                finish();
                            }
                        } else if (mCurPosX - mPosX < 0
                                && (Math.abs(mCurPosX - mPosX) > 25)) {
                            //向左滑动
                        }

                        break;
                }
                return true;
            }

        });
    }

    public void click0(View view) {

        viewPager.setCurrentItem(0);
        community_news.setTextColor(NewsActivity.this.getResources().getColor(R.color.green));
        shop_news.setTextColor(NewsActivity.this.getResources().getColor(R.color.black));

    }

    public void click1(View view) {

        viewPager.setCurrentItem(1);
        community_news.setTextColor(NewsActivity.this.getResources().getColor(R.color.black));
        shop_news.setTextColor(NewsActivity.this.getResources().getColor(R.color.green));

    }

    public void click_shop_news(View view) {
        Toast.makeText(NewsActivity.this,"商城功能正在披星戴月赶制中，敬请期待",Toast.LENGTH_SHORT).show();
    }
}
