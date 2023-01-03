package com.example.investfolio.service;

import com.example.investfolio.entity.StockLinePerUser;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class StockLinePerUserService {
    /*name of Collection in Firestore*/

    @Autowired
    private InternalStockPriceService internalStockPriceService;


    private static final String COLLECTION_NAME = "User";
    public static final String SUB_COLLECTION_NAME = "UserStocks";

    public StockLinePerUserService() {
    }


    public String saveStockLinePerUser(String userId, Stock generalStock) throws ExecutionException, InterruptedException, IOException {

        Firestore dbFirestore = FirestoreClient.getFirestore();

        //Todo: Prüfen, ob ticker valide ist

        //wenn der User die aktie schon hat, nichts machen
        //Todo: anderen http statuscode zurückgeben
        if (stockLinePerUserAlreadyExists(userId, generalStock.getSymbol())) {
            return "User hat Aktie bereits";
        }


        StockLinePerUser stockLinePerUser = new StockLinePerUser(generalStock.getSymbol(), generalStock.getName(), java.time.LocalDateTime.now().toString(), userId, internalStockPriceService.getCurrentPrice(generalStock.getSymbol()));


        //speichern der StockLinePerUser
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(userId).collection(SUB_COLLECTION_NAME).document(userId + stockLinePerUser.getTicker()).set(stockLinePerUser);

        return collectionApiFuture.get().getUpdateTime().toString();
    }



    public List<StockLinePerUser> getStocks(String userId) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();

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

    private boolean stockLinePerUserAlreadyExists(String userId, String ticker) throws ExecutionException, InterruptedException {
        for (StockLinePerUser s : getStocks(userId)){
            if (s.getTicker().equals(ticker)){
                return true;
            }
        }

        return false;
    }



}