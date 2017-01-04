package org.arcticsoft.bluebearlive.core.iLogic;

/**
 * Created by DmitriyRoot on 04.01.2017.
 */

public interface IRegisteration {

    void addUserInDataBase();

    boolean checkLogin();

    boolean checkPassword();

    boolean checkName();

    boolean isBannedIp();

}
