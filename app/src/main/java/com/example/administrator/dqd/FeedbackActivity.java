package com.example.administrator.dqd;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.example.administrator.dqd.fragment.OpinionFragment;
import com.example.administrator.dqd.fragment.RecordFragment;


public class FeedbackActivity extends FragmentActivity {

    private ImageView title_back;
    private LinearLayout linearLayout;
    private float  mPosX, mPosY,mCurPosX,mCurPosY;
    private OpinionFragment opinionFragment;
    private RecordFragment recordFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction ft;
    private View xian1,xian2,feedback_left,feedback_right;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feedback);

        xian1 = findViewById(R.id.xian1);
        xian2 = findViewById(R.id.xian2);
        feedback_left = findViewById(R.id.feedback_left);
        feedback_right = findViewById(R.id.feedback_right);
        fragmentManager = getSupportFragmentManager();

        title_back = findViewById(R.id.title_back);
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Window window =FeedbackActivity.this.getWindow();
        //设置透明状态栏，这样才能让ContentView向上
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //设置flag调用setStatusBarColor来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        //window.setStatusBarColor(R.color.green);

        ViewGroup mContentView = FeedbackActivity.this.findViewById(Window.ID_ANDROID_CONTENT);

        View mChildView = mContentView.getChildAt(0);
        if(mChildView != null){

            //!!!是设置ContentView的第一个子view的FitsSystemWindow,使其不为系统View预留空间
            ViewCompat.setFitsSystemWindows(mChildView,false);
        }
        //此方法可以监听滑动，实现右滑退出，但是无法检测速度来判定是否启动退出
        setGestureListener();

        initListen();

    }

    private void setGestureListener(){

        linearLayout = findViewById(R.id.feedback_linearLayout);

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

    ///监听方法
    private void initListen() {

        //默认选择

        FragmentTransaction ft = fragmentManager.beginTransaction();

        if(xian1.getVisibility()==xian1.VISIBLE&&opinionFragment==null){
            opinionFragment = new OpinionFragment();
            ft.add(R.id.fl_feedback,opinionFragment).commit();
        }//在此处设置默认，要考虑到viewpager切换回来的情况


        feedback_left.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                FragmentTransaction ft = fragmentManager.beginTransaction();
                hideFragments(ft);
                ft.show(opinionFragment).commit();
                //设置下划线显示隐藏
                xian1.setVisibility(xian1.VISIBLE);
                xian2.setVisibility(xian2.INVISIBLE);
            }
        });

        feedback_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = fragmentManager.beginTransaction();
                hideFragments(ft);

                if(recordFragment!=null){
                    ft.show(recordFragment).commit();
                }else{
                    recordFragment = new RecordFragment();
                    ft.add(R.id.fl_feedback,recordFragment).commit();
                }
                //设置下划线显示隐藏
                xian1.setVisibility(xian1.INVISIBLE);
                xian2.setVisibility(xian2.VISIBLE);
            }
        });



    }

    public void hideFragments(FragmentTransaction ft){
        if(opinionFragment!=null){
            ft.hide(opinionFragment);
        }
        if(recordFragment!=null){
            ft.hide(recordFragment);
        }
    }



}
