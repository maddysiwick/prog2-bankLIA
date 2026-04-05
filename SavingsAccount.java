public class SavingsAccount extends Account implements InterestBearing{
    private double interestRate=0.02;

    public SavingsAccount(String accountNum, double balance, double monthlyFee) {
        super(accountNum, balance, monthlyFee);
    }

    public void withdraw(double amount)throws InsufficientFundsException{
        if(amount<=balance){
            balance-=amount;
        }
        else throw new InsufficientFundsException("cannot withdraw $"+amount+"with balance $"+balance);
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
        return "savings account #"+accountNum+"   balance: $"+balance;
    }
}
