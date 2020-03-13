package com.example.zhenhuan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button button_nextyear;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.text);
        button_nextyear = (Button) findViewById(R.id.button_nextyear);
        button_nextyear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.append("next\n");
            }
        });
    }


    public void initdata(){

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

    }


}
