package com.banklia;
import java.util.ArrayList;
//fix the capital L
public class VIPCLient extends PremiumClient{
    private double monthlyFee=0.0;
    private double extraInterest=0.01;

    public VIPCLient(String clientNum, String name, String password, ArrayList<String> accounts) {
        super(clientNum, name, password, accounts);
    }

    public void addAccount(Account account){
        account.setMonthlyFee(monthlyFee);
        if(account instanceof InvestmentAccount)((InvestmentAccount)account).setInterestRate(((InvestmentAccount)account).getInterestRate()+extraInterest);
        if(account instanceof SavingsAccount)((SavingsAccount)account).setInterestRate(((SavingsAccount)account).getInterestRate()+extraInterest);
        accounts.add(account.getAccountNum());
    }

    public double getMonthlyFee() {
        return monthlyFee;
    }
    public void setMonthlyFee(double monthlyFee) {
        this.monthlyFee = monthlyFee;
    }
    public double getExtraInterest() {
        return extraInterest;
    }
    public void setExtraInterest(double extraInterest) {
        this.extraInterest = extraInterest;
    }
}
