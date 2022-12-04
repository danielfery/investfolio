package com.example.investfolio.service;

import com.example.investfolio.entity.ProfileDataOfUser;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.concurrent.ExecutionException;

@Service
public class ProfileDataOfUserService {

    public ProfileDataOfUser createUser(String userId, String eMail) throws ExecutionException, InterruptedException {

        //wenn User schon existiert, return null
        if (profileDataOfUserAlreadyExists(userId)){
            return null;
        }

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ProfileDataOfUser newProfileDataOfUser = new ProfileDataOfUser(userId, eMail);
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection("ProfileDataOfUser").document(userId).set(newProfileDataOfUser);

        return newProfileDataOfUser;
    }

    public ProfileDataOfUser getUser(String userId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef = dbFirestore.collection("ProfileDataOfUser").document(userId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        ProfileDataOfUser returnProfileDataOfUser = null;
        if (document.exists()){
            returnProfileDataOfUser = document.toObject(ProfileDataOfUser.class);
        }

        return returnProfileDataOfUser;
    }

    //TODO: Exception-Handling
    public boolean profileDataOfUserAlreadyExists(String userId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef = dbFirestore.collection("ProfileDataOfUser").document(userId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()){
            return true;
        }

        return false;
    }

}
