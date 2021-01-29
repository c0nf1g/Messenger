package com.iot.messenger.presentation.viewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.iot.messenger.R;
import com.iot.messenger.presentation.uiData.MessageViewData;

public class MessageViewHolder extends BaseViewHolder<MessageViewData>{
    private final TextView username;
    private final TextView message;
    private final TextView timeStamp;

    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);

        username = itemView.findViewById(R.id.username);
        message = itemView.findViewById(R.id.message);
        timeStamp = itemView.findViewById(R.id.time);
    }

    @Override
    public void bindTo(MessageViewData data) {
        if (data != null) {
            username.setText(data.getUsername());
            message.setText(data.getMessage());
            timeStamp.setText(data.getTimeStamp());
        }
    }
}
