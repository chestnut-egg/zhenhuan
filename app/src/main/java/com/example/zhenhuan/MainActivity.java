package com.example.zhenhuan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amitshekhar.DebugDB;
import com.example.zhenhuan.DB.DBInit;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.example.zhenhuan.DB.*;

public class MainActivity extends AppCompatActivity {

    private Button button_nextyear;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //显示数据库数据
        DebugDB.getAddressLog();

        final DBInit dbInit = new DBInit(this,1);
        final SQLiteDatabase db = dbInit.getWritableDatabase();
        add_testdata(db);

        //属性条初始化
        initdata(dbInit,db);

        text = (TextView) findViewById(R.id.text);
        button_nextyear = (Button) findViewById(R.id.button_nextyear);
        button_nextyear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.append("next\n");
            }
        });
    }


    public void initdata(DBInit dbInit,SQLiteDatabase db){

        Map<String,Integer> attribute_info = dbInit.attribute_info(db);

        TextView attribute_name_1 = (TextView) findViewById(R.id.attribute_name_1);
        TextView attribute_name_2 = (TextView) findViewById(R.id.attribute_name_2);
        TextView attribute_name_3 = (TextView) findViewById(R.id.attribute_name_3);
        TextView attribute_value_1 = (TextView) findViewById(R.id.attribute_value_1);
        TextView attribute_value_2 = (TextView) findViewById(R.id.attribute_value_2);
        TextView attribute_value_3 = (TextView) findViewById(R.id.attribute_value_3);

        List attribute_name_list = new LinkedList();
        attribute_name_list.add(attribute_name_1);
        attribute_name_list.add(attribute_name_2);
        attribute_name_list.add(attribute_name_3);

        List attribute_value_list = new LinkedList();
        attribute_value_list.add(attribute_value_1);
        attribute_value_list.add(attribute_value_2);
        attribute_value_list.add(attribute_value_3);

        int count = 0;
        for(Map.Entry<String,Integer> entry: attribute_info.entrySet()){
            String mapKey = entry.getKey();
            int mapValue = entry.getValue();
            TextView attribute_name = (TextView) attribute_name_list.get(count);
            TextView attribute_value = (TextView) attribute_value_list.get(count);
            attribute_name.setText(mapKey);
            attribute_value.setText(String.valueOf(mapValue));
            count++;
        }

    }


    public void add_testdata(SQLiteDatabase db){
        ContentValues values = new ContentValues();

        Map<String,Integer> map_attribute = new HashMap<String,Integer>();
        map_attribute.put("健康",80);
        map_attribute.put("魅力",60);
        map_attribute.put("学识",10);

        Log.i("sql", "add_testdata: ");

        for(Map.Entry<String,Integer> entry: map_attribute.entrySet()){
            String mapKey = entry.getKey();
            int mapValue = entry.getValue();
            Log.i("sql", "insert into attribute:"+mapKey+" "+mapValue);
            values.put("name",mapKey);
            values.put("value",mapValue);
            db.insert("attribute", null, values);
        }


    }


}
