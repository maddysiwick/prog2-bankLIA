package com.banklia;
import java.util.Date;
/**
 * An abstract class representing a basic bank account held by a client
 */
abstract public class Account implements Maintainable{
    /**
     * randomly generated number to identify the account
     */
    protected String accountNum;
    /**
     * current account balance
     */
    protected double balance;
    /**
     * monthly fee applied to this account
     */
    protected double monthlyFee;
    /**
     * date that this account was opened
     */
    protected Date dateOpened;
        /**
     * represents the type of account as a string
     */
    protected String accountType;

    public Account(String accountNum) {
        this.accountNum = accountNum;
        this.balance = 0.0;
        this.monthlyFee = 10.0;
        this.dateOpened=new Date();
    }
    /**
     * abstract method representing a withdrawl of money, to be implemented differently by each account type
     * @param amount
     * @throws InsufficientFundsException
     */
    abstract public void withdraw(double amount) throws InsufficientFundsException,InvestmentLockException;
    /**
     * Subtracts the monthly fee from the account
     */
    public void applyMonthlyFee() throws InsufficientFundsException{
        if(balance>=monthlyFee) balance-=monthlyFee;
        else throw new InsufficientFundsException("not enough funds in account #"+accountNum+" for monthly fee");
    }
    /**
     * Adda an amount to rhe balance of an account
     * @param amount
     */
    public void deposit(double amount){
        balance+=amount;
    }
    /**
     * an alternate deposit method allowing the user to deposit in a currency other than CAD, then
     * automatically convert
     * @param amount
     * @param currency
     */
    public void deposit(double amount,String currency){

    }
    /**
     * transfer money from one account to another
     * @param amount
     * @param account
     * @throws InvestmentLockException
     * @throws InsufficientFundsException
     */
    public void transfer (double amount,Account account)throws InvestmentLockException,InsufficientFundsException{
        if(balance>=amount){
            balance-=amount;
            account.deposit(amount);
        }
        else throw new InsufficientFundsException(accountNum);
    }

    public String getAccountNum() {
        return accountNum;
    }
    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public double getMonthlyFee() {
        return monthlyFee;
    }
    public void setMonthlyFee(double monthlyFee) {
        this.monthlyFee = monthlyFee;
    }
    public Date getDateOpened() {
        return dateOpened;
    }
    public void setDateOpened(Date dateOpened) {
        this.dateOpened = dateOpened;
    } 
    public String getAccountType() {
            return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
