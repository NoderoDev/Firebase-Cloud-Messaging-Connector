package io.camunda.example.config;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

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

    GoogleCredentials googleCredentials() throws IOException {
        try {
            return GoogleCredentials.fromStream(inputStream);
        }
        catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }
    FirebaseApp firebaseApp(GoogleCredentials credentials) {
        if (FirebaseApp.getApps().isEmpty()){
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(credentials)
                    .build();
            return FirebaseApp.initializeApp(options);
        }else {
            return FirebaseApp.getApps().get(0);
        }
    }
    FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {
        return FirebaseMessaging.getInstance(firebaseApp);
    }
}