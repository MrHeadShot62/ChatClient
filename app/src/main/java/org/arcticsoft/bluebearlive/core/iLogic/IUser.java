package org.arcticsoft.bluebearlive.core.iLogic;

/**
 * Created by DmitriyRoot on 04.01.2017.
 */

public interface IUser {

    void login();

    void logout();

    boolean isLogin();

    boolean isBanned();

    void sendData();

    void editUserName();
}
