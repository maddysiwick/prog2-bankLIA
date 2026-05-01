package com.banklia;
import java.util.Date;

abstract public class Account implements Maintainable{
    protected String accountNum;
    protected double balance;
    protected double monthlyFee;
    protected Date dateOpened;

    public Account(String accountNum, double balance, double monthlyFee) {
        this.accountNum = accountNum;
        this.balance = balance;
        this.monthlyFee = monthlyFee;
        this.dateOpened=new Date();
    }

    abstract public void withdraw(double amount) throws InsufficientFundsException;

    public void applyMonthlyFee(){

    }

    public void deposit(double amount){
        balance+=amount;
    }
    public void deposit(double amount,String currency){

    }
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
}
