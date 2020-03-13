package com.example.zhenhuan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
}
