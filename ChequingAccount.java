public class ChequingAccount extends Account{

    public ChequingAccount(String accountNum, double balance, double monthlyFee) {
        super(accountNum, balance, monthlyFee);
    }
    
    public void withdraw(double amount)throws InsufficientFundsException{
        if(amount<=balance){
            balance-=amount;
        }
        else throw new InsufficientFundsException("cannot withdraw $"+amount+"with balance $"+balance);
    }

    public String toString(){
        return "chequing account #"+accountNum+"   balance: $"+balance;
    }
}
