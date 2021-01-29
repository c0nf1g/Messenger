package com.iot.messenger.presentation.helpers;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iot.messenger.domain.entity.Message;

public class MessageSender {
    private final String DATABASE_REFERENCE_PATH = "messages";
    private int ID_COUNTER = 0;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference databaseReference = database.getReference(DATABASE_REFERENCE_PATH);
    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public void sendMessage(String messageText) {
        String username = firebaseAuth.getCurrentUser().getEmail();
        Message message = new Message(username, messageText);
        ID_COUNTER++;

        databaseReference.child(Integer.toString(ID_COUNTER)).setValue(message);
    }
}
