package com.hanson.app.interfaces;

import com.hanson.app.exceptions.InsuficientBalanceException;

public interface AccountsControllerI <T>{
    void deposit(T t, double amount);
    void withdraw(T t, double amount) throws InsuficientBalanceException;
    void checkBalance(T t);
    void transferFund(T t, String targetAccountNumber,double amount);
    String createAccount(T t);
}
