package com.example.zhenhuan.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class DBInit extends SQLiteOpenHelper {
    // 数据库默认名字
    public static final String db_name = "zhenhuan.db";

    public DBInit(Context context, int version) {
        super(context, db_name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    private void createTable(SQLiteDatabase db) {
        String attribute_table = "create table attribute(id integer primary key autoincrement,name vchar,value int)";
        db.execSQL(attribute_table);
        Map<String,Integer> map_attribute = new HashMap<String,Integer>();
        map_attribute.put("健康",80);
        map_attribute.put("魅力",60);
        map_attribute.put("学识",10);

        for(Map.Entry<String,Integer> entry: map_attribute.entrySet()){
            String mapKey = entry.getKey();
            int mapValue = entry.getValue();
            String attribute_sql= MessageFormat.format("insert into attribute(name,value) values('{0}','{1}')", mapKey,mapValue);
            db.execSQL(attribute_sql);
        }

    }



}
