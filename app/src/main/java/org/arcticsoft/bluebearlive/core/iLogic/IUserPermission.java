package org.arcticsoft.bluebearlive.core.iLogic;

import org.arcticsoft.bluebearlive.core.logic.Session;
import org.arcticsoft.bluebearlive.core.logic.User;

/**
 * Created by DmitriyRoot on 04.01.2017.
 */

public interface IUserPermission {

    boolean havePermission(User user);

    boolean checkTrueSession(String sessionKey);
}
