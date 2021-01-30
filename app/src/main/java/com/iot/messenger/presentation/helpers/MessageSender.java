package com.iot.messenger.presentation.helpers;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iot.messenger.domain.entity.Message;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MessageSender {
    private final String DATABASE_REFERENCE_PATH = "messages";
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference databaseReference = database.getReference(DATABASE_REFERENCE_PATH);
    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public void sendMessage(String messageText) {
        String username = firebaseAuth.getCurrentUser().getEmail();
        Message message = new Message(username, messageText);

        databaseReference.child(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))).setValue(message);
    }
}
