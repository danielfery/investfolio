package com.example.investfolio.entity;

import java.time.LocalDateTime;

public class StockLinePerUser {
    public StockLinePerUser() {
    }

    public StockLinePerUser(String ticker, LocalDateTime boughtAtDateTime, String companyName, String userId, int priceBuyCents, int currentPriceCents) {
        this.ticker = ticker;
        this.boughtAtDateTime = boughtAtDateTime;
        this.companyName = companyName;
        this.userId = userId;
        this.priceBuyCents = priceBuyCents;
        this.currentPriceCents = currentPriceCents;
    }

    private String ticker;

    public LocalDateTime getBoughtAtDateTime() {
        return boughtAtDateTime;
    }

    public void setBoughtAtDateTime(LocalDateTime boughtAtDateTime) {
        this.boughtAtDateTime = boughtAtDateTime;
    }

    private LocalDateTime boughtAtDateTime;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPriceBuyCents(int priceBuyCents) {
        this.priceBuyCents = priceBuyCents;
    }

    public void setCurrentPriceCents(int currentPriceCents) {
        this.currentPriceCents = currentPriceCents;
    }

    private String companyName;
    private String userId;
    private int priceBuyCents;

    public String getUserId() {
        return userId;
    }

    public int getPriceBuyCents() {
        return priceBuyCents;
    }

    public int getCurrentPriceCents() {
        return currentPriceCents;
    }

    private int currentPriceCents;
   public StockLinePerUser(String userId, String ticker) {
       this.userId = userId;
        this.ticker = ticker;

    }



    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getTicker() {
        return ticker;
    }
}

