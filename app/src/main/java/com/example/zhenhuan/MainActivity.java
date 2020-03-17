package com.example.zhenhuan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.amitshekhar.DebugDB;
import com.example.zhenhuan.DB.DBInit;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.example.zhenhuan.DB.*;
import com.example.zhenhuan.tool.Utils;

public class MainActivity extends AppCompatActivity {

    private Button button_nextyear;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //去除标题栏
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        //显示数据库数据
        DebugDB.getAddressLog();

        final DBInit dbInit = new DBInit(this,1);
        final SQLiteDatabase db = dbInit.getWritableDatabase();

        //增加属性测试数据
//        addTestData(db);

        //属性条初始化
        initData(dbInit,db);

        text = (TextView) findViewById(R.id.text);
        button_nextyear = (Button) findViewById(R.id.button_nextyear);
        button_nextyear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.append("next\n");
                dbInit.updateAge(db);

                text.append(getName());
            }
        });
    }


    public void initData(DBInit dbInit,SQLiteDatabase db){

        Map<String,Integer> attribute_info = dbInit.attribute_info(db,1);

        TextView attribute_name_1 = (TextView) findViewById(R.id.attribute_name_1);
        TextView attribute_name_2 = (TextView) findViewById(R.id.attribute_name_2);
        TextView attribute_name_3 = (TextView) findViewById(R.id.attribute_name_3);
        TextView attribute_name_4 = (TextView) findViewById(R.id.attribute_name_4);
        TextView attribute_name_5 = (TextView) findViewById(R.id.attribute_name_5);
        TextView attribute_value_1 = (TextView) findViewById(R.id.attribute_value_1);
        TextView attribute_value_2 = (TextView) findViewById(R.id.attribute_value_2);
        TextView attribute_value_3 = (TextView) findViewById(R.id.attribute_value_3);
        TextView attribute_value_4 = (TextView) findViewById(R.id.attribute_value_4);
        TextView attribute_value_5 = (TextView) findViewById(R.id.attribute_value_5);
        ProgressBar progress_bar_1 = findViewById(R.id.progress_bar_1);
        ProgressBar progress_bar_2 = findViewById(R.id.progress_bar_2);
        ProgressBar progress_bar_3 = findViewById(R.id.progress_bar_3);
        ProgressBar progress_bar_4 = findViewById(R.id.progress_bar_4);
        ProgressBar progress_bar_5 = findViewById(R.id.progress_bar_5);

        List<TextView> attribute_name_list = new LinkedList();
        attribute_name_list.add(attribute_name_1);
        attribute_name_list.add(attribute_name_2);
        attribute_name_list.add(attribute_name_3);
        attribute_name_list.add(attribute_name_4);
        attribute_name_list.add(attribute_name_5);

        List<TextView> attribute_value_list = new LinkedList();
        attribute_value_list.add(attribute_value_1);
        attribute_value_list.add(attribute_value_2);
        attribute_value_list.add(attribute_value_3);
        attribute_value_list.add(attribute_value_4);
        attribute_value_list.add(attribute_value_5);

        List<ProgressBar> progressbar_list = new LinkedList();
        progressbar_list.add(progress_bar_1);
        progressbar_list.add(progress_bar_2);
        progressbar_list.add(progress_bar_3);
        progressbar_list.add(progress_bar_4);
        progressbar_list.add(progress_bar_5);

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

        for (int i=0;i<progressbar_list.size();i++){
            progressbar_list.get(i).setProgress(Utils.StringToInt(attribute_value_list.get(i).getText().toString()));
        }


    }


    public void addTestData(SQLiteDatabase db) {
        Log.i("sql", "------add_testdata-----");

        ContentValues rule_values = new ContentValues();
        rule_values.put("id", 1);
        rule_values.put("name","111" );
        rule_values.put("age","1" );
        rule_values.put("isdead","0" );
        db.insert("rule", null, rule_values);

        Log.i("sql", rule_values.toString());

        ContentValues attribute_values = new ContentValues();
        attribute_values.put("id", 1);
        attribute_values.put("name","111" );
        attribute_values.put("health",(int) (Math.random()*100) );
        attribute_values.put("charm",(int) (Math.random()*100));
        attribute_values.put("knowledge",(int) (Math.random()*100));
        attribute_values.put("talent",(int) (Math.random()*100));
        attribute_values.put("luck",(int) (Math.random()*100));
        db.insert("attribute", null, attribute_values);

        Log.i("sql", attribute_values.toString());

    }


    public String getName(){
        String[] familyNames = getResources().getString(R.string.familyName).split("、");
        String[] firstNames = getResources().getString(R.string.firstName).split("、");

        int max= familyNames.length;
        int min = 0;
        int num = new Random().nextInt(max-min+1)+min;
        String familyName = familyNames[num];

        max= firstNames.length;
        num = new Random().nextInt(max-min+1)+min;

        String name = String.valueOf(familyName) +  firstNames[num];

        return name;
    }

}
