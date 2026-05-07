package com.banklia;
/**
 * a child of Account representing a chequing account
 */
public class ChequingAccount extends Account{

    public ChequingAccount(String accountNum) {
        super(accountNum);
        accountType="Chequing";
    }
    
    /**
     * withdraw money from the chequing account
     * @param amount
     * @throes InsufficientFundsException
     */
    public void withdraw(double amount)throws InsufficientFundsException{
        if(amount<=balance){
            balance-=amount;
        }
        else throw new InsufficientFundsException("cannot withdraw $"+amount+"with balance $"+balance);
    }
    public String toString(){
        return "chequing account #"+accountNum+"   balance: $"+balance;
    }
}
