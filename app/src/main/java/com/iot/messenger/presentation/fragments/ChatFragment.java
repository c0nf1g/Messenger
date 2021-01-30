package com.iot.messenger.presentation.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iot.messenger.R;
import com.iot.messenger.presentation.adapters.MessageAdapter;
import com.iot.messenger.presentation.helpers.MessageSender;
import com.iot.messenger.presentation.listeners.FragmentsListener;
import com.iot.messenger.presentation.sharedPreferences.SharedPrefs;
import com.iot.messenger.presentation.viewModels.ChatViewModel;

import org.jetbrains.annotations.NotNull;

public class ChatFragment extends Fragment {
    private ChatViewModel chatViewModel;
    private final MessageAdapter messageAdapter = new MessageAdapter();
    private EditText editTextMessage;
    private Button sendMessageButton;
    private Button logoutButton;
    private final MessageSender messageSender = new MessageSender();
    private FragmentsListener onLogoutListener;
    private final SharedPrefs sharedPrefs = new SharedPrefs();

    private final View.OnClickListener onSendMessageClickListener = v -> {

        String messageText = editTextMessage.getText().toString();
        editTextMessage.setText(null);
        if (messageText.isEmpty()) {
            showAlertDialog();
            return;
        }
        messageSender.sendMessage(messageText);
    };
    private final View.OnClickListener onLogoutClickListener = v -> {
        sharedPrefs.removeUser();
        onLogoutListener.onSignInNavClicked();
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);

        try {
            onLogoutListener = (FragmentsListener) context;
        } catch (ClassCastException exception) {
            throw new ClassCastException(context.toString() + " must implement FragmentsListener method");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onLogoutListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View chatView = inflater.inflate(R.layout.fragment_chat, container, false);

        initUI(chatView);

        sendMessageButton.setOnClickListener(onSendMessageClickListener);
        logoutButton.setOnClickListener(onLogoutClickListener);

        initRecycler(chatView);
        sharedPrefs.init(getActivity());
        registerViewModel();
        getMessages();

        return chatView;
    }

    private void initUI(View chatView) {
        editTextMessage = chatView.findViewById(R.id.message);
        sendMessageButton = chatView.findViewById(R.id.sendMessage);
        logoutButton = chatView.findViewById(R.id.logoutButton);
    }

    private void registerViewModel() {
        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);
        chatViewModel.firebaseGetMessages();
    }

    private void getMessages() {
        chatViewModel.getMessageList().observe(getViewLifecycleOwner(), messageAdapter::setItems);
    }

    private void initRecycler(View chatView) {
        RecyclerView messageList = chatView.findViewById(R.id.messageList);
        messageList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        messageList.setAdapter(messageAdapter);
    }


    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.info_title);
        builder.setPositiveButton("OK", (dialog, which) -> {
            dialog.dismiss();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}