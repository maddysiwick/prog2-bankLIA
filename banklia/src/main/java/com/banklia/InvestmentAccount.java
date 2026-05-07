package com.banklia;
import java.util.Date;
/**
 * a child of Account representing an investment bank account
 */
public class InvestmentAccount extends Account implements InterestBearing{
    /**
     * monthly intrest rate applied to this account
     */
    private double interestRate=0.05;

    public InvestmentAccount(String accountNum) {
        super(accountNum);
        accountType="Investment";
    }
    /**
     * method to override the base withdraw method of Account, preventing withdrawls
     * @param amount
     */
    public void withdraw(double amount)throws InsufficientFundsException,InvestmentLockException{
        throw new InvestmentLockException();
    }
    /**
     * method to apply the interest gained each month
     */
    public void applyInterest(){
        balance+=balance*interestRate;
    }
    /**
     * method to transfer money from the investment account to a chequing account, but only if the investment account
     * has been open for a year
     * @param amount 
     * @param account account being transferred to
     * @throws InvestmentLockException
     * @throws InsufficientFundsException
     */
    public void transfer(double amount,Account account) throws InvestmentLockException,InsufficientFundsException{
        if(balance>=amount){
            if(new Date().getTime()-dateOpened.getTime()>86400000){
                if(account instanceof ChequingAccount){
                    balance-=amount;
                    account.deposit(amount);
                }
                else throw new InvestmentLockException();
            }
            else throw new InvestmentLockException();
        }else throw new InsufficientFundsException("insufficient funds in account #"+accountNum+" to transfer");
    }
    public double getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    public String toString(){
        return "investment account #"+accountNum+"   balance: $"+balance;
    }
}
