package com.hanson.app.controller.accounts;

import com.hanson.app.exceptions.InsuficientBalanceException;
import com.hanson.app.interfaces.AccountsControllerI;
import com.hanson.app.model.accounts.SavingsAccount;
import com.hanson.app.utils.DatabaseUtilities;
import com.hanson.app.utils.Utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SavingsAccountsController extends DatabaseUtilities implements AccountsControllerI<SavingsAccount> {
    @Override
    public void deposit(SavingsAccount savingsAccount, double amount) {
        savingsAccount.setCurrentBalance(savingsAccount.getCurrentBalance() + amount);
        System.out.println(execUpdate(savingsAccount.createUpdateSql())?"Deposited successfully":"Failed to deposit money");
    }

    @Override
    public void withdraw(SavingsAccount savingsAccount, double amount) throws InsuficientBalanceException {
        if (savingsAccount.getCurrentBalance() < amount){
            throw new InsuficientBalanceException();
        }
        else {
            savingsAccount.setCurrentBalance(savingsAccount.getCurrentBalance() - amount);
            System.out.println(execUpdate(savingsAccount.createUpdateSql())?"Successfully withdrawn "+amount:"Failed to withdraw");
        }
    }

    @Override
    public void checkBalance(SavingsAccount savingsAccount) {
        System.out.println(savingsAccount.getCurrentBalance()+" for account number "+ savingsAccount.getAccountNumber());
    }

    @Override
    public void transferFund(SavingsAccount savingsAccount, String targetAccountNumber, double amount) {
        try {
            if ((searchAccountNumber(savingsAccount.getAccountNumber()))==-1 && (searchAccountNumber(targetAccountNumber))==-1){
                System.out.println("Entered incorrect account number");
            }
            else {
                savingsAccount.setCurrentBalance(savingsAccount.getCurrentBalance()-amount);
                if (execUpdate(savingsAccount.createUpdateSql())){
                    //transfer fund to target account
                    savingsAccount = fetchAccountInfo(targetAccountNumber);
                    this.deposit(savingsAccount,amount);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private SavingsAccount fetchAccountInfo(String targetAccountNumber) {
        SavingsAccount savingsAccount = new SavingsAccount();
        try {
            ResultSet resultSet = execQuery("select * from customersAccounts where accountNumber='"+targetAccountNumber+"'");
            while (resultSet.next()){
                savingsAccount.setCustomerId(resultSet.getInt(1));
                savingsAccount.setAccountNumber(resultSet.getString(4));
                savingsAccount.setDateCreated(resultSet.getString(5));
                savingsAccount.setCurrentBalance(resultSet.getDouble(6));
                savingsAccount.setAccountStatus(resultSet.getString(7));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return savingsAccount;
    }

    @Override
    public String createAccount(SavingsAccount savingsAccount) {
        try {
            if ((searchAccountNumber(savingsAccount.getAccountNumber()))==-1){
                System.out.println(execUpdate((savingsAccount.createInsertSql()))?"Account created successfully":"Failed to create Account");
            }
            else {
                System.out.println("Account already exists");
            }
            if ((searchAccountNumber(savingsAccount.getAccountNumber()))==-1){
                System.out.println(execUpdate((savingsAccount.createInsertSql()))?"Account created successfully":"Failed to create Account");
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
