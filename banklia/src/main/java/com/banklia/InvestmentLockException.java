package com.banklia;
/**
 * exception that is thrown if a user attempts to transfer money out of an investment account before one year
 */
public class InvestmentLockException extends Exception{
    public InvestmentLockException(){
        super("transfer out of this account is restricted right now");
    }
}
