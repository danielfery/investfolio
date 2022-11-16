package com.example.investfolio.entity;

public class Stock{
    private String ticker;

/*    public Stock(String ticker, String companyName) {
        this.ticker = ticker;
        this.companyName = companyName;
    }*/

    private String companyName;

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

