package com.example.administrator.dqd.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.example.administrator.dqd.ContentActivity;
import com.example.administrator.dqd.ImageLoader;
import com.example.administrator.dqd.NetWork;
import com.example.administrator.dqd.R;
import com.example.administrator.dqd.Url;
import com.example.administrator.dqd.pager.BasePager;
import com.example.administrator.dqd.pager.NoScollViewPager;
import com.makeramen.roundedimageview.RoundedImageView;
import com.viewpagerindicator.TabPageIndicator;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private static final int UPDATE_UI = 1;
    private View view11;
    private View view12;
    private View view13;
    private ViewPager viewPager;
    private NoScollViewPager soccer_viewPager;
    private List<View> viewList;
    //定义进圈精选视图
    private View circle_left,circle_right;
    //定义进圈精选下划线
    private View xian1,xian2;
    //定义侧滑菜单布局，即main_menu
    private DrawerLayout drawerLayout;
    private View menu;
    private CircleFragment circleFragment;
    private SelectedFragment selectedFragment;
    private Fragment fragment;
    private android.support.v4.app.FragmentManager fragmentManager;
    private ListView listView,listView2;
    List<BasePager> listPager;
    private TabPageIndicator tabPageIndicator;
    String [] tabName ={"头条","热门","中超","懂球号","集锦","英超","西甲","意甲","德甲","五洲","深度","专题"};
    public int[] pictureId = {R.drawable.viewpager0, R.drawable.viewpager1, R.drawable.viewpager2,
            R.drawable.viewpager3, R.drawable.viewpager4, R.drawable.viewpager5};
    String urladdress = "";
    private ImageLoader imageLoader;
    ImageView imageView;
    List<String> urls;

//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what){
//                case UPDATE_UI:
//                    post_pic.setImageBitmap((Bitmap) msg.obj);
//                    break;
//            }
//        }
//    };

    public void onCreate(){

    }

    @SuppressLint("InflateParams")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        viewPager = view.findViewById(R.id.home_viewpager);


        //indicator = viewPager.findViewById(R.id.soccer_indicator);
        LayoutInflater inflater1 = getLayoutInflater();
        View view1 = inflater1.inflate(R.layout.home_soccer_viewpager, null);
        View view2 = inflater1.inflate(R.layout.home_world_viewpager, null);
        View view3 = inflater1.inflate(R.layout.home_circle_viewpager, null);
        view11 = view.findViewById(R.id.xian1);
        view12 = view.findViewById(R.id.xian2);
        view13 = view.findViewById(R.id.xian3);

        soccer_viewPager = view1.findViewById(R.id.soccer_viewpager);
        listView2 = view2.findViewById(R.id.world_listView);

        View tv_soccer = view.findViewById(R.id.titleTextLeft);
        View tv_world = view.findViewById(R.id.titleText);
        View tv_circle = view.findViewById(R.id.titleTextRight);

        //定义头像
        View head = view.findViewById(R.id.leftText);
        menu = Objects.requireNonNull(getActivity()).findViewById(R.id.main_menu);
        drawerLayout = getActivity().findViewById(R.id.drawer_layout_main);

        listView2.setAdapter(new ListViewAdapter(listView2));
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle bundle = new Bundle();
                bundle.putInt("position",position);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(getContext(),ContentActivity.class);
                startActivity(intent);
            }
        });


        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

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

