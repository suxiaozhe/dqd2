package com.example.administrator.dqd;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.dqd.fragment.CircleFragment;
import com.example.administrator.dqd.fragment.DataFragment;
import com.example.administrator.dqd.fragment.FavouriteFragment;
import com.example.administrator.dqd.fragment.HomeFragment;
import com.example.administrator.dqd.fragment.LotteryFragment;
import com.example.administrator.dqd.fragment.MatchFragment;
import com.example.administrator.dqd.fragment.SelectedFragment;

public class MainActivity extends FragmentActivity {
    private RadioGroup rg_main;
    private HomeFragment homeFragment;
    private MatchFragment matchFragment;
    private FavouriteFragment favouriteFragment;
    private LotteryFragment lotteryFragment;
    private DataFragment dataFragment;
    private CircleFragment circleFragment;
    private SelectedFragment selectedFragment;
    private RadioButton rb_main_home;
    private RadioButton rb_main_match;
    private RadioButton rb_main_favorite;
    private RadioButton rb_main_lottery;
    private RadioButton rb_main_data;
    private DrawerLayout menudrawerlayout;
    private LinearLayout main_menu,menu_user_info,menu_user_follow,menu_user_fans,menu_user_publish;
    private RelativeLayout menu_notice,menu_collection,menu_news,menu_shop;
    private RelativeLayout feedback,search,setting;
    private FragmentManager fragmentManager;



    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();


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

        //菜单视图的初始化
        main_menu = findViewById(R.id.main_menu);
        menudrawerlayout = findViewById(R.id.drawer_layout_main);

        initView();

        initData();

        initListen();

    }

    private void initListen() {
        //RadioGroup的选择事件
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                FragmentTransaction ft = fragmentManager.beginTransaction();

                hideFragments(ft);

                switch(checkedId){
                    case R.id.rb_main_home:
                        if(homeFragment != null){
                            ft.show(homeFragment).commit();
                        }else{
                            homeFragment = new HomeFragment();
                            ft.add(R.id.fl_main,homeFragment).commit();
                        }
                        break;
                    case R.id.rb_main_match:
                        if(matchFragment != null){
                            ft.show(matchFragment).commit();
                        }else{
                            matchFragment = new MatchFragment();
                            ft.add(R.id.fl_main,matchFragment).commit();
                        }
                        break;
                    case R.id.rb_main_favorite:
                        if(favouriteFragment != null){
                            ft.show(favouriteFragment).commit();
                        }else{
                            favouriteFragment = new FavouriteFragment();
                            ft.add(R.id.fl_main,favouriteFragment).commit();
                        }
                        break;
                    case R.id.rb_main_lottery:
                        if(lotteryFragment != null){
                            ft.show(lotteryFragment).commit();
                        }else{
                            lotteryFragment = new LotteryFragment();
                            ft.add(R.id.fl_main,lotteryFragment).commit();
                        }
                        break;
                    case R.id.rb_main_data:
                        if(dataFragment != null){
                            ft.show(dataFragment).commit();
                        }else{
                            dataFragment = new DataFragment();
                            ft.add(R.id.fl_main,dataFragment).commit();
                        }
                        break;
                }
            }
        });
        //默认选择首页页面
        rg_main.check(R.id.rb_main_home);

        //侧滑菜单用户头像信息点击事件
        menu_user_info = findViewById(R.id.menu_user_info);
        menu_user_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UserInfoActivity.class));
            }
        });
        //侧滑菜单用户关注点击事件
        menu_user_follow = findViewById(R.id.menu_user_follow);
        menu_user_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FollowActivity.class));
            }
        });

        //侧滑菜单用户粉丝点击事件
        menu_user_fans = findViewById(R.id.menu_user_fans);
        menu_user_fans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FansActivity.class));
            }
        });

        //侧滑菜单用户发表点击事件
        menu_user_publish = findViewById(R.id.menu_user_publish);
        menu_user_publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PublishActivity.class));
            }
        });

        //侧滑菜单我的通知点击事件
        menu_notice = findViewById(R.id.menu_notice);
        menu_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NoticeActivity.class));
            }
        });

        //侧滑菜单收藏历史点击事件
        menu_collection = findViewById(R.id.menu_collection);
        menu_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CollectionActivity.class));
            }
        });

        //侧滑菜单系统消息点击事件
        menu_news = findViewById(R.id.menu_news);
        menu_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,NoticeActivity.class));
            }
        });

        //侧滑菜单懂球帝商城点击事件
        menu_shop = findViewById(R.id.menu_shop);
        menu_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShopActivity.class));
            }
        });

        //侧滑菜单反馈点击事件
        feedback = findViewById(R.id.menu4_feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedbackActivity.class));
            }
        });

        //侧滑菜单搜索点击事件
        search = findViewById(R.id.menu4_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        });

        //侧滑菜单设置点击事件
        setting = findViewById(R.id.menu4_setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
            }
        });

    }

    public void hideFragments(FragmentTransaction ft){

        if(homeFragment!=null){
            ft.hide(homeFragment);
        }
        if(matchFragment!=null){
            ft.hide(matchFragment);
        }
        if(favouriteFragment!=null){
            ft.hide(favouriteFragment);
        }
        if(lotteryFragment!=null){
            ft.hide(lotteryFragment);
        }
        if(dataFragment!=null){
            ft.hide(dataFragment);
        }
    }

    private void initData() {

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

        feedback = findViewById(R.id.menu4_feedback);
        search = findViewById(R.id.menu4_search);
        setting = findViewById(R.id.menu4_setting);


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

        if(keyCode == KeyEvent.KEYCODE_BACK ){// 点击了返回按键
            if(menudrawerlayout.isDrawerOpen(main_menu)){
                menudrawerlayout.closeDrawers();
                return false;
            }else {

                if (manager.getBackStackEntryCount() != 0) {
                    manager.popBackStack();
                } else {
                    exitApp(2000);// 退出应用
                }
                return true;// 返回true，防止该事件继续向下传播

            }
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