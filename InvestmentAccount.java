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
