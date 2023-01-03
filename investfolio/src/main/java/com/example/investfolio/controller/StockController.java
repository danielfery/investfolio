package com.example.investfolio.controller;

import com.example.investfolio.entity.ReturnUserStockLine;
import com.example.investfolio.entity.StockLinePerUser;
import com.example.investfolio.service.InternalStockPriceService;
import com.example.investfolio.service.ProfileDataOfUserService;
import com.example.investfolio.service.StockLinePerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api")
@CrossOrigin("*")
public class StockController {

    @Autowired
    private InternalStockPriceService internalStockPriceService;

    @Autowired
    private StockLinePerUserService stockLinePerUserService;

    @Autowired
    private ProfileDataOfUserService profileDataOfUserService;

    public StockController() throws ExecutionException, InterruptedException {
    }

    @PostMapping("/createStock")
    public ResponseEntity saveStock(String userId, String ticker) throws ExecutionException, InterruptedException, IOException {


        //alternativ diese Logik im StockLinePerUserService einfügen
        Stock generalStock = YahooFinance.get(ticker);
        if (generalStock == null) {
            return ResponseEntity.badRequest().build();
        }
        stockLinePerUserService.saveStockLinePerUser(userId, generalStock);

        return ResponseEntity.ok("Aktie angelegt.");

    }

    @GetMapping("/getStocks")
    public List<ReturnUserStockLine> getStocks(String userId) throws ExecutionException, InterruptedException, IOException {
        LinkedList<ReturnUserStockLine> returnList = new LinkedList<>();
        for (StockLinePerUser s : stockLinePerUserService.getStocks(userId)) {
            returnList.add(new ReturnUserStockLine(s.getTicker(), s.getCompanyName(), s.getBoughtAtDateTime(), s.getUserId(), s.getPriceBuy(), internalStockPriceService.getCurrentPrice(s.getTicker())));
        }


        return returnList;
    }

    @DeleteMapping("/deleteStock")
    public void deleteStock(String userId, String ticker){
        stockLinePerUserService.deleteStock(userId, ticker);
    }

    @PostMapping("/createUser")
    public ResponseEntity createUser(String userId, String eMail) throws ExecutionException, InterruptedException {
        //TODO: Fall behandeln, wenn User schon existiert
        return ResponseEntity.ok(profileDataOfUserService.createUser(userId, eMail));
    }

    //TODO: Fehlerbehandlung für diese Methode
    @GetMapping("/getUser")
    public ResponseEntity getUser(String userId) throws ExecutionException, InterruptedException {
        //TODO: Fall behandeln, wenn User nicht existiert
        return ResponseEntity.ok(profileDataOfUserService.getUser(userId));
    }
}
