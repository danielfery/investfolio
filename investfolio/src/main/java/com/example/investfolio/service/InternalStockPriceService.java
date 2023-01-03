package com.example.investfolio.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

//Dieser InternalStockService dient dazu, dass Objekte von der Yahoo-Finace-Api nicht duplikat angelegt werden müssen.
@Service
public class InternalStockPriceService {
    private HashMap<String, Stock> internalStocks = new HashMap<>();

    //TODO: Exception-Handling, falls ticker nicht existiert
    public double getCurrentPrice(String ticker) throws IOException {
        if (!internalStocks.containsKey(ticker)){
            internalStocks.put(ticker, YahooFinance.get(ticker));
        }

        return internalStocks.get(ticker).getQuote(true).getPrice().doubleValue();
    }


}
