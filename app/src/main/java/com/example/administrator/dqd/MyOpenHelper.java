package com.example.administrator.dqd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {
    public MyOpenHelper(Context context) {
        super(context,"dqd.db", null, 1);
    }

    //数据库第一次创建时调用，一般用于初始化表结构
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table info(name text,number text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
