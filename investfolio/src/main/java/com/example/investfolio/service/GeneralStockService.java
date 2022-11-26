package com.example.investfolio.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class GeneralStockService {


    private Map<String, Stock> generalStocks;
    public static final String GENERAL_STOCKS_COLLECTION = "GeneralStocks";
    public static final String GENERAL_STOCKS_DOCUMENT = "Map";
    public GeneralStockService() {




    }

    private void loadMapWithStocks() {
        if (generalStocks == null) {
            generalStocks = new HashMap<String, Stock>();
        }
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(GENERAL_STOCKS_COLLECTION).get();
        List<QueryDocumentSnapshot> documents = null;
        try {
            documents = future.get().getDocuments();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        for (DocumentSnapshot document : documents) {
            Stock tempGeneralStock = document.toObject(Stock.class);
            generalStocks.put(tempGeneralStock.getSymbol(), tempGeneralStock);
        }
    }

    private void addNewGeneralStock(String ticker) throws IOException, ExecutionException, InterruptedException {

        Stock stockToAdd = YahooFinance.get(ticker);
        if (stockToAdd != null) {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(GENERAL_STOCKS_COLLECTION).document(ticker).set(stockToAdd);

        }
               loadMapWithStocks();


    }

    public Stock getOrAddGeneralStock(String ticker) throws IOException, ExecutionException, InterruptedException {
        loadMapWithStocks();

        if (generalStocks.get(ticker) == null){
            addNewGeneralStock(ticker);
        }

        return generalStocks.get(ticker);
    }
}
