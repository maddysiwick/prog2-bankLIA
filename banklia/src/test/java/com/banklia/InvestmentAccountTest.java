package com.banklia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.Date;

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
    @Test
    public final void testTransferErrorTime(){
        InvestmentAccount testAcc=new InvestmentAccount("1111");
        testAcc.setBalance(100.0);
        ChequingAccount transferAccount=new ChequingAccount("2222");
        try{
            testAcc.transfer(15.0, transferAccount);
            fail("test failed  because the account has not been open long enough to transfer");
        }catch(InsufficientFundsException e){
            fail("test failed because insufficient funds should not be thrown");
        }catch(InvestmentLockException e){
            assertTrue(true);
        }
    }
    @Test
    public final void testTransferErrorType(){
        InvestmentAccount testAcc=new InvestmentAccount("1111");
        testAcc.setBalance(100.0);
        InvestmentAccount transferAccount=new InvestmentAccount("2222");
        try{
            testAcc.transfer(15.0, transferAccount);
            fail("test failed  because the account has not been open long enough to transfer");
        }catch(InsufficientFundsException e){
            fail("test failed because insufficient funds should not be thrown");
        }catch(InvestmentLockException e){
            assertTrue(true);
        }
    }
    @Test
    public final void testTransferPass(){
        InvestmentAccount testAcc=new InvestmentAccount("1111");
        testAcc.setBalance(100.0);
        testAcc.setDateOpened(new Date(2022,12,12));
        ChequingAccount transferAccount=new ChequingAccount("2222");
        try{
            testAcc.transfer(15.0, transferAccount);
            assertTrue(true);
        }catch(InsufficientFundsException e){
            fail("test failed because insufficient funds should not be thrown");
        }catch(InvestmentLockException e){
            fail("test failed because transferring shoukd be allowed");
        }
    }
}
