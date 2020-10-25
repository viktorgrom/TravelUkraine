package com.example.travelukraine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelukraine.Common.Common;
import com.example.travelukraine.Interface.ItemClickListener;
import com.example.travelukraine.ViewHolder.MenuViewHolder;
import com.example.travelukraine.model.Category;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Category_menu extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference DataRef;

    FirebaseRecyclerOptions <Category> options;
    FirebaseRecyclerAdapter <Category, CategoryViewHolder> catMenuAdapter;

    TextView txtFullName;

    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_menu);



        //Init Firebase
        database = FirebaseDatabase.getInstance();
        DataRef = database.getReference("Category");


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

        options = new FirebaseRecyclerOptions.Builder<Category>().setQuery(DataRef, Category.class).build();
        catMenuAdapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i, @NonNull Category category) {
                categoryViewHolder.textView.setText(category.getName());
                Picasso.with(getBaseContext()).load(category.getImage()).into(categoryViewHolder.imageView);
            }

            @NonNull
            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item,parent,false);
                return new CategoryViewHolder(v);
            }
        };
        catMenuAdapter.startListening();
        recycler_menu.setAdapter(catMenuAdapter);


    }


}