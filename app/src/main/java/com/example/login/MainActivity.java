package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.util.Hex;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    // TextView
    TextView title;

    // EditText
    EditText emailTF, passwordTF;

    // Button
    Button signIn, createAccountBtn;

    // Firebase
    FirebaseAuth mAuth;

    // Msg index
    int index = 0;

    ArrayList<String> msg = new ArrayList<>();
    ArrayList<Hex> color = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        title = findViewById(R.id.title);

        emailTF = findViewById(R.id.emailTF);
        passwordTF = findViewById(R.id.passwordTF);

        signIn = findViewById(R.id.signIn);
        createAccountBtn = findViewById(R.id.createAccountBtn);

        mAuth = FirebaseAuth.getInstance();

        createAccountBtn.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, CreateAccount.class));
        });

        signIn.setOnClickListener(view -> {
            signin();
        });
    }

    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    private void signin() {
        String email = emailTF.getText().toString();
        String password = passwordTF.getText().toString();

        msg.add("Try Again");
        msg.add("Wrong Again");
        msg.add("Are You High?");
        msg.add("I think you should register");

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(MainActivity.this, "Email or Password cannot be empty", Toast.LENGTH_SHORT).show();
        }else{
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        startActivity(new Intent(MainActivity.this, Home.class));
                    }else {

                        if(index < msg.size() - 1){
                            title.setText(msg.get(index).toString());
                            signIn.setBackgroundColor(Color.rgb(255,0,0));
                            if(index == 3){
                                signIn.setBackgroundColor(getResources().getColor(R.color.teal_700));
                                createAccountBtn.setWidth(createAccountBtn.getWidth() + 150);
                            }
                            index++;
                        }
                        Toast.makeText(MainActivity.this, "ERROR TRY AGAIN", Toast.LENGTH_SHORT).show();
                    }

                }

            });
        }
    }
}