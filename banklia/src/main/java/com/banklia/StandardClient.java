package com.banklia;
import java.util.ArrayList;
/**
 * a child of Client that represents basic bank clients
 */
abstract public class StandardClient extends Client{
    public StandardClient(String clientNum, String name, String password, ArrayList<String> accounts) {
        super(clientNum, name, password, accounts);
    }
}