//        //找到soccer下的listview
//        listView = view1.findViewById(R.id.soccer_listView);
//        listView.setAdapter(new MyListAdapter());


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if(position==0)
                {
                    view11.setVisibility(View.VISIBLE);
                    view12.setVisibility(View.INVISIBLE);
                    view13.setVisibility(View.INVISIBLE);


                }else if (position==1) {
                    view11.setVisibility(View.INVISIBLE);
                    view12.setVisibility(View.VISIBLE);
                    view13.setVisibility(View.INVISIBLE);
                }else if (position==2){

                    //获取进圈精选下划线视图
                    xian1 = (viewList.get(2)).findViewById(R.id.xian1);
                    xian2 = (viewList.get(2)).findViewById(R.id.xian2);



                    //实例化进圈和精选的视图
                    circle_left = (viewList.get(2)).findViewById(R.id.circle_left);
                    circle_right = (viewList.get(2)).findViewById(R.id.circle_right);

                    view11.setVisibility(View.INVISIBLE);
                    view12.setVisibility(View.INVISIBLE);
                    view13.setVisibility(View.VISIBLE);
                    //对进圈和精选的视图进行监听
                    initListen();
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //头像点击事件
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(menu);
            }
        });

        tv_soccer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });
        tv_world.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });
        tv_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
            }
        });



        initMenu();



        listPager = new ArrayList<BasePager>();
        for(int i=0;i<tabName.length;i++){
            BasePager basePager = new BasePager(getActivity(),tabName[i],i);
            listPager.add(basePager);
        }

        //为soccer_viewPager设置适配器
        soccer_viewPager.setAdapter(new MyViewPagerAdapter());
        TabPageIndicator tabPageIndicator = view1.findViewById(R.id.tabPageIndicator);
        //TabPageIndicator和ViewPager关联
        tabPageIndicator.setViewPager(soccer_viewPager);
        //以后监听页面的变化用TabPageIndicator



        return view;
    }

    public void initMenu() {
        //屏幕保持高亮，不被侧滑菜单遮盖
        //drawerLayout.setScrimColor(0x00ffffff);
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                //滑动过程中不断回调 slideOffset:0~1,使得主界面一同滑动
//                View content = drawerLayout.getChildAt(0);
//                float scale = 1 - slideOffset;//1~0
//                content.setTranslationX(drawerView.getMeasuredWidth() * (1 - scale));//0~width
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    ///监听方法
    private void initListen() {

        //默认选择进圈视图
        fragmentManager = getChildFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        if(xian1.getVisibility()== View.VISIBLE &&circleFragment==null){
            circleFragment = new CircleFragment();
            ft.add(R.id.fl_circle,circleFragment).commit();
        }//在此处设置默认，要考虑到viewpager切换回来的情况


        circle_left.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                FragmentTransaction ft = fragmentManager.beginTransaction();
                hideFragments(ft);
                ft.show(circleFragment).commit();
                //设置下划线显示隐藏
                xian1.setVisibility(View.VISIBLE);
                xian2.setVisibility(View.INVISIBLE);
            }
        });

        circle_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = fragmentManager.beginTransaction();
                hideFragments(ft);

                if(selectedFragment!=null){
                    ft.show(selectedFragment).commit();
                }else{
                    selectedFragment = new SelectedFragment();
                    ft.add(R.id.fl_circle,selectedFragment).commit();
                }
                //设置下划线显示隐藏
                xian1.setVisibility(View.INVISIBLE);
                xian2.setVisibility(View.VISIBLE);
            }
        });



    }

    public void hideFragments(FragmentTransaction ft){
        if(circleFragment!=null){
            ft.hide(circleFragment);
        }
        if(selectedFragment!=null){
            ft.hide(selectedFragment);
        }
    }


