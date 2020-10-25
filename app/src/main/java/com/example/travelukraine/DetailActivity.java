package com.example.travelukraine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    private ImageView imageView;
    TextView tvTitle, tvShDescribe, tvLgDescribe;

    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.image_detail);
        tvTitle = findViewById(R.id.title_detail);
        tvShDescribe = findViewById(R.id.descr_detail);
        tvLgDescribe = findViewById(R.id.tv_long_descr_detail);
        ref = FirebaseDatabase.getInstance().getReference().child("Category1");

        String CategoryKey = getIntent().getStringExtra("CategoryKey");

        ref.child(CategoryKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists())
                {
                    String Name = Objects.requireNonNull(snapshot.child("profileName").getValue()).toString();
                    String Image = Objects.requireNonNull(snapshot.child("background").getValue()).toString();

                    Picasso.with(getBaseContext()).load(Image).into(imageView);
                    tvTitle.setText(Name);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}