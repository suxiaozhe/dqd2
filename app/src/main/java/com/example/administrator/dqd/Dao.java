//package com.example.administrator.dqd;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//
//public class Dao{
//    private Context context;
//    private SQLiteDatabase db;
//    public Dao(Context context){
//        this.context = context;
//        //新建MyOpenHelper
//        MyOpenHelper myOpenHelper = new MyOpenHelper(context);
//        db = myOpenHelper.getWritableDatabase();
//    }
//
//    //数据库增加的方法
//    public long insert(String table, String nullColumnHack, ContentValues values){
//        return db.insert("info",null,values);
//    }
//    //数据库查询方法
//    public Cursor query(String table,String Colums,String selection,String[] selectionArgs,String groupBy,String having,String orderBy){
//        return db.query("info",null,null,null,null,null,null);
//    }
//}
