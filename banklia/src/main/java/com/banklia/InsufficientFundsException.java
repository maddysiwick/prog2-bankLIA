package com.banklia;
/**
 * an exception that occurs when there is not enough funds in an account for a given operation
 */
public class InsufficientFundsException extends Exception{
    public InsufficientFundsException(String message) {
        super(message);
    }
    
}
