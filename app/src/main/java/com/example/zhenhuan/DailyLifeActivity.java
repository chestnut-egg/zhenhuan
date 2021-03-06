package com.example.zhenhuan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DailyLifeActivity extends AppCompatActivity {



    private DrawerLayout drawerLayout;

    private DailyLife[] dailyLives = {
            new DailyLife(R.drawable.progressbar,"医馆"),
            new DailyLife(R.drawable.progressbar,"客栈"),
            new DailyLife(R.drawable.progressbar,"甜水巷"),
            new DailyLife(R.drawable.progressbar,"寺庙"),
            new DailyLife(R.drawable.progressbar,"武馆")};
    private List<DailyLife> dailyLiveList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_life);

        //去除标题栏
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        initData();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        int spanCount = 1;
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        recyclerView.setLayoutManager(layoutManager);
        DailyAdapter adapter = new DailyAdapter(dailyLiveList);
        recyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new DailyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position,String name) {
                System.out.println("点击了第"+position+"个card name = "+name);
                Toast.makeText(getApplicationContext(),
                        "click: " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(),
                        "long click: " + position, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initData() {
        dailyLiveList.clear();
        for (int i = 0; i < 20; i++) {//随机添加 100 只猫的信息
            Random random = new Random();
            int index = random.nextInt(dailyLives.length);
            dailyLiveList.add(dailyLives[index]);
        }
    }


}
