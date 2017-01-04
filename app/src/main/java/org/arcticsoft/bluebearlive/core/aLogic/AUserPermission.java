package org.arcticsoft.bluebearlive.core.aLogic;

import org.arcticsoft.bluebearlive.core.iLogic.IUserPermission;
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
        return true;
    }

    @Override
    public boolean checkId() {
        return false;
    }
}
