import java.util.ArrayList;

public class CorporateClient extends PremiumClient{
    private String companyName;

    public CorporateClient(String clientNum, String name, String password, ArrayList<String> accounts) {
        super(clientNum, name, password, accounts);
    }

    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
