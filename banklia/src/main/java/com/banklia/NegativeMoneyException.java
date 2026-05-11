package com.banklia;

public class NegativeMoneyException extends Exception{
    public NegativeMoneyException(){
        super("Amount cannot be negative");
    }
}
