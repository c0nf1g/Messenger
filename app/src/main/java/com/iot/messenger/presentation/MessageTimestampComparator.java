package com.iot.messenger.presentation;

import com.iot.messenger.presentation.uiData.MessageViewData;

import java.time.LocalDateTime;
import java.util.Comparator;

public class MessageTimestampComparator implements Comparator<MessageViewData> {

    @Override
    public int compare(MessageViewData messageViewData, MessageViewData t1) {
        return LocalDateTime.parse(t1.getTimeStamp()).compareTo(LocalDateTime.parse(messageViewData.getTimeStamp()));
    }
}
