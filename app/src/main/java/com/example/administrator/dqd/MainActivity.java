package com.example.administrator.dqd;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.dqd.fragment.DataFragment;
import com.example.administrator.dqd.fragment.FavouriteFragment;
import com.example.administrator.dqd.fragment.HomeFragment;
import com.example.administrator.dqd.fragment.LotteryFragment;
import com.example.administrator.dqd.fragment.MatchFragment;
import com.githang.statusbar.StatusBarCompat;

public class MainActivity extends FragmentActivity {
    private RadioGroup rg_main;
    private HomeFragment homeFragment;
    private MatchFragment matchFragment;
    private FavouriteFragment favouriteFragment;
    private LotteryFragment lotteryFragment;
    private DataFragment dataFragment;
    private RadioButton rb_main_home;
    private RadioButton rb_main_match;
    private RadioButton rb_main_favorite;
    private RadioButton rb_main_lottery;
    private RadioButton rb_main_data;


    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        //第三方库StatusBarCompat，用以更改状态栏颜色
//        StatusBarCompat.setStatusBarColor(MainActivity.this,getResources().getColor(R.color.green));

        Window window =MainActivity.this.getWindow();
        //设置透明状态栏，这样才能让ContentView向上
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //设置flag调用setStatusBarColor来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        //window.setStatusBarColor(R.color.green);

        ViewGroup mContentView = MainActivity.this.findViewById(Window.ID_ANDROID_CONTENT);

        View mChildView = mContentView.getChildAt(0);
        if(mChildView != null){

            //!!!是设置ContentView的第一个子view的FitsSystemWindow,使其不为系统View预留空间
            ViewCompat.setFitsSystemWindows(mChildView,false);
        }





        initView();

        initData();

        initListen();

    }

    private void initListen() {
        //RadioGroup的选择事件
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Fragment fragment = null;
                switch(checkedId){
                    case R.id.rb_main_home:
                        fragment = homeFragment;
                        break;
                    case R.id.rb_main_match:
                        fragment = matchFragment;
                        break;
                    case R.id.rb_main_favorite:
                        fragment = favouriteFragment;
                        break;
                    case R.id.rb_main_lottery:
                        fragment = lotteryFragment;
                        break;
                    case R.id.rb_main_data:
                        fragment = dataFragment;
                        break;

                }
                //实现Fragment切换方法
                switchFragment(fragment);
            }
        });
        //默认选择首页页面
        rg_main.check(R.id.rb_main_home);
    }

    private void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_main,fragment).commit();
    }

    private void initData() {
        //创建Fragment对象
        homeFragment = new HomeFragment();
        matchFragment = new MatchFragment();
        favouriteFragment = new FavouriteFragment();
        lotteryFragment = new LotteryFragment();
        dataFragment = new DataFragment();
    }

    private void initView() {
        rg_main=findViewById(R.id.rg_main);
        rb_main_home=findViewById(R.id.rb_main_home);
        rb_main_match=findViewById(R.id.rb_main_match);
        rb_main_favorite=findViewById(R.id.rb_main_favorite);
        rb_main_lottery=findViewById(R.id.rb_main_lottery);
        rb_main_data=findViewById(R.id.rb_main_data);
        //定义底部标签图片大小和位置
        Drawable drawable_tab1=getResources().getDrawable(R.drawable.main_botton_image_tab1_selector);
        //当这个图片被绘制时，给它绑定一个矩形
        drawable_tab1.setBounds(0,0,80,80);
        //设置图片在文字的哪个方向
        rb_main_home.setCompoundDrawables(null,drawable_tab1,null,null);

        //定义底部标签图片大小和位置
        Drawable drawable_tab2=getResources().getDrawable(R.drawable.main_botton_image_tab2_selector);
        //当这个图片被绘制时，给它绑定一个矩形
        drawable_tab2.setBounds(0,0,80,80);
        //设置图片在文字的哪个方向
        rb_main_match.setCompoundDrawables(null,drawable_tab2,null,null);

        //定义底部标签图片大小和位置
        Drawable drawable_tab3=getResources().getDrawable(R.drawable.main_botton_image_tab3_selector);
        //当这个图片被绘制时，给它绑定一个矩形
        drawable_tab3.setBounds(0,0,120,120);
        //设置图片在文字的哪个方向
        rb_main_favorite.setCompoundDrawables(null,drawable_tab3,null,null);

        //定义底部标签图片大小和位置
        Drawable drawable_tab4=getResources().getDrawable(R.drawable.main_botton_image_tab4_selector);
        //当这个图片被绘制时，给它绑定一个矩形
        drawable_tab4.setBounds(0,0,80,80);
        //设置图片在文字的哪个方向
        rb_main_lottery.setCompoundDrawables(null,drawable_tab4,null,null);

        //定义底部标签图片大小和位置
        Drawable drawable_tab5=getResources().getDrawable(R.drawable.main_botton_image_tab5_selector);
        //当这个图片被绘制时，给它绑定一个矩形
        drawable_tab5.setBounds(0,0,80,80);
        //设置图片在文字的哪个方向
        rb_main_data.setCompoundDrawables(null,drawable_tab5,null,null);

    }

    private FragmentManager manager = getSupportFragmentManager();
    private long firstTime;// 记录点击返回时第一次的时间毫秒值


    /*
     * 重写该方法，判断用户按下返回按键的时候，执行退出应用方法
     * @param keyCode
     * @param event
     * @return
     */

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){// 点击了返回按键
            if(manager.getBackStackEntryCount() != 0){
                manager.popBackStack();
            }else {
                exitApp(2000);// 退出应用
            }
            return true;// 返回true，防止该事件继续向下传播
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 退出应用
     * @param timeInterval 设置第二次点击退出的时间间隔
     */

    private void exitApp(long timeInterval) {
        // 第一次肯定会进入到if判断里面，然后把firstTime重新赋值当前的系统时间
        // 然后点击第二次的时候，当点击间隔时间小于2s，那么退出应用；反之不退出应用
        if(System.currentTimeMillis() - firstTime >= timeInterval){
            Toast.makeText(MainActivity.this, "再按一次退出懂球帝",Toast.LENGTH_LONG).show();
            firstTime = System.currentTimeMillis();
        }else {
            finish();// 销毁当前activity
            System.exit(0);// 完全退出应用
        }
    }


}
