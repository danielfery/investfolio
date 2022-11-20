package com.example.investfolio.controller;

import com.example.investfolio.entity.StockLinePerUser;
import com.example.investfolio.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("/createStock")
    public ResponseEntity saveStock(String userId, String ticker) throws ExecutionException, InterruptedException {

        //Todo: Hier muss dann mithilfe der Yahoo-Finance-Api eine Abfrage der restlichen Daten erfolgen
        //alternativ diese Logik im StockService einfügen
        StockLinePerUser stockLinePerUserToCreate = new StockLinePerUser(userId, ticker);
        stockService.saveStockLinePerUser(stockLinePerUserToCreate, userId);

        return ResponseEntity.ok(stockLinePerUserToCreate);

    }

    @GetMapping("/getStocks")
    public List<StockLinePerUser> getStocks(String userId) throws ExecutionException, InterruptedException {
        return stockService.getStocks(userId);
    }

    @DeleteMapping("/deleteStock")
    public void deleteStock(String userId, String ticker){
        stockService.deleteStock(userId, ticker);
    }


}
