package org.arcticsoft.bluebearlive.core.iLogic;

import org.arcticsoft.bluebearlive.core.logic.User;

/**
 * Created by DmitriyRoot on 04.01.2017.
 */

public interface IErrorCore {

    boolean checkError();

    int getErrorCode();

    String getErrorCodeString();

    void sendDataError();//TODO AndreyPacket type

    User getUserThrowExeption();
}
