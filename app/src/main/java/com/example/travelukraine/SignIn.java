package com.example.travelukraine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travelukraine.Common.Common;
import com.example.travelukraine.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    EditText edtPhone, edtPassword;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        btnSignIn = (Button) findViewById(R.id.btbSignIn);

        //Init Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table_user = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                mDialog.setMessage("Будь ласка зачекайте..");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        //Check if user not exist in database
                        if (snapshot.child(edtPhone.getText().toString()).exists()) {

                            //Get user info
                            mDialog.dismiss();
                            User user = snapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(edtPassword.getText().toString())) {
                                Toast.makeText(SignIn.this, "Вхід виконано", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent categotyIntent = new Intent(SignIn.this, Cat_menu.class);
                                Common.currenrUser = user;
                                startActivity(categotyIntent);
                                finish();
                            }
                        } else {
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this, "Користувача не існує, зареєструйтесь будь ласка", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}