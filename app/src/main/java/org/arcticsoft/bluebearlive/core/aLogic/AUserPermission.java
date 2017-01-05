package org.arcticsoft.bluebearlive.core.aLogic;

import org.arcticsoft.bluebearlive.core.iLogic.IUserPermission;
import org.arcticsoft.bluebearlive.core.logic.Session;
import org.arcticsoft.bluebearlive.core.logic.User;

/**
 * Created by DmitriyRoot on 04.01.2017.
 */

public abstract class AUserPermission implements IUserPermission {
    @Override
    public boolean havePermission(User user) {
        if (!user.isLogin()){
            return false;
        }
        if (!user.isBanned()){
            return false;
        }
        if (!checkTrueSession(user.getSession())){
            return false;
        }
        return true;

    }

    @Override
    public boolean checkTrueSession(Session session) {
        return true;
    }
}
