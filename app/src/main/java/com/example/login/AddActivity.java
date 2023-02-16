package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

class Information{
    private String name;
    private String email;
    private String course;

    public Information() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}

public class AddActivity extends AppCompatActivity {

    // EditText
    EditText etName, etEmail, etCourse;

    // Button
    Button submitBtn;

    // Object
    Information ob;

    // Firebase
    FirebaseDatabase db;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().hide();

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etCourse = findViewById(R.id.etCourse);

        db = FirebaseDatabase.getInstance();

        submitBtn = findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(view -> {
            addInfo();
        });

    }

    private void addInfo() {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String course = etCourse.getText().toString();

        ob = new Information();

        ob.setName(name);
        ob.setEmail(email);
        ob.setCourse(course);

        dbRef = db.getReference().child("Information");

        dbRef.child(ob.getName()).setValue(ob);

    }
}