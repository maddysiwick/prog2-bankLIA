package com.banklia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class AccountTest {
    @Test
    public final void testDeposit(){
        ChequingAccount testAcc=new ChequingAccount("1111");
        testAcc.deposit(15.0);
        assertEquals(15.0,testAcc.getBalance(),"deposit works for chequing account");
    }
    @Test
    public final void testTransfer(){
        ChequingAccount testAcc=new ChequingAccount("1111");
        ChequingAccount transAccount= new ChequingAccount("2222");
        testAcc.deposit(15.0);
        try{
            testAcc.transfer(5.0, transAccount);
            assertEquals(10.0,testAcc.getBalance(),"test failed because money was not transferred out correctly");
            assertEquals(5.0,transAccount.getBalance(),"test failed because money was not transfered in correctly");
        }catch(InvestmentLockException e){
            fail("test failed because it threw an investment lock");
        }catch(InsufficientFundsException e){
            fail("test failed because it threw an insufficient funds exception");
        }
    }
    @Test
    public final void testApplyMonthlyFee(){
        ChequingAccount testAcc=new ChequingAccount("1111");
        testAcc.setBalance(15.0);
        try{
            testAcc.applyMonthlyFee();
            assertEquals(5.0,testAcc.getBalance(),"test failed because monthly fee is not executed properly");
        }catch(InsufficientFundsException e){
            fail("test failed because it threw insufficient funds");
        }
    }
    @Test
    public final void testApplyMonthlyFeeError(){
        ChequingAccount testAcc=new ChequingAccount("1111");
        testAcc.setBalance(5.0);
        try{
            testAcc.applyMonthlyFee();
            fail("test failed because insufficient funds was not thrown");
        }catch(InsufficientFundsException e){
            assertTrue(true);
        }
    }
}
