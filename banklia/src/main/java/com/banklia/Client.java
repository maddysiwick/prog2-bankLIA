package com.banklia;
import java.util.ArrayList;
/**
 * abstract class representing a generic client at a bank
 */
abstract public class Client{
    /**
     * randomly generated number to identify a specific client
     */
    protected String clientNum;
    /**
     * name of the client
     */
    protected String name;
    /**
     * client's password
     */
    protected String password;
    /**
     * whether this client has ope
     */
    protected boolean hasChequing;
    /**
     * account numbers associated with this client
     */
    protected ArrayList<String> accounts;

    public Client(String clientNum, String name, String password, ArrayList<String> accounts) {
        this.clientNum = clientNum;
        this.name = name;
        this.password = password;
        this.accounts = accounts;
        hasChequing=false;
    }
    /**
     * add an account under the client
     * @param accountNum
     */
    public void addAccount(Account account) throws MissingChequingAccountException{
        if(account instanceof ChequingAccount){
            hasChequing=true;
            accounts.add(account.getAccountNum());
        }
        else if(hasChequing) accounts.add(account.getAccountNum());
        else{
            throw new MissingChequingAccountException("must have a chequing account before opening an account of another type");
        }
    }

    public String getClientNum() {
        return clientNum;
    }
    public void setClientNum(String clientNum) {
        this.clientNum = clientNum;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public ArrayList<String> getAccounts() {
        return accounts;
    }
    public void setAccounts(ArrayList<String> accounts) {
        this.accounts = accounts;
    }
    public boolean isHasChequing() {
        return hasChequing;
    }
    public void setHasChequing(boolean hasChequing) {
        this.hasChequing = hasChequing;
    }
}