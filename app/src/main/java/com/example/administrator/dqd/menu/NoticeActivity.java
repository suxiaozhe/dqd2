package com.example.administrator.dqd.menu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import com.example.administrator.dqd.R;

import java.util.ArrayList;
import java.util.List;

public class NoticeActivity extends Activity {

    private ImageView title_back;
    private LinearLayout linearLayout;
    private float  mPosX, mPosY,mCurPosX,mCurPosY;
    private ViewPager viewPager;
    List<View> viewList;
    TextView tv_a_tail,tv_agree,tv_reply;
    View line0,line1,line2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_notice);

        initView();

        initData();

        initAdapter();

        initListen();

        //状态栏
        status();


    }

    private void initListen() {
        //此方法可以监听滑动，实现右滑退出，但是无法检测速度来判定是否启动退出
        setGestureListener();



        //监听页面滑动
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        tv_a_tail.setTextColor(NoticeActivity.this.getResources().getColor(R.color.green));
                        tv_agree.setTextColor(NoticeActivity.this.getResources().getColor(R.color.black));
                        tv_reply.setTextColor(NoticeActivity.this.getResources().getColor(R.color.black));
                        line0.setVisibility(View.VISIBLE);
                        line1.setVisibility(View.INVISIBLE);
                        line2.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        tv_a_tail.setTextColor(NoticeActivity.this.getResources().getColor(R.color.black));
                        tv_agree.setTextColor(NoticeActivity.this.getResources().getColor(R.color.green));
                        tv_reply.setTextColor(NoticeActivity.this.getResources().getColor(R.color.black));
                        line0.setVisibility(View.INVISIBLE);
                        line1.setVisibility(View.VISIBLE);
                        line2.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        tv_a_tail.setTextColor(NoticeActivity.this.getResources().getColor(R.color.black));
                        tv_agree.setTextColor(NoticeActivity.this.getResources().getColor(R.color.black));
                        tv_reply.setTextColor(NoticeActivity.this.getResources().getColor(R.color.green));
                        line0.setVisibility(View.INVISIBLE);
                        line1.setVisibility(View.INVISIBLE);
                        line2.setVisibility(View.VISIBLE);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        //返回键的点击监听
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initView() {

        title_back = findViewById(R.id.title_back);
        viewPager = findViewById(R.id.viewPager_notice);

        tv_a_tail = findViewById(R.id.a_tail);
        tv_agree = findViewById(R.id.agree);
        tv_reply = findViewById(R.id.reply);

        line0 = findViewById(R.id.line0);
        line1 = findViewById(R.id.line1);
        line2 = findViewById(R.id.line2);

        View view1,view2,view3;
        view1 = View.inflate(NoticeActivity.this,R.layout.a_tail_fragment,null);
        view2 = View.inflate(NoticeActivity.this,R.layout.agree_fragment,null);
        view3 = View.inflate(NoticeActivity.this,R.layout.reply_fragment,null);
        viewList = new ArrayList<View>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

    }

    private void initData() {

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



    private void status() {
        Window window =NoticeActivity.this.getWindow();
        //设置透明状态栏，这样才能让ContentView向上
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //设置flag调用setStatusBarColor来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        //window.setStatusBarColor(R.color.green);

        ViewGroup mContentView = NoticeActivity.this.findViewById(Window.ID_ANDROID_CONTENT);

        View mChildView = mContentView.getChildAt(0);
        if(mChildView != null){

            //!!!是设置ContentView的第一个子view的FitsSystemWindow,使其不为系统View预留空间
            ViewCompat.setFitsSystemWindows(mChildView,false);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setGestureListener(){

        linearLayout = findViewById(R.id.notice_linearLayout);

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

    //tab的点击事件
     public void click0(View view) {
            viewPager.setCurrentItem(0);
            tv_a_tail.setTextColor(NoticeActivity.this.getResources().getColor(R.color.green));
            tv_agree.setTextColor(NoticeActivity.this.getResources().getColor(R.color.black));
            tv_reply.setTextColor(NoticeActivity.this.getResources().getColor(R.color.black));

            line0.setVisibility(View.VISIBLE);
            line1.setVisibility(View.INVISIBLE);
            line2.setVisibility(View.INVISIBLE);

        }
    public void click1(View view) {

        viewPager.setCurrentItem(1);
        tv_a_tail.setTextColor(NoticeActivity.this.getResources().getColor(R.color.black));
        tv_agree.setTextColor(NoticeActivity.this.getResources().getColor(R.color.green));
        tv_reply.setTextColor(NoticeActivity.this.getResources().getColor(R.color.black));

        line0.setVisibility(View.INVISIBLE);
        line1.setVisibility(View.VISIBLE);
        line2.setVisibility(View.INVISIBLE);

    }

    public void click2(View view) {
        viewPager.setCurrentItem(2);
        tv_a_tail.setTextColor(NoticeActivity.this.getResources().getColor(R.color.black));
        tv_agree.setTextColor(NoticeActivity.this.getResources().getColor(R.color.black));
        tv_reply.setTextColor(NoticeActivity.this.getResources().getColor(R.color.green));

        line0.setVisibility(View.INVISIBLE);
        line1.setVisibility(View.INVISIBLE);
        line2.setVisibility(View.VISIBLE);

    }


}
