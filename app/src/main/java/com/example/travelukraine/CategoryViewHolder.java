package com.example.travelukraine;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class CategoryViewHolder extends RecyclerView.ViewHolder {

    TextView tv_title;
    ImageView imageView;
    View v;


    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);

        tv_title = itemView.findViewById(R.id.menu_cat_title);
        imageView = itemView.findViewById(R.id.menu_cat_image);
        v = itemView;

    }
}

