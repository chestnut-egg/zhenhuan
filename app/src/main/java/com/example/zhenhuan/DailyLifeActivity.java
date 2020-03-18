package com.example.zhenhuan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class DailyLifeActivity extends AppCompatActivity {



    private DrawerLayout drawerLayout;

    private DailyLife[] dailyLives = {new DailyLife(R.drawable.ic_launcher_foreground,"123"),
            new DailyLife(R.drawable.ic_launcher_foreground,"123"),
            new DailyLife(R.drawable.ic_launcher_foreground,"123"),
            new DailyLife(R.drawable.ic_launcher_foreground,"123"),
            new DailyLife(R.drawable.ic_launcher_foreground,"123"),
            new DailyLife(R.drawable.ic_launcher_foreground,"123"),
            new DailyLife(R.drawable.ic_launcher_foreground,"123")};
    private List<DailyLife> catList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_life);
    }
}
