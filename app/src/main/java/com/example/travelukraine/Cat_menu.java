package com.example.travelukraine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.travelukraine.Common.Common;
import com.example.travelukraine.model.CategoryItem;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Cat_menu extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference DataRef;

    FirebaseRecyclerOptions<CategoryItem> options;
    FirebaseRecyclerAdapter<CategoryItem, CatViewHolder> catMenuAdapter;

    TextView txtFullName;

    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_menu);

        //Init Firebase
        database = FirebaseDatabase.getInstance();
        DataRef = database.getReference("Category1");


        //set name for User
        txtFullName = (TextView) findViewById(R.id.txtFullName);
        txtFullName.setText(Common.currenrUser.getName());

        //Load menu
        recycler_menu = (RecyclerView) findViewById(R.id.rv_category);
        recycler_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layoutManager);

        LoadData();
    }

    private void LoadData() {
        options = new FirebaseRecyclerOptions.Builder<CategoryItem>().setQuery(DataRef, CategoryItem.class).build();
        catMenuAdapter = new FirebaseRecyclerAdapter<CategoryItem, CatViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CatViewHolder categoryViewHolder, int i, @NonNull CategoryItem category) {
                categoryViewHolder.tv_title.setText(category.getProfileName());
                Picasso.with(getBaseContext()).load(category.getBackground()).into(categoryViewHolder.imageView);
                categoryViewHolder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent detailIntent = new Intent(Cat_menu.this, DetailActivity.class);
                        detailIntent.putExtra("CategoryKey", getRef(i).getKey());
                        startActivity(detailIntent);
                    }
                });
            }

            @NonNull
            @Override
            public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);
                return new CatViewHolder(v);
            }
        };
        catMenuAdapter.startListening();
        recycler_menu.setAdapter(catMenuAdapter);
    }
}