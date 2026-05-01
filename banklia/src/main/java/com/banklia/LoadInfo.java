package com.banklia;

import java.util.Date;
public class LoadInfo {
    private Date lastUpdate;
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
