package com.hanson.app.model.accounts;

public class Account {
    private String accountNumber;
    private double currentBalance = 0.00;
    private String accountStatus = "active";

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Account() {

    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
