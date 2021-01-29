package com.iot.messenger.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iot.messenger.R;
import com.iot.messenger.presentation.uiData.MessageViewData;
import com.iot.messenger.presentation.viewHolders.MessageViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {
    private final List<MessageViewData> messageList = new ArrayList<>();

    public void setItems(List<MessageViewData> messageList) {
        this.messageList.clear();
        this.messageList.addAll(messageList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.message_item, viewGroup, false);
        return new MessageViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder viewHolder, int position) {
        viewHolder.bindTo(messageList.get(position));
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}
