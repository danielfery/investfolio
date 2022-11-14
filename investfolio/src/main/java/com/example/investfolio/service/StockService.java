package com.example.investfolio.service;

import com.example.investfolio.entity.Stock;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class StockService {
    private static final String COLLECTION_NAME = "Stock";

    public String saveStock(Stock stock) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(stock.getTicker()).set(stock);

        return collectionApiFuture.get().getUpdateTime().toString();
    }


}
