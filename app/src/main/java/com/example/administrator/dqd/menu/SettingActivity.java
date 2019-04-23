package com.example.administrator.dqd.menu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.administrator.dqd.R;
import com.example.administrator.dqd.menu.setting.AboutActivity;

public class SettingActivity extends Activity {

    private ImageView title_back;
    private LinearLayout linearLayout;
    private float  mPosX, mPosY,mCurPosX,mCurPosY;
    private RadioButton rb_textsize_big,rb_textsize_middle,rb_textsize_small;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);

        stutas();

        initView();

        initData();

        initListen();

    }

    private void initView() {
        rb_textsize_big = findViewById(R.id.rb_textsize_big);
        rb_textsize_big.setChecked(true);

        title_back = findViewById(R.id.title_back);
    }

    private void initData() {

    }

    private void initListen() {

        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //此方法可以监听滑动，实现右滑退出，但是无法检测速度来判定是否启动退出
        setGestureListener();
    }

    private void stutas() {
        Window window =SettingActivity.this.getWindow();
        //设置透明状态栏，这样才能让ContentView向上
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //设置flag调用setStatusBarColor来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        //window.setStatusBarColor(R.color.green);

        ViewGroup mContentView = SettingActivity.this.findViewById(Window.ID_ANDROID_CONTENT);

        View mChildView = mContentView.getChildAt(0);
        if(mChildView != null){

            //!!!是设置ContentView的第一个子view的FitsSystemWindow,使其不为系统View预留空间
            ViewCompat.setFitsSystemWindows(mChildView,false);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setGestureListener(){

        linearLayout = findViewById(R.id.setting_linearLayout);

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


    public void click_clear_session(View view) {
        Toast.makeText(SettingActivity.this,"缓存已清除",Toast.LENGTH_SHORT).show();
    }

    public void click_share(View view) {
        Toast.makeText(SettingActivity.this,"已分享给好友",Toast.LENGTH_SHORT).show();
    }

    public void click_internet(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    Looper.prepare();
                    Toast.makeText(SettingActivity.this,"网络良好",Toast.LENGTH_SHORT).show();
                    Looper.loop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void click_evaluate(View view) {
        Toast.makeText(SettingActivity.this,"评分功能正在开发，敬请期待",Toast.LENGTH_SHORT).show();
    }

    public void click_advert(View view) {
        Toast.makeText(SettingActivity.this,"广告投放功能正在开发，敬请期待",Toast.LENGTH_SHORT).show();
    }

    public void click_about(View view) {
        startActivity(new Intent(SettingActivity.this, AboutActivity.class));
    }
}
