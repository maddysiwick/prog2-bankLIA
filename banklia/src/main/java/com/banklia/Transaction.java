package com.banklia;
//i should maybe just be using system milis or wtv
import java.util.Date;
public class Transaction {
    /**
     * randomly generated ID identifying a transaction
     */
    private String transactionID;
    /**
     * the type of transaction
     */
    private String type;
    /**
     * the ID of the client involved
     */
    private String clientNum;
    /**
     * the ID of the main account involved
     */
    private String mainAccountNum;
    /**
     * the ID of the second account involved in the case of a transfer
     */
    private String secondaryAccountNum;
    /**
     * the account balance before the transaction
     */
    private double balanceBefore;
    /**
     * the account balance after the transaction
     */
    private double balanceAfter;
    /**
     * the difference in account balance 
     */
    private double difference;
    /**
     * the date of the transaction
     */
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
        difference=balanceAfter-balanceBefore;
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
    public double getDifference() {
        return difference;
    }
    public void setDifference(double difference) {
        this.difference = difference;
    }
}
