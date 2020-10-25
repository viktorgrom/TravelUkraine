package com.example.travelukraine;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    ImageView imageView;
    View v;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.menu_name);
        imageView = itemView.findViewById(R.id.menu_image);
        v = itemView;
    }
}
