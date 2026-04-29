import java.util.Date;
public class InvestmentAccount extends Account implements InterestBearing{
    private double interestRate=0.05;

    public InvestmentAccount(String accountNum, double balance, double monthlyFee) {
        super(accountNum, balance, monthlyFee);
    }

    public void withdraw(double amount)throws InsufficientFundsException{
        System.out.println("cannot withdraw from an investment acount");
    }
    public void applyInterest(){

    }
    public void transfer(double amount,Account account) throws InvestmentLockException,InsufficientFundsException{
        if(balance>=amount){
            if(new Date().getTime()-dateOpened.getTime()>86400000){
                if(account instanceof ChequingAccount){
                    balance-=amount;
                    account.deposit(amount);
                }
                else System.out.println("can only transfer to chequing accounts from investment accounts");
            }
            else throw new InvestmentLockException();
        }else throw new InsufficientFundsException(accountNum);
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
