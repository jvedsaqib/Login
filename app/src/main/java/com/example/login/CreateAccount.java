package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

class userInfo{

    private String user;
    private String email;
    private String uid;
    public userInfo() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}

public class CreateAccount extends AppCompatActivity {
    // EditTexts
    EditText addName, addEmail, addPassword;

    // Buttons
    Button registerBtn;

    // Firebase
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_create);
        getSupportActionBar().hide();

        addName = findViewById(R.id.addName);
        addEmail = findViewById(R.id.addEmail);
        addPassword = findViewById(R.id.addPassword);

        registerBtn = findViewById(R.id.registerBtn);

        mAuth = FirebaseAuth.getInstance();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
    }

    private void addUser(){
        String user = addName.getText().toString();
        String email = addEmail.getText().toString();
        String password = addPassword.getText().toString();

        userInfo ob = new userInfo();

        ob.setUser(user);
        ob.setEmail(email);

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(CreateAccount.this, "Email or Password cannot be empty", Toast.LENGTH_SHORT).show();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        String uid = mAuth.getCurrentUser().getUid();
                        ob.setUid(uid);
                        FirebaseDatabase.getInstance().getReference("users").child(ob.getUid()).setValue(ob);
                        Toast.makeText(CreateAccount.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(CreateAccount.this, MainActivity.class));
                    }else{
                        Toast.makeText(CreateAccount.this, "Registration Failed "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
