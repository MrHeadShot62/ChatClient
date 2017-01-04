package org.arcticsoft.bluebearlive.core.iLogic;

/**
 * Created by DmitriyRoot on 04.01.2017.
 */

public interface ILogin {

    boolean checkLoginInDataBase();

    boolean checkPasswordInDataBase();

    void refreshStatus();

    void refreshLastLogin();

    void refreshLastIp();
}
