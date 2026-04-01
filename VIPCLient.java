import java.util.ArrayList;

public class VIPCLient extends PremiumClient{
    private double monthlyFee=0.0;
    private double extraInterest=0.01;

    public VIPCLient(String clientNum, String name, String password, ArrayList<String> accounts) {
        super(clientNum, name, password, accounts);
    }

    public void addAccount(String accountNum){
        
    }

    public double getMonthlyFee() {
        return monthlyFee;
    }
    public void setMonthlyFee(double monthlyFee) {
        this.monthlyFee = monthlyFee;
    }
    public double getExtraInterest() {
        return extraInterest;
    }
    public void setExtraInterest(double extraInterest) {
        this.extraInterest = extraInterest;
    }
}
