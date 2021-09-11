package com.hanson.app.controller.accounts;

import com.hanson.app.exceptions.InsuficientBalanceException;
import com.hanson.app.interfaces.AccountsControllerI;
import com.hanson.app.model.accounts.JuniorAccount;
import com.hanson.app.utils.DatabaseUtilities;
import com.hanson.app.utils.Utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JuniorAccountController extends DatabaseUtilities implements AccountsControllerI<JuniorAccount> {
    @Override
    public void deposit(JuniorAccount juniorAccount, double amount) {
        juniorAccount.setCurrentBalance(juniorAccount.getCurrentBalance() + amount);
        System.out.println(execUpdate(juniorAccount.createUpdateSql())?"Deposited successfully":"Failed to deposit money");
    }

    @Override
    public void withdraw(JuniorAccount juniorAccount, double amount) throws InsuficientBalanceException {
        if (juniorAccount.getCurrentBalance() < amount){
            throw new InsuficientBalanceException();
        }
        else {
            juniorAccount.setCurrentBalance(juniorAccount.getCurrentBalance() - amount);
            System.out.println(execUpdate(juniorAccount.createUpdateSql())?"Successfully withdrawn "+amount:"Failed to withdraw");
        }
    }

    @Override
    public void checkBalance(JuniorAccount juniorAccount) {
        System.out.println(juniorAccount.getCurrentBalance()+" for account number "+ juniorAccount.getAccountNumber()+" "+juniorAccount.getAccountType());
    }

    @Override
    public void transferFund(JuniorAccount juniorAccount, String targetAccountNumber, double amount) {
        try {
            if ((searchAccountNumber(juniorAccount.getAccountNumber()))==-1 && (searchAccountNumber(targetAccountNumber))==-1){
                System.out.println("Entered incorrect account number");
            }
            else {
                juniorAccount.setCurrentBalance(juniorAccount.getCurrentBalance()-amount);
                if (execUpdate(juniorAccount.createUpdateSql())){
                    //transfer fund to target account
                    juniorAccount = fetchAccountInfo(targetAccountNumber);
                    this.deposit(juniorAccount,amount);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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

    public JuniorAccount fetchAccountInfo(String targetAccountNumber) {
        JuniorAccount juniorAccount = new JuniorAccount();
        try {
            ResultSet resultSet = execQuery("select * from customersAccounts where accountNumber='"+targetAccountNumber+"'");
            while (resultSet.next()){
                juniorAccount.setParentId(resultSet.getInt(1));
                juniorAccount.setAccountNumber(resultSet.getString(2));
                juniorAccount.setChildName(resultSet.getString(3));
                juniorAccount.setBirthCertificateNumber(resultSet.getInt(4));
                juniorAccount.setCurrentBalance(resultSet.getDouble(5));
                juniorAccount.setAccountStatus(resultSet.getString(6));
                juniorAccount.setCurrentBalance(resultSet.getDouble(7));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return juniorAccount;
    }

    @Override
    public String createAccount(JuniorAccount juniorAccount) {
        try {
            if ((searchAccountNumber(juniorAccount.getAccountNumber()))==-1){
                System.out.println(execUpdate((juniorAccount.createInsertSql()))?"Account created successfully":"Failed to create Account");
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