//    private class MyListAdapter extends BaseAdapter{
//
//        //一共有多少条数据需要显示
//        @Override
//        public int getCount() {
//            return 300;
//        }
//
//        //返回指定position位置对应的对象
//        @Override
//        public Object getItem(int position) {
//            return null;
//        }
//        //返回position对应的ID
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//        //获取一个view，原来显示ListView的数据，作为一个条目出现
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
////            TextView tv = new TextView(getActivity());
////            tv.setText("哈哈哈"+position);
////            return tv;
//
//
//            //进行优化，复用创建的视图，仅需传入数据即可
//            TextView tv;
//            if(convertView == null){
//                tv = new TextView(getActivity());
//            }else {
//                tv = (TextView) convertView;
//            }
//
//            tv.setText("哈哈哈"+position);
//            return tv;
//        }
//    }


    class MyViewPagerAdapter extends PagerAdapter{


        @Override
        public int getCount() {
            if(listPager==null){
                return 0;
            }else{
                return listPager.size();
            }
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            BasePager basePager = listPager.get(position);
            View rootView = basePager.rootView;
            //初始化数据
            basePager.initData();
            container.addView(rootView);
            return rootView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
           container.removeView((View) object);
        }


        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }


        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabName[position];
        }
    }

    //世界部分ListView适配器
    class ListViewAdapter extends BaseAdapter {

        private ImageLoader imageLoader;
        private ListView mListView;


        public ListViewAdapter(ListView listView){
            mListView = listView;
            imageLoader= new ImageLoader(mListView);
        }

        @Override
        public int getCount() {
            return 50;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {


//            ImageView post_pic;

            Viewholder viewholder = null;
            if(convertView==null){
                viewholder = new Viewholder();
                convertView =  View.inflate(getContext(),R.layout.item_post,null);
                //post_head = new RoundedImageView(getContext());
                viewholder.post_head = convertView.findViewById(R.id.post_head);
                //post_name = new TextView(getContext());
                viewholder.post_name = convertView.findViewById(R.id.post_name);
                //post_content = new TextView(getContext());
                viewholder.post_content = convertView.findViewById(R.id.post_content);
                //post_pic = new ImageView(getContext());
                viewholder.post_pic = convertView.findViewById(R.id.post_pic);
                convertView.setTag(viewholder);

            }else{
//                post_head = convertView.findViewById(R.id.post_head);
//                post_name = convertView.findViewById(R.id.post_name);
//                post_content = convertView.findViewById(R.id.post_content);
//                post_pic = convertView.findViewById(R.id.post_pic);

                  viewholder = (Viewholder) convertView.getTag();
                  viewholder.post_pic.setVisibility(View.VISIBLE);
            }

            if(NetWork.isNetSystemUsable(getContext())){
//                Toast.makeText(getContext(),"网络已连接",Toast.LENGTH_SHORT).show();
                AVQuery<AVObject> query = new AVQuery<>("Posts");
                query.selectKeys(Arrays.asList("title", "content","pics","writer"));
                query.limit(50);
                final Viewholder finalViewholder = viewholder;
                query.findInBackground(new FindCallback<AVObject>() {
                    @Override
                    public void done(List<AVObject> list, AVException e) {
//                        for (AVObject avObject : list) {
                        String title = list.get(position).getString("title");
                        String content = list.get(position).getString("content");
                        String writer = list.get(position).getString("writer");
                        urls = list.get(position).getList("pics");

//                            System.out.println("11111"+avObject);
//                            System.out.println("22222"+avObject.getList("pics"));
//                            for(int i=0;i<urls.size();i++){
//                                String s = urls.get(i);
//                                System.out.println(s);
//                            }
//                            System.out.println(title+"----"+content+"--------"+urls);

                        if(urls.size()==0){
                            System.out.println(title+"----"+urls);
                            finalViewholder.post_pic.setVisibility(View.GONE);
                        }
                        if(!urls.isEmpty()){
                            urladdress = urls.get(0);
//                            System.out.println(urladdress);
                            finalViewholder.post_pic.setImageResource(R.drawable.loading);
                            //finalViewholder.post_head.setImageResource(R.drawable.loading);

                            if(!urladdress.isEmpty()){
                                finalViewholder.post_pic.setTag(urladdress);
//                           finalViewholder.post_head.setTag(urladdress);

                                imageLoader.showImageByAsyncTask(finalViewholder.post_pic,urladdress);
//                            imageLoader.showImageByAsyncTask(finalViewholder.post_head,urladdress);


                            }

                            if(urladdress == ""){
                                finalViewholder.post_pic.setVisibility(View.GONE);
                            }

                        }//else {
//
//                        }


//
//                    // 如果访问没有指定返回的属性（key），则会报错，在当前这段代码中访问 location 属性就会报错
////                    String location = avObject.getString("location");

                        finalViewholder.post_pic.setTag(urladdress);

                        finalViewholder.post_content.setText(title);
                        finalViewholder.post_name.setText(writer);




//                            new Thread(new Runnable() {
//                                @Override
//                                public void run() {
//                                     requestImage(urladdress);
//                                }
//                            }).start();
//
//                            if(!urladdress.isEmpty()){
//                                final String finalUrladdress = urladdress;
//                                new Thread(new Runnable() {
//                                    Drawable drawable;
//                                    @Override
//                                    public void run() {
//                                        try {
//                                            drawable = (new Url()).loadImageFromNetWork(finalUrladdress);
////                                            System.out.println("finalUrladdress----"+finalUrladdress);
////                                            System.out.println("drawable-----"+drawable);
//
//                                            post_head.post(new Runnable() {
//                                                @Override
//                                                public void run() {
//                                                    post_head.setImageDrawable(drawable);
//                                                    post_pic.setImageDrawable(drawable);
//                                                }
//                                            });
//
//                                        } catch (IOException e1) {
//                                            e1.printStackTrace();
//                                        }
//
//                                    }
//                                }).start();
//                            }


                        }
//                    }
                });
            }



//            int i = position % 6;
//            post_head.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.head,null));
//            post_name.setText(position+"一只老刺猬");
//            post_content.setText(position+"【深夜闲聊】 如果你最好的朋友生意失败破产，向你借一万救急，你会愿意借给他吗？每晚10点来D站，睡前我们一起聊一聊");
//           // post_pic.setImageDrawable(ResourcesCompat.getDrawable(getResources(),pictureId[i],null));

            return convertView;
        }

        class Viewholder{

            RoundedImageView post_head;
            TextView post_name;
            TextView post_content;
            ImageView post_pic;

        }
    }

//    private void requestImage(String imageUrl) {
//        try {
//            //定义URL地址
//            URL url = new URL(imageUrl);
//            //打开连接
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            //设置请求
//            urlConnection.setRequestMethod("GET");
//
//            //获取请求状态
//            int responseCode = urlConnection.getResponseCode();
//            if(responseCode == 200){
//                Bitmap bitmap = BitmapFactory.decodeStream(urlConnection.getInputStream());
//                handler.sendMessage(handler.obtainMessage(UPDATE_UI,bitmap));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}


