package com.example.administrator.dqd.pager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.administrator.dqd.MainActivity;
import com.example.administrator.dqd.R;

import java.util.ArrayList;

public class BasePager {
        //上下文
    public Context context;//homefragment
    //视图，代表各个页面
    public View rootView;

    private ViewPager viewPager;
    private TextView tv_title;
    private LinearLayout ll_point_group;
    private ListView listView;
    private ListViewAdapter adapter;
    private int preposition = 0;
    boolean isRunning = false;
    public int[] pictureId = {R.drawable.viewpager0, R.drawable.viewpager1, R.drawable.viewpager2,
            R.drawable.viewpager3, R.drawable.viewpager4, R.drawable.viewpager5};
    public String[] titie = {"人和VS恒大：黄博文、刘弈鸣首发，布朗宁替补","半场战报：山东0-1一方，卡拉斯科失良机，穆谢奎头球破门","官方：黄博文当选长沙市足协副主席",
            "头号玩家？国外小哥玩FM连吼带叫，还怒踢茶杯“被罚上看台”", "恒大1-2人和联赛四连胜终结，保利尼奥染红，陈杰补时绝杀","克林斯曼回应执教国足传闻：时间会给出答案"};
    public String[] comment = {"1234评论","1342评论","2341评论","2431评论","2143评论","4231评论"};

//    private TextView textView;

    private String ss ,sss;

    public BasePager(final Context context, String s, int i){

        this.context = context;
        //构造方法一执行就初始化视图
        rootView = initView();
        ss = s;

        new Thread(new Runnable() {
            @Override
            public void run(){
                isRunning=true;
                while (isRunning){
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //往下跳一位，注意Android系统中的视图组件并不是线程安全的，如果要更新视图，必须在主线程中更新，不可以在子线程中执行更新的操作。
                    //既然这样，就需要在子线程中通知主线程，让主线程做更新操作，可以使用Handler对象,也可以下面这样
                    viewPager.post(new Runnable(){
                        @Override
                        public void run(){
                            if(viewPager.getCurrentItem()<5){
                                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                            }else{
                                viewPager.setCurrentItem(0);
                            }
                        }
                    });

                }
            }
        }).start();

    }
    //用于初始化公共视图
    private View initView() {
//        textView = new TextView(context);
//        textView.setGravity(Gravity.CENTER);
//        textView.setTextColor(Color.RED);
//        textView.setTextSize(25);
//        return textView;

        View view = View.inflate(context,R.layout.soccer_viewpager_viewpager,null);
        listView = view.findViewById(R.id.listview);


        View topNewsView = View.inflate(context,R.layout.topnews,null);
        viewPager = topNewsView.findViewById(R.id.viewpager);
        tv_title = topNewsView.findViewById(R.id.tv_title);
        sss = tv_title.getText().toString();
        ll_point_group = topNewsView.findViewById(R.id.ll_point_group);

        ArrayList<View> pointViews = new ArrayList<View>();
        View pointView;
        LinearLayout.LayoutParams layoutParams;
        for(int i=0;i < 6;i++){
            //加小白点，指示器
            pointView = new View(context);
            pointView.setBackgroundResource(R.drawable.selector_bg_point);
            layoutParams = new LinearLayout.LayoutParams(15,15);
            if(i!=0){
                layoutParams.leftMargin = 15;
            }
            pointView.setEnabled(false);
            ll_point_group.addView(pointView,layoutParams);
            //默认第一个视图的点是亮的，preposition是0
            ll_point_group.getChildAt(preposition).setEnabled(true);
        }



        viewPager.setAdapter(new MyViewPager());
        viewPager.addOnPageChangeListener(new MyOnPageChangeLiatener());


        adapter = new ListViewAdapter();
        listView.addHeaderView(topNewsView);
        listView.setAdapter(adapter);

        return view;
    }
    //初始化数据，当孩子需要初始化数据、绑定数据、联网请求数据并绑定的时候，重写该方法
    @SuppressLint("SetTextI18n")
    public void initData(){

        //textView.setText(ss);
        // tv_title = new TextView(context);
        tv_title.setText(ss+":"+sss);

    }


    class ListViewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView text_View;
            ImageView image_View;
            TextView textView_comment;
            if(convertView == null){
                convertView = View.inflate(context,R.layout.item_listview,null);
                text_View = new TextView(context);
                text_View = convertView.findViewById(R.id.item_tv);
                image_View = new ImageView(context);
                image_View = convertView.findViewById(R.id.item_iv);
                textView_comment = new TextView(context);
                textView_comment = convertView.findViewById(R.id.item_comment);

            }else{
                //复用历史缓存对象
                text_View = convertView.findViewById(R.id.item_tv);
                image_View = convertView.findViewById(R.id.item_iv);
                textView_comment = convertView.findViewById(R.id.item_comment);
            }
            int i = position % 6;
            text_View.setText(position+":"+titie[i]);
            image_View.setBackgroundResource(pictureId[i]);
            textView_comment.setText(comment[i]);
            return convertView;
        }
    }

    static class ListViewHolder{
        ImageView item_iv;
        TextView item_tv;

        TextView item_comment;

    }


    class MyOnPageChangeLiatener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onPageSelected(int i) {


            //1.设置文本
            switch(i){
                case 0:
                    tv_title.setText(sss);
                    break;
                case 1:
                    tv_title.setText(titie[i]);
                    break;
                case 2:
                    tv_title.setText(titie[i]);
                    break;
                case 3:
                    tv_title.setText(titie[i]);
                    break;
                case 4:
                    tv_title.setText(titie[i]);
                    break;
                case 5:
                    tv_title.setText(titie[i]);
                    break;
            }
            //2.对应页面的点高亮，之前的点变暗
            ll_point_group.getChildAt(preposition).setEnabled(false);
            ll_point_group.getChildAt(i).setEnabled(true);
            preposition = i;

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }

    class MyViewPager extends PagerAdapter{

        @Override
        public int getCount() {
            return 6;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = new ImageView(context);
            switch(position){
                case 0:
                    imageView.setBackgroundResource(R.drawable.viewpager0);
                    break;
                case 1:
                    imageView.setBackgroundResource(R.drawable.viewpager1);
                    break;
                case 2:
                    imageView.setBackgroundResource(R.drawable.viewpager2);
                    break;
                case 3:
                    imageView.setBackgroundResource(R.drawable.viewpager3);
                    break;
                case 4:
                    imageView.setBackgroundResource(R.drawable.viewpager4);
                    break;
                case 5:
                    imageView.setBackgroundResource(R.drawable.viewpager5);
                    break;
            }
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
           container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }
    }


}



//    //上下文
//    public Context context;//homefragment
//    //视图，代表各个页面
//    public View rootView;
//
//    private TextView textView;
//
//    private String ss;
//
//    public BasePager(Context context, String s, int i) {
//
//        this.context = context;
//        //构造方法一执行就初始化视图
//        rootView = initView();
//        ss = s;
//
//    }
//
//    //用于初始化公共视图
//    @SuppressLint("ResourceAsColor")
//    private View initView() {
//        textView = new TextView(context);
//        textView.setGravity(Gravity.CENTER);
//        textView.setTextColor(Color.RED);
//        textView.setTextSize(25);
//        textView.setBackgroundColor(com.viewpagerindicator.R.color.green);
//        return textView;
//    }
//
//    //初始化数据，当孩子需要初始化数据、绑定数据、联网请求数据并绑定的时候，重写该方法
//    public void initData() {
//
//        textView.setText(ss);
//
//    }
//}