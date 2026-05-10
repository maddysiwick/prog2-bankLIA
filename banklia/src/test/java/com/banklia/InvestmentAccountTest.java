package com.banklia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class InvestmentAccountTest {
    @Test
    public final void testWithdrawl(){
        InvestmentAccount testAcc=new InvestmentAccount("1111");
        testAcc.setBalance(15.0);
        try{
            testAcc.withdraw(10.0);
            fail("test failed  because withdrawing from investment is impossible");
        }catch(InsufficientFundsException e){
            fail("test failed because withdraw should never throw insufficient funds from investement");
        }catch(InvestmentLockException e){
            assertTrue(true);
        }

    }
    @Test
    public final void testApplyInterest(){
        InvestmentAccount testAcc=new InvestmentAccount("1111");
        testAcc.setBalance(100.0);
        testAcc.applyInterest();
        assertEquals(105.0,testAcc.getBalance(),"test failed because interest was not applied correctly");
    }
}
