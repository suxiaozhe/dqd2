package com.example.administrator.dqd;

import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.IOException;
import java.net.URL;

public class Url {
//    URL url;
//    public Url (URL url){
//        this.url=url;
//    }
    public Drawable loadImageFromNetWork(String url) throws IOException {

        Drawable drawable=null;

        drawable = Drawable.createFromStream(new URL(url).openStream(),url);
        if(drawable==null){
//            Log.d("test","null drawable");
        }else
        {
//            Log.d("test","not null drawable");
        }
        return drawable;
    }
}
