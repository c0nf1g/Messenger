package com.iot.messenger.presentation.viewModels;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iot.messenger.domain.entity.Message;
import com.iot.messenger.presentation.uiData.MessageViewData;

import java.util.ArrayList;
import java.util.List;

public class ChatViewModel extends ViewModel {
    private final String DATABASE_REFERENCE_PATH = "messages";

    private final MutableLiveData<List<MessageViewData>> messageList = new MutableLiveData<>();
    private final DatabaseReference databaseReference = FirebaseDatabase.getInstance()
            .getReference(DATABASE_REFERENCE_PATH);

    public void firebaseGetMessages() {
        ValueEventListener messagesListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<MessageViewData> result = new ArrayList<>();

                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    MessageViewData messageViewData = itemSnapshot.getValue(MessageViewData.class);
                    result.add(messageViewData);
                }

                messageList.setValue(result);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        databaseReference.addValueEventListener(messagesListener);
    }

    public MutableLiveData<List<MessageViewData>> getMessageList() {
        return messageList;
    }
}
