package com.banklia;
import java.util.ArrayList;
/**
 * child of Client, represents a basic client at a bank
 */
public class IndividualClient extends StandardClient{

    public IndividualClient(String clientNum, String name, String password, ArrayList<String> accounts) {
        super(clientNum, name, password, accounts);
    }
    
}
