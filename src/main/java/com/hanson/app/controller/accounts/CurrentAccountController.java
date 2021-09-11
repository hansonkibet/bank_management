package com.hanson.app.controller.accounts;

import com.hanson.app.exceptions.InsuficientBalanceException;
import com.hanson.app.interfaces.AccountsControllerI;
import com.hanson.app.model.accounts.CurrentAccount;
import com.hanson.app.utils.DatabaseUtilities;
import com.hanson.app.utils.Utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author hanson kibet
 */
public class CurrentAccountController extends DatabaseUtilities implements AccountsControllerI<CurrentAccount> {
    @Override
    public void deposit(CurrentAccount currentAccount,double amount) {
        currentAccount.setCurrentBalance(currentAccount.getCurrentBalance() + amount);
        System.out.println(execUpdate(currentAccount.createUpdateSql())?"Deposited successfully":"Failed to deposit money");
    }

    @Override
    public void withdraw(CurrentAccount currentAccount, double amount) throws InsuficientBalanceException {
        if (currentAccount.getCurrentBalance() < amount){
            throw new InsuficientBalanceException();
        }
        else {
            currentAccount.setCurrentBalance(currentAccount.getCurrentBalance() - amount);
            System.out.println(execUpdate(currentAccount.createUpdateSql())?"Successfully withdrawn "+amount:"Failed to withdraw");
        }
    }

    @Override
    public void checkBalance(CurrentAccount currentAccount) {
        System.out.println(currentAccount.getCurrentBalance()+" for account number "+ currentAccount.getAccountNumber());
    }

    @Override
    public void transferFund(CurrentAccount currentAccount, String targetAccountNumber,double amount) {
        try {
            if ((searchAccountNumber(currentAccount.getAccountNumber()))==-1 && (searchAccountNumber(targetAccountNumber))==-1){
                System.out.println("Entered incorrect account number");
            }
            else {
                currentAccount.setCurrentBalance(currentAccount.getCurrentBalance()-amount);
                if (execUpdate(currentAccount.createUpdateSql())){
                    //transfer fund to target account
                    currentAccount = fetchAccountInfo(targetAccountNumber);
                    this.deposit(currentAccount,amount);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String createAccount(CurrentAccount currentAccount) {
        String message = "";
        try {
            if ((searchAccountNumber(currentAccount.getAccountNumber()))==-1){
                if(execUpdate((currentAccount.createInsertSql()))){
                    message = "Account created successfully";
                }
                else{
                    message = "Failed to create Account";
                }
            }
            else {
                message ="Account already exists";
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return "failed to create";
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
    public CurrentAccount fetchAccountInfo(String accountNumber){
        CurrentAccount currentAccount = new CurrentAccount();
        try {
            ResultSet resultSet = execQuery("select * from customersAccounts where accountNumber='"+accountNumber+"'");
            while (resultSet.next()){
                currentAccount.setCustomerId(resultSet.getInt(1));
                currentAccount.setAccountNumber(resultSet.getString(4));
                currentAccount.setDateCreated(resultSet.getString(5));
                currentAccount.setCurrentBalance(resultSet.getDouble(6));
                currentAccount.setAccountStatus(resultSet.getString(7));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return currentAccount;
    }
}
