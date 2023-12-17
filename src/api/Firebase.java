package api;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.FileInputStream;
import java.io.IOException;

public class Firebase {
    public Firestore db;

    public Firebase() throws IOException {
        FileInputStream refreshToken = new FileInputStream("secret/firebase.json");
        Dotenv dotenv = Dotenv.load();

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(refreshToken))
                .setDatabaseUrl(dotenv.get("FIREBASE_URL"))
                .setStorageBucket(dotenv.get("FIREBASE_BUCKET_URL"))
                .build();

        FirebaseApp.initializeApp(options);
        this.db = FirestoreClient.getFirestore();


    }
}
