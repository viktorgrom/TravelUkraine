package com.example.travelukraine;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CatViewHolder extends RecyclerView.ViewHolder {

    TextView tv_title, tv_sh_des, tv_lg_des, tv_on_map;
    ImageView imageView;
    View v;

    public CatViewHolder(@NonNull View itemView) {
        super(itemView);

        tv_title = itemView.findViewById(R.id.card_title);
        tv_sh_des = itemView.findViewById(R.id.short_describe_place);
        tv_lg_des = itemView.findViewById(R.id.tv_long_describe);
        imageView = itemView.findViewById(R.id.card_background);
        tv_on_map = itemView.findViewById(R.id.tv_on_map);
        v = itemView;
    }
}
