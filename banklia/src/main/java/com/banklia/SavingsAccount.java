package com.banklia;
/**
 * child of Account which represents a savings bank account
 */
public class SavingsAccount extends Account implements InterestBearing{
    /**
     * monthly interest rate for the account
     */
    private double interestRate=0.02;

    public SavingsAccount(String accountNum, double balance, double monthlyFee) {
        super(accountNum, balance, monthlyFee);
    }
    /**
     * method that allows money to be withdrawn from a savings account
     * @param amount
     * @throws InsufficientFundsException
     */
    public void withdraw(double amount)throws InsufficientFundsException{
        if(amount<=balance){
            balance-=amount;
        }
        else throw new InsufficientFundsException("cannot withdraw $"+amount+"with balance $"+balance);
    }
    public void applyInterest(){
        balance+=balance*interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    public String toString(){
        return "savings account #"+accountNum+"   balance: $"+balance;
    }
}
