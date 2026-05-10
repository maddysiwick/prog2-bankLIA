package com.banklia;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class ClientTest {
    @Test
    public void addAccountTest(){
        IndividualClient client=new IndividualClient("1111", "John Doe", "password123", new ArrayList<>());
        client.addAccount(new ChequingAccount("111"));
        assertEquals(client.getAccounts().size(),1,"test failed because account was not correctly added to list");
    }
}
