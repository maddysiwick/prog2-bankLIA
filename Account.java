import java.util.Date;

abstract public class Account {
    protected String accountNum;
    protected double balance;
    protected double monthlyFee;

    public Account(String accountNum, double balance, double monthlyFee) {
        this.accountNum = accountNum;
        this.balance = balance;
        this.monthlyFee = monthlyFee;
    }

    abstract public void withdraw(double amount);

    public void deposit(double amount){

    }
    public void deposit(double amount,String currency){

    }
    public void transfer(double amount,String accoountNumber){
        
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

    
}
