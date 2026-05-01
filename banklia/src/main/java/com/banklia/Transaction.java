package com.banklia;
//i should maybe just be using system milis or wtv
import java.util.Date;
public class Transaction {
    private String transactionID;
    private String type;
    private String clientNum;
    private String mainAccountNum;
    private String secondaryAccountNum;
    private double balanceBefore;
    private double balanceAfter;
    private Date TransactionDate;

    public Transaction(String transactionID,String type, String clientNum, String mainAccountNum, String secondaryAccountNum, double balanceBefore,
            double balanceAfter) {
        this.transactionID=transactionID;
        this.type = type;
        this.clientNum=clientNum;
        this.mainAccountNum = mainAccountNum;
        this.secondaryAccountNum = secondaryAccountNum;
        this.balanceBefore = balanceBefore;
        this.balanceAfter = balanceAfter;
        this.TransactionDate=new Date();
    }

    public String getTransactionID() {
        return transactionID;
    }
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getMainAccountNum() {
        return mainAccountNum;
    }
    public void setMainAccountNum(String mainAccountNum) {
        this.mainAccountNum = mainAccountNum;
    }
    public String getSecondaryAccountNum() {
        return secondaryAccountNum;
    }
    public void setSecondaryAccountNum(String secondaryAccountNum) {
        this.secondaryAccountNum = secondaryAccountNum;
    }
    public double getBalanceBefore() {
        return balanceBefore;
    }
    public void setBalanceBefore(double balanceBefore) {
        this.balanceBefore = balanceBefore;
    }
    public double getBalanceAfter() {
        return balanceAfter;
    }
    public void setBalanceAfter(double balanceAfter) {
        this.balanceAfter = balanceAfter;
    }
    public Date getTransactionDate() {
        return TransactionDate;
    }
    public void setTransactionDate(Date transactionDate) {
        TransactionDate = transactionDate;
    }
    public String getClientNum() {
        return clientNum;
    }

    public void setClientNum(String clientNum) {
        this.clientNum = clientNum;
    }
}
