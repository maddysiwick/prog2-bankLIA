package com.banklia;
import java.util.ArrayList;
/**
 * child of the PremiumClient class, representing a corporate client of a bank
 */

public class CorporateClient extends PremiumClient{
    /**
     * name of the company associated to this CorporateClient
     */
    private String companyName;

    public CorporateClient(String clientNum, String name, String password, ArrayList<String> accounts,String companyName) {
        super(clientNum, name, password, accounts);
        this.companyName=companyName;
    }

    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
