package org.arcticsoft.bluebearlive.core.iLogic;

/**
 * Created by DmitriyRoot on 04.01.2017.
 */

public interface ILogin {

    void startLogin(String name, String pass);

    boolean checkLoginInDataBase(String name);

    boolean checkPasswordInDataBase(String pass);

    void refreshStatus();

    void refreshLastLogin();

    void refreshLastIp();
}
