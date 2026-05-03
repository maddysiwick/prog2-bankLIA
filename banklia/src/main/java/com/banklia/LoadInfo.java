package com.banklia;

import java.util.Date;
/**
 * class to combine information that is relevant between sessions
 */
public class LoadInfo {
    /**
     * the last time that the monthly fees and interest of all bank accounts was applied
     */
    private Date lastUpdate;
    /**
     * the account, if any, that was left signed in
     */
    private String signedIn;

    public LoadInfo(Date lastUpdate, String signedIn) {
        this.lastUpdate = lastUpdate;
        this.signedIn = signedIn;
    }
    
    public Date getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    public String getSignedIn() {
        return signedIn;
    }
    public void setSignedIn(String signedIn) {
        this.signedIn = signedIn;
    }
    
}
