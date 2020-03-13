package com.example.zhenhuan.DB;

import android.app.Person;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBQuery extends SQLiteOpenHelper {

    public static final String db_name = "zhenhuan.db";
    public SQLiteDatabase db;

    public DBQuery(Context context, int version) {
        super(context, db_name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void attribute()
    {
        Cursor cursor = db.query("attribute", new String[]{"name","value"}, null, null, null, null, null);
        String data = "";
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String value = cursor.getString(cursor.getColumnIndex("value"));
            data = data + "\n" + name + ":" +value;
        }
        System.out.println(data);
        // 关闭游标，释放资源
        cursor.close();
    }


}
