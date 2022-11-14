package com.example.investfolio.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Service
public class FirebaseInitializer {

    public void initDB(){

        FileInputStream serviceAccount =
                null;
        try {
            serviceAccount = new FileInputStream("./serviceAccountKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
//           throw new RuntimeException(e);
        }

    }
}
