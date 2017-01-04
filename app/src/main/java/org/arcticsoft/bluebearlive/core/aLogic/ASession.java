package org.arcticsoft.bluebearlive.core.aLogic;

import org.arcticsoft.bluebearlive.core.iLogic.ISession;

/**
 * Created by DmitriyRoot on 04.01.2017.
 */

public abstract class ASession implements ISession {

    @Override
    public boolean checkSession() {
        return true;
    }

    @Override
    public void setSessionKey() {

    }
}
