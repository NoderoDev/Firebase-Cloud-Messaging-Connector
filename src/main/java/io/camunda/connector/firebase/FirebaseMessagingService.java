package io.camunda.connector.firebase;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;

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
                    .setTopic(topic)
                    .putData("title", title)
                    .putData("body", data)
                    .build();
            fcm.send(msg);
        } else {
            List<Message> messageList = new ArrayList<>();
            String[] tokenArray = tokens.split(",");

            for (String token : tokenArray) {
                Message msg = Message.builder()
                        .setToken(token)
                        .putData("title", title)
                        .putData("body", data)
                        .build();
                messageList.add(msg);
            }
            fcm.sendEach(messageList);
        }
    }
}
