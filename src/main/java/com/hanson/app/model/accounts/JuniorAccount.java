package com.hanson.app.model.accounts;

import com.hanson.app.utils.Utilities;

import java.util.Date;

public class JuniorAccount extends Account {
    private int parentId;
    private String accountType;
    private String childName;
    private int birthCertificateNumber;
    private String dateCreated;
    private double interest = 0.00;

    public JuniorAccount(String accountNumber, int parentId, String name, int birthCertificateNumber) {
        super(accountNumber);
        this.parentId = parentId;
        this.childName = name;
        this.birthCertificateNumber = birthCertificateNumber;
        this.setDateCreated(new Utilities().dateFormater(new Date()));
    }

    public JuniorAccount() {
        super();
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
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

    @Override
    public String getAccountNumber() {
        return super.getAccountNumber();
    }

    @Override
    public void setAccountNumber(String accountNumber) {
        super.setAccountNumber(accountNumber);
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getBirthCertificateNumber() {
        return birthCertificateNumber;
    }

    public void setBirthCertificateNumber(int birthCertificateNumber) {
        this.birthCertificateNumber = birthCertificateNumber;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String createInsertSql(){
        String sql;
        sql = "insert into juniorAccounts(customerId,accountNumber,childName,birthNo,dateCreated,balance,status) values(";
        sql += "'" + getParentId() + "',";
        sql += "'" + getAccountNumber() + "',";
        sql += "'" + getChildName() + "',";
        sql += "'" + getBirthCertificateNumber() + "',";
        sql += "'" + getCurrentBalance() + "',";
        sql += "'" + getAccountStatus() + "',";
        sql += "'" + getInterest() + "')";
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
