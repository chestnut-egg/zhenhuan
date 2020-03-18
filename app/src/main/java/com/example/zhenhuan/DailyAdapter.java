package com.example.zhenhuan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.ViewHolder> {

    private Context context;

    private List<DailyLife> dailyLives = new ArrayList<>();

    public DailyAdapter(List<DailyLife> dailyLives) {
        this.dailyLives = dailyLives;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.daily_life, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DailyLife dailyLife = dailyLives.get(position);
        holder.name.setText(dailyLife.getName());
        Glide.with(context).load(dailyLife.getImgId()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return dailyLives.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView image;
        TextView name;


        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            image = (ImageView) itemView.findViewById(R.id.daily_image);
            name = (TextView) itemView.findViewById(R.id.daily_name);
        }
    }
}
