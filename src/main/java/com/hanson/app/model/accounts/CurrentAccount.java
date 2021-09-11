package com.hanson.app.model.accounts;

import com.hanson.app.utils.Utilities;

import java.util.Date;

public class CurrentAccount extends Account {
    private int customerId;
    private String dateCreated;

    public CurrentAccount(int customerId, String accountNumber) {
        super(accountNumber);
        this.customerId = customerId;
        this.setDateCreated(new Utilities().dateFormater(new Date()));
    }


    public CurrentAccount() {
        super();
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String getAccountNumber() {
        return super.getAccountNumber();
    }

    @Override
    public void setAccountNumber(String accountNumber) {
        super.setAccountNumber(accountNumber);
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public double getCurrentBalance() {
        return super.getCurrentBalance();
    }

    @Override
    public void setCurrentBalance(double currentBalance) {
        super.setCurrentBalance(currentBalance);
    }

    @Override
    public String getAccountStatus() {
        return super.getAccountStatus();
    }

    @Override
    public void setAccountStatus(String accountStatus) {
        super.setAccountStatus(accountStatus);
    }

    public String createInsertSql(){
        String sql;
        sql = "insert into customersAccounts(customerId,accountNumber,dateCreated,balance,status) values(";
        sql += "'" + getCustomerId() + "',";
        sql += "'" + getAccountNumber() + "',";
        sql += "'" + getDateCreated() + "',";
        sql += "'" + getCurrentBalance() + "',";
        sql += "'" + getAccountStatus() + "')";
        return sql;
    }
    public String createUpdateSql(){

        String sql = "update customersAccounts set ";

        String setColumns = "";
        setColumns += "balance='" + getCurrentBalance() + "'";
        sql += setColumns;
        sql += " where accountNumber='" + getAccountNumber()+"'";
        return sql;
    }
}
