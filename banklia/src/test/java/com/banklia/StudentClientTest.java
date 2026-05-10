package com.banklia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import java.util.Date;

public class StudentClientTest {
    @Test
    public void addAccountTest(){
        StudentClient client=new StudentClient("1111", "John Doe", "password123", new ArrayList<>(),new Date(2028,12,12));
        SavingsAccount account=new SavingsAccount("111");
        try{
            client.addAccount(new ChequingAccount("0000"));
            client.addAccount(account);
            assertEquals(2,client.getAccounts().size(),"test failed because account was not correctly added to list");
            assertEquals(0.0,account.getMonthlyFee(),"test failed because montly fee was not waived");
        }catch(MissingChequingAccountException e){
            fail("test failed because missing chequing was thrown when it should not have been");
        }
        
    }
}
