package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {

    // buttons
    Button signOut, showData;
    FloatingActionButton fab;

    // Firebase
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();

        signOut = findViewById(R.id.signOut);
        showData = findViewById(R.id.showData);
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
            startActivity(new Intent(Home.this, ShowRecords.class));
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null){
            startActivity(new Intent(Home.this, MainActivity.class));
        }

    }
}
