package com.banklia;

import java.util.Date;
/**
 * class to hold information that is relevant between sessions, such as the last day the accounts were updated
 * or the last used id to be able to create id numbers in a sequence
 */
public class LoadInfo {
    /**
     * the last time that the monthly fees and interest of all bank accounts was applied
     */
    private Date lastUpdate;
    /**
     * the last id used for clients
     */
    private int clientNum;
    /**
     * the last id used for accounts
     */
    private int accountNum;
    /**
     * the last id used for transactions
     */
    private int transactionNum;

    public LoadInfo(Date lastUpdate,int clientNum,int accountNum,int transactionNum) {
        this.lastUpdate = lastUpdate;
        this.clientNum=clientNum;
        this.accountNum=accountNum;
        this.transactionNum=transactionNum;
    }
    /**
     * returns a new clientNum in a sequence each time called, to avoid duplicates in auto generated ids
     * @return the next clientNum in the sequence
     */
    public String nextClientNum(){
        clientNum++;
        return String.valueOf(clientNum);
    }
    /**
     * returns a new accountNum in a sequence each time called, to avoid duplicates in auto generated ids
     * @return the next accountNum in the sequence
     */
    public String nextAccountNum(){
        accountNum++;
        return String.valueOf(accountNum);
    }
    /**
     * returns a new transactionNum in a sequence each time called, to avoid duplicates in auto generated ids
     * @return the next transactionNum in the sequence
     */
    public String nextTransationNum(){
        transactionNum++;
        return String.valueOf(transactionNum);
    }
    public Date getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    public int getClientNum() {
        return clientNum;
    }
    public void setClientNum(int clientNum) {
        this.clientNum = clientNum;
    }
    public int getAccountNum() {
        return accountNum;
    }
    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }
    public int getTransactionNum() {
        return transactionNum;
    }
    public void setTransactionNum(int transactionNum) {
        this.transactionNum = transactionNum;
    }
}
