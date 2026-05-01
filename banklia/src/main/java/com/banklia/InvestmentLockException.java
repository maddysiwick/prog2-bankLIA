package com.banklia;

public class InvestmentLockException extends Exception{
    public InvestmentLockException(){
        super("transfer out of thid account is frestricted right noe=w");
    }
}
