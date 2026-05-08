package com.banklia;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ChequingAccountTest {
    @Test
    public final void testWithdrawlClean(){
        ChequingAccount testAcc=new ChequingAccount("1111");
        testAcc.setBalance(15.0);
        try{
            testAcc.withdraw(10.0);
            assertEquals(5.0,testAcc.getBalance(),"withdraw works for chequing account");
        }catch(InsufficientFundsException e){
            fail("withdraw failed due to insufficient funds");
        }

    }
}
