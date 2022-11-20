package com.example.investfolio.service;

import com.example.investfolio.entity.StockLinePerUser;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class StockService {
    /*name of Collection in Firestore*/
    private static final String COLLECTION_NAME = "User";
    public static final String SUB_COLLECTION_NAME = "UserStocks";


    public String saveStockLinePerUser(StockLinePerUser stockLinePerUser, String userId) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(userId).collection(SUB_COLLECTION_NAME).document(userId + stockLinePerUser.getTicker()).set(stockLinePerUser);

        return collectionApiFuture.get().getUpdateTime().toString();
    }



    public List<StockLinePerUser> getStocks(String userId) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference mQuery = dbFirestore.collection(COLLECTION_NAME).document(userId).collection(SUB_COLLECTION_NAME);

        List<StockLinePerUser> returnStocks = new ArrayList<>();
        // asynchronously retrieve multiple documents
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COLLECTION_NAME).document(userId).collection(SUB_COLLECTION_NAME).get();
// future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (DocumentSnapshot document : documents) {
            returnStocks.add(document.toObject(StockLinePerUser.class));

        }
        return returnStocks;
    }

    //TODO: Testen
    public void deleteStock(String userId, String ticker) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference mQuery = dbFirestore.collection(COLLECTION_NAME).document(userId).collection(SUB_COLLECTION_NAME);

        // asynchronously delete a document
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COLLECTION_NAME).document(userId).collection(SUB_COLLECTION_NAME).document(userId + ticker).delete();
    }
}