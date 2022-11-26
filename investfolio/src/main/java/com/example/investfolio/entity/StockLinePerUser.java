package com.example.investfolio.entity;

import java.time.LocalDateTime;

public class StockLinePerUser {
    private String ticker;
    private String companyName;
    //private LocalDateTime boughtAtDateTime;
    private String userId;
    private double priceBuy;
    private double currentPrice;
    public StockLinePerUser() {
    }



    public StockLinePerUser(String userId, String ticker) {
        this.userId = userId;
        this.ticker = ticker;

    }

    public StockLinePerUser(String ticker, String companyName, /*LocalDateTime boughtAtDateTime,*/ String userId, double priceBuy, double currentPrice) {
        this.ticker = ticker;
        this.companyName = companyName;
        //this.boughtAtDateTime = boughtAtDateTime;
        this.userId = userId;
        this.priceBuy = priceBuy;
        this.currentPrice = currentPrice;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /*
    public LocalDateTime getBoughtAtDateTime() {
        return boughtAtDateTime;
    }

    public void setBoughtAtDateTime(LocalDateTime boughtAtDateTime) {
        this.boughtAtDateTime = boughtAtDateTime;
    }
*/
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getPriceBuy() {
        return priceBuy;
    }

    public void setPriceBuy(double priceBuy) {
        this.priceBuy = priceBuy;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }
}

