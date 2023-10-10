package io.camunda.example;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;

public class FirebaseMessagingService {

    private final FirebaseMessaging fcm;

    public FirebaseMessagingService(FirebaseMessaging fcm) {
        this.fcm = fcm;
    }

    public String execute() throws FirebaseMessagingException {
        Message msg = Message.builder()
                .setToken("d-RBQz67QUqfcvT9EfOINt:APA91bEHLxqQX-EZf9tbLdfhnqX1u-6DxhdT11KMszxAbUbLD87WAEP6rD9ZJQcJsTZ_odikKzwwR6_BEY3A8lYAP-vWQ8P_XuZFCaJ0bIsSwFTXqXoJR3QqaksEgX2TlX57s-us0IF3")
                .putData("body", "Please work")
                .build();

        String id = fcm.send(msg);

        return id;
    }
}
