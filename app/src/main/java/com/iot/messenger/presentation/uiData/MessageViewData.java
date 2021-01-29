package com.iot.messenger.presentation.uiData;

public class MessageViewData {
    private String username;
    private String message;
    private String timeStamp;

    public MessageViewData(String username, String message, String timeStamp) {
        this.username = username;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
