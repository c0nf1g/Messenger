package com.iot.messenger.domain.entity;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;

@IgnoreExtraProperties
public class Message {
    private String username;
    private String message;
    private long timeStamp;

    public Message() {}

    public Message(String username, String message) {
        this.username = username;
        this.message = message;
        this.timeStamp = new Date().getTime();
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}
