package com.banklia;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class ClientTest {
    @Test
    public void addAccountTest(){
        IndividualClient client=new IndividualClient("1111", "John Doe", "password123", new ArrayList<>());
        try{
            client.addAccount(new ChequingAccount("111"));
            assertEquals(1,client.getAccounts().size(),"test failed because account was not correctly added to list");
        }catch(MissingChequingAccountException e){
            fail("test failed because a missing chequing account was thrown when it should be impossible");
        }
    }
    @Test
    public void addAccountTestError(){
        IndividualClient client=new IndividualClient("1111", "John Doe", "password123", new ArrayList<>());
        try{
            client.addAccount(new InvestmentAccount("111"));
            fail("test failed because adding of an investment account without a prior chequing account was allowed");
        }catch(MissingChequingAccountException e){
            assertTrue(true);
        }
    }
}
