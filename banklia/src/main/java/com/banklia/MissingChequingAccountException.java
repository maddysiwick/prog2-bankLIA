package com.banklia;

/**
 * exception that is thrown if a client tries to open an account of another type before having a chequing account open
 */
public class MissingChequingAccountException extends Exception{
    public MissingChequingAccountException(String message) {
        super(message);
    }
    
}
