package org.arcticsoft.bluebearlive.core.aLogic;

import org.arcticsoft.bluebearlive.core.iLogic.ILogin;

/**
 * Created by DmitriyRoot on 04.01.2017.
 */

public abstract class ALogin implements ILogin {

    @Override
    public void startLogin(String name, String pass) {
        if(!checkLoginInDataBase(name)){
            return;
        }
        if (checkPasswordInDataBase(pass)){
            return;
        }
        refreshLastIp();
        refreshLastLogin();
        refreshStatus();
    }

    @Override
    public boolean checkLoginInDataBase(String name) {
        return true;
    }

    @Override
    public boolean checkPasswordInDataBase(String pass) {
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
