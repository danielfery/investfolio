package com.example.investfolio.entity;

import java.time.LocalDateTime;

//dient zum internen Abspeichern. Hier ist der aktuelle Preis NICHT enthalten.
public class StockLinePerUser {
    private String ticker;
    private String companyName;
    private String boughtAtDateTime;
    private String userId;
    private double priceBuy;

    public StockLinePerUser() {
    }



    public StockLinePerUser(String userId, String ticker) {
        this.userId = userId;
        this.ticker = ticker;

    }

    public StockLinePerUser(String ticker, String companyName, String boughtAtDateTime, String userId, double priceBuy) {
        this.ticker = ticker;
        this.companyName = companyName;
        //this.boughtAtDateTime = boughtAtDateTime;
        this.userId = userId;
        this.priceBuy = priceBuy;

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


    public String getBoughtAtDateTime() {
        return boughtAtDateTime;
    }

    public void setBoughtAtDateTime(String boughtAtDateTime) {
        this.boughtAtDateTime = boughtAtDateTime;
    }

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


}

