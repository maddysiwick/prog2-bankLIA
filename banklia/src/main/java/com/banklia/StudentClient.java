package com.banklia;
import java.util.ArrayList;
import java.util.Date;
/**
 * a child of StandardClient which represents student bank clients
 */
public class StudentClient extends StandardClient{
    /** 
     * the date on which this clients student status will expire
     */
    private Date statusExpiryDate;
    /**
     * the monthly fee of zero dollars applied to bank accounts under students
     */
    private double monthlyFee=0.0;

    public StudentClient(String clientNum, String name, String password, ArrayList<String> accounts,Date statusExpiryDate) {
        super(clientNum, name, password, accounts);
        this.statusExpiryDate=statusExpiryDate;
    }
    public Date getStatusExpiryDate() {
        return statusExpiryDate;
    }
    /**
     * method overriding the basic addAccount method of Account, adds an account under this student client, setting the monthly fee 
     * of that account to zero
     * @param account
     */
    public void addAccount(Account account){
        account.setMonthlyFee(monthlyFee);
        accounts.add(account.getAccountNum());
    }

    public void setStatusExpiryDate(Date statusExpiryDate) {
        this.statusExpiryDate = statusExpiryDate;
    }

    public double getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(double monthlyFee) {
        this.monthlyFee = monthlyFee;
    }
}
