package com.iot.messenger.presentation.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.iot.messenger.R;
import com.iot.messenger.presentation.ViewModels.ChatViewModel;

public class ChatFragment extends Fragment {
    private ChatViewModel chatViewModel;
    private EditText editTextMessage;
    private Button sendMessageButton;
    private RecyclerView messageList;

    private final View.OnClickListener onClickListener = v -> {
        String messageText = editTextMessage.getText().toString();
        chatViewModel.sendMessage(messageText);
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

        registerViewModel();

        return chatView;
    }

    private void initUI(View chatView) {
        editTextMessage = chatView.findViewById(R.id.message);
        sendMessageButton = chatView.findViewById(R.id.sendMessage);
    }

    private void registerViewModel() {
        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);
    }
}