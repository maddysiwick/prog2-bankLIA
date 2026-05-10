package com.banklia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class VIPClientTest {
    @Test
    public void addSavingsAccountTest(){
        VIPCLient client=new VIPCLient("1111", "John Doe", "password123", new ArrayList<>());
        SavingsAccount account=new SavingsAccount("111");
        try{
            client.addAccount(new ChequingAccount("0000"));
            client.addAccount(account);
            assertEquals(2,client.getAccounts().size(),"test failed because account was not correctly added to list");
            assertEquals(0.03,account.getInterestRate(),"test failed because interest rate was not what was expected");
            assertEquals(0.0,account.getMonthlyFee(),"test failed because montly fee was not waived");
        }catch(MissingChequingAccountException e){
            fail("test failed because missing chequing was thrown when it shouldn't be");
        }
        
    }
    @Test
    public void addInvestmentAccountTest(){
        VIPCLient client=new VIPCLient("1111", "John Doe", "password123", new ArrayList<>());
        InvestmentAccount account=new InvestmentAccount("111");
        try{
            client.addAccount(new ChequingAccount("0000"));
            client.addAccount(account);
            assertEquals(2,client.getAccounts().size(),"test failed because account was not correctly added to list");
            assertEquals(0.01+0.05,account.getInterestRate(),"test failed because interest rate was not what was expected");
            assertEquals(0.0,account.getMonthlyFee(),"test failed because montly fee was not waived");
        }catch(MissingChequingAccountException e){
            fail("test failed because missing chequing was thrown when it shouldn't be");
        }
    }
}
