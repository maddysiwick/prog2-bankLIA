package com.banklia;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ChequingAccountTest {
    @Test
    public final void testWithdrawl(){
        ChequingAccount testAcc=new ChequingAccount("1111");
        testAcc.setBalance(15.0);
        try{
            testAcc.withdraw(10.0);
            assertEquals(5.0,testAcc.getBalance(),"simple withdraw works for chequing account");
        }catch(InsufficientFundsException e){
            fail("withdraw failed due to insufficient funds");
        }catch(NegativeMoneyException e){
            fail("test failed because NegativeMoneyException was thrown");
        }

    }
    @Test
    public final void testOverdraw(){
        ChequingAccount testAcc=new ChequingAccount("1111");
        testAcc.setBalance(15.0);
        try{
            testAcc.withdraw(20.0);
            fail("test failed because overdrafting is allowed");
        }catch(InsufficientFundsException e){
            assertTrue(true);
        }catch(NegativeMoneyException e){
            fail("test failed because NegativeMoneyException was thrown");
        }
    }
}
