package com.example.zhenhuan.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.zhenhuan.tool.Utils;

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

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public Map attribute_info(SQLiteDatabase db)
    {
        Cursor cursor = db.query("attribute", new String[]{"name","value"}, null, null, null, null, null);
        String data = "";
        Map<String,Integer> attribute_info = new HashMap<String,Integer>();
        Log.i("sql", "---query attribute_info---");
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int value = Utils.StringToInt(cursor.getString(cursor.getColumnIndex("value")));
            attribute_info.put(name,value);
        }
        Log.i("sql", attribute_info.toString());
        Log.i("sql", "--------");
        cursor.close();
        return attribute_info;
    }


}
