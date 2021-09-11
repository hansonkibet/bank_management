package com.hanson.app.exceptions;

public class InsuficientBalanceException extends Exception {
    public InsuficientBalanceException(){
        super("Sorry you don't have enough funds in your account");
    }
}
