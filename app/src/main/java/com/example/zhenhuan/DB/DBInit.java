package com.example.zhenhuan.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class DBInit extends SQLiteOpenHelper {
    // 数据库默认名字
    public static final String db_name = "zhenhuan_db";
    SQLiteDatabase db;

    public DBInit(Context context, int version) {
        super(context, db_name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("sql", "createTable: ");
        String attribute_table = "create table attribute(id integer primary key autoincrement,name vchar,value int)";
        db.execSQL(attribute_table);

//        addtestdata(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public void addtestdata(SQLiteDatabase db){
        Map<String,Integer> map_attribute = new HashMap<String,Integer>();
        map_attribute.put("健康",80);
        map_attribute.put("魅力",60);
        map_attribute.put("学识",10);

        for(Map.Entry<String,Integer> entry: map_attribute.entrySet()){
            String mapKey = entry.getKey();
            int mapValue = entry.getValue();
            Log.i("sql", "insert into attribute");
            Object objects[] = new Object[2];
            objects[0] = mapKey;
            objects[1] = mapValue;
            db.execSQL("insert into attribute(name,value) values('?','?')", objects);
        }
    }




}
