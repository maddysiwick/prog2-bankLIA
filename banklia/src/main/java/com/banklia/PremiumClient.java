package com.banklia;

import java.util.ArrayList;
/**
 * a child of client, represents more sophisticated kinds of bank client
 */
abstract public class PremiumClient extends Client{

    public PremiumClient(String clientNum, String name, String password, ArrayList<String> accounts) {
        super(clientNum, name, password, accounts);
    }
}
