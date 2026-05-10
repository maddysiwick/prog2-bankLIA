package com.banklia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class VIPClientTest {
    @Test
    public void addSavingsAccountTest(){
        VIPCLient client=new VIPCLient("1111", "John Doe", "password123", new ArrayList<>());
        SavingsAccount account=new SavingsAccount("111");
        client.addAccount(account);
        assertEquals(client.getAccounts().size(),1,"test failed because account was not correctly added to list");
        assertEquals(account.getInterestRate(),0.03,"test failed because interest rate was not what was expected");
        assertEquals(account.getMonthlyFee(),0.0,"test failed because montly fee was not waived");
    }
    @Test
    public void addInvestmentAccountTest(){
        VIPCLient client=new VIPCLient("1111", "John Doe", "password123", new ArrayList<>());
        InvestmentAccount account=new InvestmentAccount("111");
        client.addAccount(account);
        assertEquals(client.getAccounts().size(),1,"test failed because account was not correctly added to list");
        assertEquals(0.01+0.05,account.getInterestRate(),"test failed because interest rate was not what was expected");
        assertEquals(0.0,account.getMonthlyFee(),"test failed because montly fee was not waived");
    }
}
