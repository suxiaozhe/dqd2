package com.example.administrator.dqd;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class LaunchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        View target = findViewById(R.id.iv_bg);//如果View显示无法找到，在前面加上android.view

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(target,"alpha",0.0f,1.0f);
        objectAnimator.setDuration(2000);
        objectAnimator.start();

//        //隐藏状态栏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //设置全屏

        Window window =LaunchActivity.this.getWindow();
        //设置透明状态栏，这样才能让ContentView向上
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //设置flag调用setStatusBarColor来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        //window.setStatusBarColor(R.color.green);

        ViewGroup mContentView = LaunchActivity.this.findViewById(Window.ID_ANDROID_CONTENT);

        View mChildView = mContentView.getChildAt(0);
        if(mChildView != null){

            //!!!是设置ContentView的第一个子view的FitsSystemWindow,使其不为系统View预留空间
            ViewCompat.setFitsSystemWindows(mChildView,false);
        }



        //动画执行完毕，执行新的页面,其中adapter为适配器模式的使用
        objectAnimator.addListener(new AnimatorListenerAdapter(){
            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(new Intent(LaunchActivity.this,LoginActivity.class));
                finish();
            }

        });

    }
}
