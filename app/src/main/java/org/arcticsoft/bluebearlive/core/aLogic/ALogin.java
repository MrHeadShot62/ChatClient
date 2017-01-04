package org.arcticsoft.bluebearlive.core.aLogic;

import org.arcticsoft.bluebearlive.core.iLogic.ILogin;

/**
 * Created by DmitriyRoot on 04.01.2017.
 */

public abstract class ALogin implements ILogin {



    @Override
    public boolean checkLoginInDataBase() {
        return true;
    }

    @Override
    public boolean checkPasswordInDataBase() {
        return true;
    }

    @Override
    public void refreshStatus() {

    }

    @Override
    public void refreshLastLogin() {

    }

    @Override
    public void refreshLastIp() {

    }
}
