package com.example.zhenhuan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.amitshekhar.DebugDB;
import com.example.zhenhuan.DB.DBInit;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.example.zhenhuan.tool.Utils;

public class MainActivity extends AppCompatActivity {

    private Button buttonNextYear;
    private Button buttonStarLife;
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

        //属性条初始化
        initData(dbInit,db);

        text = (TextView) findViewById(R.id.text);
        buttonNextYear = (Button) findViewById(R.id.buttonNextYear);
        buttonNextYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.append("next\n");
                dbInit.updateAge(db);
//                addRule(db,0,10,30);
            }
        });


        buttonStarLife = (Button) findViewById(R.id.buttonStarLife);
        buttonStarLife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLife(db);
                initData(dbInit,db);
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

    public void startLife(SQLiteDatabase db) {
        Log.i("sql", "------start Life-----");

        Log.i("delete", "truncate table rule & attribute");
        db.execSQL("DELETE FROM sqlite_sequence WHERE name = 'rule';");
        db.execSQL("DELETE FROM sqlite_sequence WHERE name = 'attribute';");

        String name = getName();

        int maxnumber = 0;
        int minnumber = 0;
        int age = 0;

        Log.i("add", "--------add mysqlf--------");
        addRule(db,name,0,0,-1);

        Log.i("add", "--------add mother--------");
        maxnumber = 30;
        minnumber = 16;
        int matherAge = new Random().nextInt(maxnumber-minnumber+1)+minnumber;
        addRule(db,name,0,matherAge,0);

        Log.i("add", "--------add father--------");
        maxnumber = 30;
        minnumber = 16;
        int fatherAge = new Random().nextInt(maxnumber-minnumber+1)+minnumber;
        addRule(db,name,1,fatherAge,1);

        //哥哥姐姐数量
        int num = new Random().nextInt(5);
        //哥哥姐姐最大年龄
        int maxAge = fatherAge;
        if (matherAge < fatherAge)
            maxAge = matherAge;

        if (maxAge > 18){
            //最早16岁生下
            maxAge -= 16;
            for (int i=0;i<num;i++){
                age = new Random().nextInt(maxAge-1+1)+1;
                int sex = new Random().nextInt(2);
                if (sex == 0){
                    Log.i("add", "--------add sister--------");
                    addRule(db,getName(),0,age,2);
                }else{
                    Log.i("add", "--------add brother--------");
                    addRule(db,getName(),1,age,3);
                }
            }
        }
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
        String firstName = firstNames[num];

        String name = familyName + firstName;

        System.out.println("-----getName------");
        System.out.println(name);
        System.out.println("------------------");

        return name;
    }

    public void addRule(SQLiteDatabase db,String name,int sex,int age,int family){
        Log.i("sql", "------add rule-----");

        ContentValues rule_values = new ContentValues();
        rule_values.put("name",name);
        rule_values.put("sex", sex);
        rule_values.put("age",age);
        rule_values.put("family",family);
        rule_values.put("isdead","0" );
        db.insert("rule", null, rule_values);

        Log.i("rule info", rule_values.toString());

        ContentValues attribute_values = new ContentValues();
        attribute_values.put("name",name );
        attribute_values.put("health",(int) (Math.random()*100) );
        attribute_values.put("charm",(int) (Math.random()*100));
        attribute_values.put("knowledge",(int) new Random().nextInt(30-5+1)+5);
        attribute_values.put("talent",(int) (Math.random()*100));
        attribute_values.put("luck",(int) (Math.random()*100));
        db.insert("attribute", null, attribute_values);

        Log.i("attribute info", attribute_values.toString());

        Log.i("sql", "------add rule end-----");
    }

}
