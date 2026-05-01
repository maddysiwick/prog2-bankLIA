package com.banklia;
import java.util.ArrayList;

abstract public class StandardClient extends Client{
    public StandardClient(String clientNum, String name, String password, ArrayList<String> accounts) {
        super(clientNum, name, password, accounts);
    }
}