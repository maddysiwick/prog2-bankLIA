package com.banklia;

public class MissingChequingAccountException extends Exception{
    public MissingChequingAccountException(String message) {
        super(message);
    }
    
}
