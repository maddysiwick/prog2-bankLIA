package com.banklia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import java.util.Date;

public class StudentClientTest {
    @Test
    public void addAccountTest(){
        StudentClient client=new StudentClient("1111", "John Doe", "password123", new ArrayList<>(),new Date(12,12,2028));
        SavingsAccount account=new SavingsAccount("111");
        client.addAccount(account);
        assertEquals(client.getAccounts().size(),1,"test failed because account was not correctly added to list");
        assertEquals(account.getMonthlyFee(),0.0,"test failed because montly fee was not waived");
    }
}
