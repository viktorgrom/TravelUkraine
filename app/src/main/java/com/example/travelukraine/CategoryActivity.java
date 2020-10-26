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
import com.example.travelukraine.model.Category;
import com.example.travelukraine.model.CategoryItem;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class CategoryActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference DataRef;

    FirebaseRecyclerOptions<Category> options;
    FirebaseRecyclerAdapter<Category, CategoryViewHolder> categoryMenuAdapter;

    TextView txtFullName;

    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        //Init Firebase
        database = FirebaseDatabase.getInstance();
        DataRef = database.getReference("Category");

        //set name for User
        txtFullName = (TextView) findViewById(R.id.txtFullName);
        txtFullName.setText(Common.currenrUser.getName());

        //Load menu
        recycler_menu = (RecyclerView) findViewById(R.id.recycler_category_menu);
        recycler_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layoutManager);

        LoadData();
    }

    private void LoadData() {
        options = new FirebaseRecyclerOptions.Builder<Category>().setQuery(DataRef, Category.class).build();
        categoryMenuAdapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i, @NonNull Category category) {
                categoryViewHolder.tv_title.setText(category.getName());
                Picasso.with(getBaseContext()).load(category.getImage()).into(categoryViewHolder.imageView);
                categoryViewHolder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent cat_menu_intent = new Intent(CategoryActivity.this, Cat_menu.class);
                        cat_menu_intent.putExtra("CategoryId", categoryMenuAdapter.getRef(i).getKey());
                        startActivity(cat_menu_intent);
                    }
                });
            }

            @NonNull
            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_menu_item,parent,false);
                return new CategoryViewHolder(v);
            }
        };
        categoryMenuAdapter.startListening();
        recycler_menu.setAdapter(categoryMenuAdapter);
    }
}