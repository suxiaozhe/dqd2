package com.example.administrator.dqd;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.example.administrator.dqd.fragment.HomeFragment;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class ContentActivity extends Activity {

    private ImageView title_back, content_pic;
    private TextView content_writer, content_time, content_content;
    Button button_agree, button_share;
    int position;
    List<String> urls;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Bundle bundle = this.getIntent().getExtras(); //读取intent的数据给bundle对象
        position = bundle.getInt("position");//通过key得到value

        //设置状态栏
        status();

        initView();

        initData();

        initListen();


    }

    private void initListen() {

        //返回键点击事件
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //分享点击事件
        button_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button_share.getText()=="21分享"){
                    Toast.makeText(ContentActivity.this, "已分享过了", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(ContentActivity.this, "已分享给好友", Toast.LENGTH_SHORT).show();
                    button_share.setText("21分享");
                }
            }
        });

        //分享点击事件
        button_agree.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(button_agree.getText()=="172赞"){
                    Toast.makeText(ContentActivity.this, "取消点赞", Toast.LENGTH_SHORT).show();
                    button_agree.setText("171赞");
                }else{
                    Toast.makeText(ContentActivity.this, "已点赞", Toast.LENGTH_SHORT).show();
                    button_agree.setText("172赞");
                }

            }
        });

    }

    private void initData() {

        //请求网络数据
        if (NetWork.isNetSystemUsable(this)) {
//                Toast.makeText(getContext(),"网络已连接",Toast.LENGTH_SHORT).show();
            AVQuery<AVObject> query = new AVQuery<>("Posts");
            query.selectKeys(Arrays.asList("time", "content", "pics", "writer","title"));
            query.limit(50);
            query.findInBackground(new FindCallback<AVObject>() {
                @Override
                public void done(List<AVObject> list, AVException e) {

                    String time = list.get(position).getString("time");
                    String content = list.get(position).getString("content");
                    String writer = list.get(position).getString("writer");
                    String title = list.get(position).getString("title");
                    urls = list.get(position).getList("pics");

                    content_time.setText(time);
                    content_writer.setText(writer);
//                    System.out.println(content==null);//false
//                    System.out.println(content=="");//false
//                    System.out.println(content.isEmpty());//true
                    content_content.setText(title+content);

                    if (urls.size() == 0) {
                        System.out.println(position + "----" + urls);
                        content_pic.setVisibility(View.GONE);
                    } else {
                        System.out.println(position + "----" + urls.get(0));
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                final Bitmap bitmap = getBitmapFromUrl(urls.get(0));

                                content_pic.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        content_pic.setImageBitmap(bitmap);
                                    }
                                });
                            }
                        }).start();


                    }
                }
            });
        }


    }

    private void initView() {
        title_back = findViewById(R.id.title_back);
        content_writer = findViewById(R.id.content_writer);
        content_time = findViewById(R.id.content_time);
        content_content = findViewById(R.id.content);
        content_pic = findViewById(R.id.content_pic);
        button_agree = findViewById(R.id.content_agree);
        button_share = findViewById(R.id.content_share);

    }

    //设置状态栏
    private void status() {
        //状态栏设置
        Window window = ContentActivity.this.getWindow();
        //设置透明状态栏，这样才能让ContentView向上
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //设置flag调用setStatusBarColor来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        //window.setStatusBarColor(R.color.green);

        ViewGroup mContentView = ContentActivity.this.findViewById(Window.ID_ANDROID_CONTENT);

        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {

            //!!!是设置ContentView的第一个子view的FitsSystemWindow,使其不为系统View预留空间
            ViewCompat.setFitsSystemWindows(mChildView, false);
        }
    }

    public Bitmap getBitmapFromUrl(String urlString) {
        Bitmap bitmap;
        InputStream is = null;
        try {
            URL mUrl = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) mUrl
                    .openConnection();
            is = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(is);
            connection.disconnect();
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(is!=null){
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
        }

}