package com.example.investfolio.controller;

import com.example.investfolio.entity.Stock;
import com.example.investfolio.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("/stock")
    public String saveStock(@RequestBody Stock stock) throws ExecutionException, InterruptedException {

        return stockService.saveStock(stock);
    }

    @GetMapping("/hello")
    public String hello() {
        return " === INVESTFOLIO ===";
    }
}
