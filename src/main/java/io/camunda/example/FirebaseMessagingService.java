package io.camunda.example;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;

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
            String[] tokenArray = tokens.split(",");

            for (String token : tokenArray) {
                Message msg = Message.builder()
                        .setToken(token)
                        .putData("title", title)
                        .putData("body", data)
                        .build();

                fcm.send(msg);
            }
        }
    }
}
