package com.hanson.app.model.accounts;

public class SalaryAccount extends CurrentAccount {
    public SalaryAccount(int customerId, String accountNumber) {
        super(customerId, accountNumber);
    }

    public SalaryAccount() {
    }

    @Override
    public int getCustomerId() {
        return super.getCustomerId();
    }

    @Override
    public void setCustomerId(int customerId) {
        super.setCustomerId(customerId);
    }

    @Override
    public String getAccountNumber() {
        return super.getAccountNumber();
    }

    @Override
    public void setAccountNumber(String accountNumber) {
        super.setAccountNumber(accountNumber);
    }


    @Override
    public String getDateCreated() {
        return super.getDateCreated();
    }

    @Override
    public void setDateCreated(String dateCreated) {
        super.setDateCreated(dateCreated);
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
    public String createInsertSql() {
        String sql;
        sql = "insert into customersAccounts(customerId,accountNumber,dateCreated,balance,status) values(";
        sql += "'" + getCustomerId() + "',";
        sql += "'" + getAccountNumber() + "',";
        sql += "'" + getDateCreated() + "',";
        sql += "'" + getCurrentBalance() + "',";
        sql += "'" + getAccountStatus() + "')";
        return sql;
    }

    @Override
    public String createUpdateSql() {
        String sql = "update customersAccounts set ";

        String setColumns = "";
        setColumns += "balance='" + getCurrentBalance() + "'";
        sql += setColumns;
        sql += " where accountNumber='" + getAccountNumber()+"'";
        return sql;
    }
}
