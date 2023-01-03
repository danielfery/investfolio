package com.example.investfolio.entity;

//dient nur zum Ausgeben, weil hier zusätzlich der aktuelle Preis enthalten ist.
public class ReturnUserStockLine {
    private String ticker;
    private String companyName;
    private String boughtAtDateTime;
    private String userId;
    private double priceBuy;
    private double currentPrice;
    public ReturnUserStockLine() {
    }



    public ReturnUserStockLine(String userId, String ticker) {
        this.userId = userId;
        this.ticker = ticker;

    }

    public ReturnUserStockLine(String ticker, String companyName, String boughtAtDateTime, String userId, double priceBuy, double currentPrice) {
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

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }
}

