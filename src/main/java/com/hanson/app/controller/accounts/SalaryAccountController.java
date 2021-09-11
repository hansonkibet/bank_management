package com.hanson.app.controller.accounts;

import com.hanson.app.exceptions.InsuficientBalanceException;
import com.hanson.app.interfaces.AccountsControllerI;
import com.hanson.app.model.accounts.SalaryAccount;
import com.hanson.app.utils.DatabaseUtilities;
import com.hanson.app.utils.Utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryAccountController extends DatabaseUtilities implements AccountsControllerI<SalaryAccount> {
    @Override
    public void deposit(SalaryAccount salaryAccount, double amount) {
        salaryAccount.setCurrentBalance(salaryAccount.getCurrentBalance() + amount);
        System.out.println(execUpdate(salaryAccount.createUpdateSql())?"Deposited successfully":"Failed to deposit money");
    }

    @Override
    public void withdraw(SalaryAccount salaryAccount, double amount) throws InsuficientBalanceException {
        if (salaryAccount.getCurrentBalance() < amount){
            throw new InsuficientBalanceException();
        }
        else {
            salaryAccount.setCurrentBalance(salaryAccount.getCurrentBalance() - amount);
            System.out.println(execUpdate(salaryAccount.createUpdateSql())?"Successfully withdrawn "+amount:"Failed to withdraw");
        }
    }

    @Override
    public void checkBalance(SalaryAccount salaryAccount) {
        System.out.println(salaryAccount.getCurrentBalance()+" for account number "+ salaryAccount.getAccountNumber());
    }

    @Override
    public void transferFund(SalaryAccount salaryAccount, String targetAccountNumber, double amount) {
        try {
            if ((searchAccountNumber(salaryAccount.getAccountNumber()))==-1 && (searchAccountNumber(targetAccountNumber))==-1){
                System.out.println("Entered incorrect account number");
            }
            else {
                salaryAccount.setCurrentBalance(salaryAccount.getCurrentBalance()-amount);
                if (execUpdate(salaryAccount.createUpdateSql())){
                    //transfer fund to target account
                    salaryAccount = fetchAccountInfo(targetAccountNumber);
                    this.deposit(salaryAccount,amount);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private SalaryAccount fetchAccountInfo(String targetAccountNumber) {
        SalaryAccount salaryAccount = new SalaryAccount();
        try {
            ResultSet resultSet = execQuery("select * from customersAccounts where accountNumber='"+targetAccountNumber+"'");
            while (resultSet.next()){
                salaryAccount.setCustomerId(resultSet.getInt(1));
                salaryAccount.setAccountNumber(resultSet.getString(4));
                salaryAccount.setDateCreated(resultSet.getString(5));
                salaryAccount.setCurrentBalance(resultSet.getDouble(6));
                salaryAccount.setAccountStatus(resultSet.getString(7));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return salaryAccount;
    }

    @Override
    public String createAccount(SalaryAccount salaryAccount) {
        try {
            if ((searchAccountNumber(salaryAccount.getAccountNumber()))==-1){
                System.out.println(execUpdate((salaryAccount.createInsertSql()))?"Account created successfully":"Failed to create Account");
            }
            else {
                System.out.println("Account already exists");
            }
            if ((searchAccountNumber(salaryAccount.getAccountNumber()))==-1){
                System.out.println(execUpdate((salaryAccount.createInsertSql()))?"Account created successfully":"Failed to create Account");
            }
            else {
                System.out.println("Account already exists");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private int searchAccountNumber(String accountNumber) throws SQLException {
        Utilities utilities = new Utilities();
        ArrayList<String> accountNumbers = new ArrayList<>();
        ResultSet resultSet = execQuery("select accountNumber from customersAccounts");
        while (resultSet.next()){
            accountNumbers.add(resultSet.getString(4));
        }
        String[] a = new String[accountNumbers.size()];
        for (int i = 0; i < accountNumbers.size(); i++) {
            a[i] = accountNumbers.get(i);
        }
        return utilities.searchString(accountNumber, a);
    }
}
