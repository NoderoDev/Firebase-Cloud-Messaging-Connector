package io.camunda.connector.firebase.config;
import java.io.*;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;

public class FirebaseConfiguration {

    private final FirebaseMessaging fbm;
    private final InputStream inputStream;
    public FirebaseConfiguration(final InputStream inputStream) throws IOException {
        this.inputStream = inputStream;
        GoogleCredentials gc = googleCredentials();
        FirebaseApp fbApp = firebaseApp(gc);
        this.fbm = firebaseMessaging(fbApp);
    }
    public FirebaseMessaging getFirebaseMessaging() {
        return this.fbm;
    }

    GoogleCredentials googleCredentials(){
        try {
            return GoogleCredentials.fromStream(inputStream);
        }
        catch (Exception ex) {
            throw new RuntimeException("Wrong Firebase credentials format. Unable to create connection");
        }
    }
    FirebaseApp firebaseApp(GoogleCredentials credentials) {
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .build();
        if (!FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.getApps().get(0).delete();
        }
        return FirebaseApp.initializeApp(options);
    }
    FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {
        return FirebaseMessaging.getInstance(firebaseApp);
    }
}