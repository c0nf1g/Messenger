package com.iot.messenger.presentation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.iot.messenger.R;
import com.iot.messenger.presentation.adapters.MessageAdapter;
import com.iot.messenger.presentation.helpers.MessageSender;
import com.iot.messenger.presentation.viewModels.ChatViewModel;

public class ChatFragment extends Fragment {
    private ChatViewModel chatViewModel;
    private final MessageAdapter messageAdapter = new MessageAdapter();
    private EditText editTextMessage;
    private Button sendMessageButton;
    private final MessageSender messageSender = new MessageSender();

    private final View.OnClickListener onClickListener = v -> {
        String messageText = editTextMessage.getText().toString();
        editTextMessage.setText(null);
        messageSender.sendMessage(messageText);
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View chatView = inflater.inflate(R.layout.fragment_chat, container, false);

        initUI(chatView);

        sendMessageButton.setOnClickListener(onClickListener);

        initRecycler(chatView);
        registerViewModel();
        getMessages();

        return chatView;
    }

    private void initUI(View chatView) {
        editTextMessage = chatView.findViewById(R.id.message);
        sendMessageButton = chatView.findViewById(R.id.sendMessage);
    }

    private void registerViewModel() {
        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);
        chatViewModel.firebaseGetMessages();
    }

    private void getMessages() {
        chatViewModel.getMessageList().observe(getViewLifecycleOwner(), response -> {
            messageAdapter.setItems(response);
        });
    }

    private void initRecycler(View chatView) {
        RecyclerView messageList = chatView.findViewById(R.id.messageList);
        messageList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        messageList.setAdapter(messageAdapter);
    }
}