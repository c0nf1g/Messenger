package com.iot.messenger.presentation.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iot.messenger.domain.entity.Message;

public class ChatViewModel extends ViewModel {
    private final MutableLiveData<Boolean> isMessageWasWritten = new MutableLiveData<>();
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference databaseReference = database.getReference("messages");
    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private Message message;

    public void sendMessage(String messageText) {
        String username = getCurrentUser().getEmail();
        message = new Message(username, messageText);

        databaseReference.setValue(message);
    }

    public FirebaseUser getCurrentUser() {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        return currentUser;
    }
}
