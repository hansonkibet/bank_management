package com.hanson.app.controller.accounts;

import com.hanson.app.exceptions.InsuficientBalanceException;
import com.hanson.app.interfaces.AccountsControllerI;
import com.hanson.app.model.accounts.FixedDepositAccount;
import com.hanson.app.utils.DatabaseUtilities;
import com.hanson.app.utils.Utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FixedDepositAccountController extends DatabaseUtilities implements AccountsControllerI<FixedDepositAccount> {
    @Override
    public void deposit(FixedDepositAccount fixedDepositAccount, double amount) {
        fixedDepositAccount.setCurrentBalance(fixedDepositAccount.getCurrentBalance() + amount);
        System.out.println(execUpdate(fixedDepositAccount.createUpdateSql())?"Deposited successfully":"Failed to deposit money");
    }

    @Override
    public void withdraw(FixedDepositAccount fixedDepositAccount, double amount) throws InsuficientBalanceException {
        if (fixedDepositAccount.getCurrentBalance() < amount){
            throw new InsuficientBalanceException();
        }
        else {
            fixedDepositAccount.setCurrentBalance(fixedDepositAccount.getCurrentBalance() - amount);
            System.out.println(execUpdate(fixedDepositAccount.createUpdateSql())?"Successfully withdrawn "+amount:"Failed to withdraw");
        }
    }

    @Override
    public void checkBalance(FixedDepositAccount fixedDepositAccount) {
        System.out.println(fixedDepositAccount.getCurrentBalance()+" for account number "+ fixedDepositAccount.getAccountNumber());
    }

    @Override
    public void transferFund(FixedDepositAccount fixedDepositAccount, String targetAccountNumber, double amount) {
        try {
            if ((searchAccountNumber(fixedDepositAccount.getAccountNumber()))==-1 && (searchAccountNumber(targetAccountNumber))==-1){
                System.out.println("Entered incorrect account number");
            }
            else {
                fixedDepositAccount.setCurrentBalance(fixedDepositAccount.getCurrentBalance()-amount);
                if (execUpdate(fixedDepositAccount.createUpdateSql())){
                    //transfer fund to target account
                    fixedDepositAccount = fetchAccountInfo(targetAccountNumber);
                    this.deposit(fixedDepositAccount,amount);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private int searchAccountNumber(String accountNumber) throws SQLException {
        Utilities utilities = new Utilities();
        ArrayList<String> accountNumbers = new ArrayList<>();
        ResultSet resultSet = execQuery("select accountNumber from fixedDepositAccounts");
        while (resultSet.next()){
            accountNumbers.add(resultSet.getString(2));
        }
        String[] a = new String[accountNumbers.size()];
        for (int i = 0; i < accountNumbers.size(); i++) {
            a[i] = accountNumbers.get(i);
        }
        return utilities.searchString(accountNumber, a);
    }

    public FixedDepositAccount fetchAccountInfo(String targetAccountNumber) {
        FixedDepositAccount fixedDepositAccount = new FixedDepositAccount();
        try {
            ResultSet resultSet = execQuery("select * from fixedDepositAccounts where accountNumber='"+targetAccountNumber+"'");
            while (resultSet.next()){
                fixedDepositAccount.setCustomerId(resultSet.getInt(1));
                fixedDepositAccount.setAccountNumber(resultSet.getString(2));
                fixedDepositAccount.setDateCreated(resultSet.getString(3));
                fixedDepositAccount.setCurrentBalance(resultSet.getDouble(4));
                fixedDepositAccount.setAccountStatus(resultSet.getString(5));
                fixedDepositAccount.setInterest(resultSet.getDouble(6));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return fixedDepositAccount;
    }

    @Override
    public String createAccount(FixedDepositAccount fixedDepositAccount) {
        try {
            if ((searchAccountNumber(fixedDepositAccount.getAccountNumber()))==-1){
                System.out.println(execUpdate((fixedDepositAccount.createInsertSql()))?"Account created successfully":"Failed to create Account");
            }
            else {
                System.out.println("Account already exists");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
