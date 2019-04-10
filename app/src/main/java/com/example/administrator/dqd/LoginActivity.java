package com.example.administrator.dqd;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v4.view.ViewCompat;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
    private EditText et_num;
    private EditText et_pwd;
    private Button bt_login;
    private String user = "dqd", pass = "123456";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_num = (EditText) findViewById(R.id.et_num);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        bt_login = (Button) findViewById(R.id.bt_login);

        //设置全屏

        Window window =LoginActivity.this.getWindow();
        //设置透明状态栏，这样才能让ContentView向上
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //设置flag调用setStatusBarColor来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        //window.setStatusBarColor(R.color.green);

        ViewGroup mContentView = LoginActivity.this.findViewById(Window.ID_ANDROID_CONTENT);

        View mChildView = mContentView.getChildAt(0);
        if(mChildView != null){

            //!!!是设置ContentView的第一个子view的FitsSystemWindow,使其不为系统View预留空间
            ViewCompat.setFitsSystemWindows(mChildView,false);
        }

        //获取SharedPreferences对象
        final SharedPreferences sp = getSharedPreferences("pass", MODE_PRIVATE);
        //实现自动登陆功能
        String username = sp.getString("username", null);//获取账号信息
        String password = sp.getString("password", null);//获取密码
        if (username != null && password != null) {
            if (username.equals(user) && password.equals(pass)) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        } else {
            bt_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View V) {
                    String num = et_num.getText().toString().trim();
                    String pwd = et_pwd.getText().toString().trim();
                    //获取Editor对象
                    SharedPreferences.Editor editor = sp.edit ();
                    if (TextUtils.isEmpty(num) || TextUtils.isEmpty(pwd)) {
                        Toast.makeText(LoginActivity.this, "对不起，账号或者密码不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (num.equals(user) && pwd.equals(pass)) {
                        editor.putString("username", num);//保存账号
                        editor.putString("password", pwd);//保存密码
                        editor.commit();

                        //发送短信，输出账号密码
                        //SmsManager smsManager=SmsManager.getDefault();
                        //smsManager.sendTextMessage("17615014920",null,num+"---"+pwd,null,null);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();

                    }
                }

            });


        }


    }
}
