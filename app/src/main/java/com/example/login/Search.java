package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {

    Button btnProfile;
    GridView gridView;
    ArrayList<GridSearch> dataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();

        gridView = findViewById(R.id.gridView);

        btnProfile = findViewById(R.id.btnProfile);

        btnProfile.setOnClickListener(view -> {
            startActivity(new Intent(Search.this, Home.class));
        });

        dataList = new ArrayList<>();

        loadDatainGridView();

    }

    private void loadDatainGridView() {

        FirebaseDatabase.getInstance().getReference("bookData").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                String title = "";
                String imgUrl = "";
                String price = "";


                for (DataSnapshot i : dataSnapshot.getChildren()) {
                    //Log.d("Iterating", i.getKey());
                    for (DataSnapshot iChild : i.getChildren()) {

                        //Log.d("IteratingChild", iChild.getKey());
                        Log.d("ChildKeyValue", iChild.child("title").getValue().toString());
                        Log.d("ChildKeyValue", iChild.child("uid").getValue().toString());
                        Log.d("ChildKeyValue", iChild.child("imgUrl").getValue().toString());

                        dataList.add(new GridSearch(iChild.child("title").getValue().toString(),
                                "",
                                iChild.child("imgUrl").getValue().toString()));
                    }
                }

                GVAdapter adapter = new GVAdapter(Search.this, dataList);
                gridView.setAdapter(adapter);
            }
        });

    }

}