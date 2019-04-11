package com.example.administrator.dqd;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class PublishActivity extends Activity {

    private ImageView title_back;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_publish);

        title_back = findViewById(R.id.title_back);
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Window window =PublishActivity.this.getWindow();
        //设置透明状态栏，这样才能让ContentView向上
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //设置flag调用setStatusBarColor来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        //window.setStatusBarColor(R.color.green);

        ViewGroup mContentView = PublishActivity.this.findViewById(Window.ID_ANDROID_CONTENT);

        View mChildView = mContentView.getChildAt(0);
        if(mChildView != null){

            //!!!是设置ContentView的第一个子view的FitsSystemWindow,使其不为系统View预留空间
            ViewCompat.setFitsSystemWindows(mChildView,false);
        }
    }

}
