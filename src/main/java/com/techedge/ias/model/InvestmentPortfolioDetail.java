package com.techedge.ias.model;

public class InvestmentPortfolioDetail {

    private String customerID;
    private String accountID;
    private String accountName;
    private String ticker;
    private String investment_type;
    private double purchase_price;
    private int number_of_shares;
    private String purchase_date;

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getInvestment_type() {
        return investment_type;
    }

    public void setInvestment_type(String investment_type) {
        this.investment_type = investment_type;
    }

    public double getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(double purchase_price) {
        this.purchase_price = purchase_price;
    }

    public int getNumber_of_shares() {
        return number_of_shares;
    }

    public void setNumber_of_shares(int number_of_shares) {
        this.number_of_shares = number_of_shares;
    }

    public String getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }
}
