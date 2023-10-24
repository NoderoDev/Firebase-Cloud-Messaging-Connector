package com.nodero.connector;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import java.util.ArrayList;
import java.util.List;

public class FirebaseMessagingService {

    private final FirebaseMessaging fcm;

    public FirebaseMessagingService(FirebaseMessaging fcm) {
        this.fcm = fcm;
    }

    public void execute(String messageType, String title, String data, String tokens, String topic) throws FirebaseMessagingException {

        if (messageType.equals("topic")) {
            Message msg = Message.builder()
                    .setNotification(Notification.builder()
                            .setBody(data)
                            .setTitle(title)
                            .build())
                    .setTopic(topic)
                    .build();
            fcm.send(msg);
        } else {
            List<Message> messageList = new ArrayList<>();
            String[] tokenArray = tokens.split(",");

            for (String token : tokenArray) {
                Message msg = Message.builder()
                        .setNotification(Notification.builder()
                                .setBody(data)
                                .setTitle(title)
                                .build())
                        .setToken(token)
                        .build();
                messageList.add(msg);
            }
            fcm.sendEach(messageList);
        }
    }
}
