package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {

    // buttons
    Button signOut, showData, chat, addBook;
    FloatingActionButton fab;

    // TextView
    TextView tvUser;

    // Firebase
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        getSupportActionBar().hide();

        tvUser = findViewById(R.id.tvUser);

        mAuth = FirebaseAuth.getInstance();

        signOut = findViewById(R.id.signOut);
        showData = findViewById(R.id.showData);
        chat = findViewById(R.id.chat);
        addBook = findViewById(R.id.addBook);
        fab = findViewById(R.id.fab);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(Home.this, MainActivity.class));
            }
        });

        fab.setOnClickListener(view -> {
            startActivity(new Intent(Home.this, AddActivity.class));
        });

        showData.setOnClickListener(view -> {
            startActivity(new Intent(Home.this, Search.class));
        });

        chat.setOnClickListener(view ->{
            startActivity(new Intent(Home.this, Chat.class));
        });

        addBook.setOnClickListener(view -> {
            startActivity(new Intent(Home.this, UploadData.class));
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null){
            startActivity(new Intent(Home.this, MainActivity.class));
        } else{

            FirebaseDatabase.getInstance().getReference("users/"+user.getUid()+"/user").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    tvUser.setText(snapshot.getValue().toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }
}
