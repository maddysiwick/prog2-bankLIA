package com.banklia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class SavingsAccountTest {
    @Test
    public final void testWithdrawl(){
        SavingsAccount testAcc=new SavingsAccount("1111");
        testAcc.setBalance(15.0);
        try{
            testAcc.withdraw(10.0);
            assertEquals(5.0,testAcc.getBalance(),"simple withdraw works for chequing account");
        }catch(InsufficientFundsException e){
            fail("withdraw failed due to insufficient funds");
        }

    }
    @Test
    public final void testOverdraw(){
        SavingsAccount testAcc=new SavingsAccount("1111");
        testAcc.setBalance(15.0);
        try{
            testAcc.withdraw(20.0);
            fail("test failed because overdrafting is allowed");
        }catch(InsufficientFundsException e){
            assertTrue(true,"throws an exception on overdraft");
        }
    }
    @Test
    public final void testApplyInterest(){
        SavingsAccount testAcc=new SavingsAccount("1111");
        testAcc.setBalance(100.0);
        testAcc.applyInterest();
        assertEquals(102.0,testAcc.getBalance(),"apply interest works");
    }
}
