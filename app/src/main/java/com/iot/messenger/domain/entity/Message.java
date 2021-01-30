package com.iot.messenger.domain.entity;

import android.annotation.SuppressLint;

import com.google.firebase.database.IgnoreExtraProperties;

import java.time.LocalDateTime;


@IgnoreExtraProperties
public class Message {
    private final String username;
    private final String message;
    private final String timeStamp;

    @SuppressLint("DefaultLocale")
    public Message(String username, String message) {
        this.username = username;
        this.message = message;
        this.timeStamp = LocalDateTime.now().toString();
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}
