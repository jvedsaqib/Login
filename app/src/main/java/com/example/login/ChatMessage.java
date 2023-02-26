package com.example.login;

import java.util.Date;

public class ChatMessage {
    private String messageText;
    private String messageUser;

    private String messageUserUid;
    private long messageTime;
    public ChatMessage(String messageText, String messageUser, String messageUserUid) {
        this.messageText = messageText;
        this.messageUser = messageUser;
        this.messageUserUid = messageUserUid;
        // Initialize to current time
        messageTime = new Date().getTime();
    }
    public ChatMessage(){
    }
    public String getMessageText() {
        return messageText;
    }
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
    public String getMessageUser() {
        return messageUser;
    }
    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }
    public long getMessageTime() {
        return messageTime;
    }
    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

    public String getMessageUserUid() {
        return messageUserUid;
    }

    public void setMessageUserUid(String messageUserUid) {
        this.messageUserUid = messageUserUid;
    }
}
