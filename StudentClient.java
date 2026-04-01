import java.util.ArrayList;
import java.util.Date;
public class StudentClient extends StandardClient{
    private Date statusExpiryDate;
    private double monthlyFee=0.0; //should be final?

    public StudentClient(String clientNum, String name, String password, ArrayList<String> accounts,Date statusExpiryDate) {
        super(clientNum, name, password, accounts);
        this.statusExpiryDate=statusExpiryDate;
    }

    public Date getStatusExpiryDate() {
        return statusExpiryDate;
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
