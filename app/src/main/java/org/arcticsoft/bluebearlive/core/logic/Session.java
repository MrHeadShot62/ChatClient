package org.arcticsoft.bluebearlive.core.logic;

import android.util.Log;

import org.arcticsoft.bluebearlive.core.aLogic.ASession;

/**
 * Created by DmitriyRoot on 04.01.2017.
 */

public class Session extends ASession {

    private String sessionKey;

    @Override
    public boolean checkSession() {
        return super.checkSession();
    }

    @Override
    public void setSessionKey() {
        this.sessionKey = null;
        Log.d("SessionKey = ", this.sessionKey);
    }
}
