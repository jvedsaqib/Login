package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Chat extends AppCompatActivity {

    // Button
    FloatingActionButton sendFab;

    // EditText
    EditText inputMsg;

    // Firebase
    FirebaseListAdapter<ChatMessage> adapter;

    userInfo ob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getSupportActionBar().hide();
        displayChatMessages();

        sendFab = findViewById(R.id.sendFab);

        sendFab.setOnClickListener(view -> {
            inputMsg = findViewById(R.id.inputMsg);

            FirebaseDatabase.getInstance().getReference().child("Chat").push().setValue(
                    new ChatMessage(inputMsg.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getEmail())
            );

            inputMsg.setText("");
        });

    }

    private void displayChatMessages() {
        ListView msgList = findViewById(R.id.msgList);

        adapter = new FirebaseListAdapter<ChatMessage>(
                this, ChatMessage.class, R.layout.message, FirebaseDatabase.getInstance().getReference().child("Chat")) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                TextView tvUsername = v.findViewById(R.id.tvUsername);
                TextView tvMessage = v.findViewById(R.id.tvMessage);
                TextView tvTime = v.findViewById(R.id.tvTime);

                // Set their text
                tvMessage.setText(model.getMessageText());

                tvUsername.setText(model.getMessageUser());

                // Format the date before showing it
                tvTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));

            }
        };
        msgList.setAdapter(adapter);
    }
}