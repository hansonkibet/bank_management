package com.hanson.app.model.accounts;

import com.hanson.app.utils.Utilities;

import java.util.Date;

public class FixedDepositAccount extends Account {
    private int customerId;
    private double interest=0.00;
    private String dateCreated;

    public FixedDepositAccount(int customerId, String accountNumber) {
        super(accountNumber);
        this.customerId = customerId;
        this.setDateCreated(new Utilities().dateFormater(new Date()));
    }

    public FixedDepositAccount() {
        super();
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String createInsertSql(){
        String sql;
        sql = "insert into fixedDepositAccounts(customerId,accountTypeId,accountType,accountNumber,dateCreated,balance,status) values(";
        sql += "'" + getCustomerId() + "',";
        sql += "'" + getAccountNumber() + "',";
        sql += "'" + getDateCreated() + "',";
        sql += "'" + getCurrentBalance() + "',";
        sql += "'" + getAccountStatus() + "',";
        sql += "'" + getInterest() + "')";
        return sql;
    }
    public String createUpdateSql(){

        String sql = "update fixedDepositAccounts set ";

        String setColumns = "";
        setColumns += "balance='" + getCurrentBalance() + "'";
        sql += setColumns;
        sql += " where accountNumber='" + getAccountNumber()+"'";
        return sql;
    }
}
