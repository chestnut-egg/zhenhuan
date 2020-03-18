package com.example.zhenhuan.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.zhenhuan.tool.Utils;

import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DBInit extends SQLiteOpenHelper {
    // 数据库默认名字
    public static final String db_name = "zhenhuan_db";

    public DBInit(Context context, int version) {
        super(context, db_name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("sql", "createTable: ");
        String rule_table = "create table rule(id integer primary key autoincrement,name vchar,sex int,age int,family int,isdead int)";
        db.execSQL(rule_table);
        String attribute_table = "create table attribute(id integer primary key autoincrement,name vchar,health int,charm int,knowledge int,talent int,luck int)";
        db.execSQL(attribute_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public Map attribute_info(SQLiteDatabase db,int id)
    {
        Cursor cursor = db.query("attribute", new String[]{"health","charm","knowledge","talent","luck"}, "id = ?", new String[]{""+id}, null, null, null);
        String data = "";
        Map<String,Integer> attribute_info = new LinkedHashMap<String,Integer>();
        Log.i("sql", "---query attribute_info---");
        while(cursor.moveToNext()){
            String health = cursor.getString(cursor.getColumnIndex("health"));
            attribute_info.put("health",Utils.StringToInt(health));
            String charm = cursor.getString(cursor.getColumnIndex("charm"));
            attribute_info.put("charm",Utils.StringToInt(charm));
            String knowledge = cursor.getString(cursor.getColumnIndex("knowledge"));
            attribute_info.put("knowledge",Utils.StringToInt(knowledge));
            String talent = cursor.getString(cursor.getColumnIndex("talent"));
            attribute_info.put("talent",Utils.StringToInt(talent));
            String luck = cursor.getString(cursor.getColumnIndex("luck"));
            attribute_info.put("luck",Utils.StringToInt(luck));
        }
        Log.i("sql", attribute_info.toString());
        Log.i("sql", "--------");
        cursor.close();
        return attribute_info;
    }

    public List<Map<String,Integer>> queryAge(SQLiteDatabase db){
        Cursor cursor = db.query("rule", new String[]{"id","age"}, null, null, null, null, null);

        List<Map<String,Integer>> rules = new LinkedList();

        while(cursor.moveToNext()){
            Map<String,Integer> rule = new HashMap();
            rule.put("id",Utils.StringToInt(cursor.getString(cursor.getColumnIndex("id"))) );
            rule.put("age",Utils.StringToInt(cursor.getString(cursor.getColumnIndex("age"))) );
            rules.add(rule);
        }

        System.out.println("------all rule's age------");
        System.out.println(rules);
        System.out.println("--------------------------");

        return rules;
    }

    public void updateAge(SQLiteDatabase db){

        List<Map<String,Integer>> rules = queryAge(db);

        System.out.println("----- update age -------");

        for (int i = 0; i < rules.size(); i++) {
            Map<String,Integer> rule = rules.get(i);
            String id = rule.get("id").toString();
            int age = rule.get("age");
            ContentValues values = new ContentValues();
            age++;
            values.put("age", age);
            db.update("rule", values, "id = ?", new String[]{id});
        }

        queryAge(db);

    }


}
