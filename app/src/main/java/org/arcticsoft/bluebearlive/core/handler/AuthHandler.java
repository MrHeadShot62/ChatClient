package org.arcticsoft.bluebearlive.core.handler;


import android.util.Log;

import com.mrheadshot62.api.types.answer.ServerAnswerAuthPacket;

import org.arcticsoft.bluebearlive.core.handler.abstracts.AAuthHandler;
import org.arcticsoft.bluebearlive.core.logic.Application;
import org.arcticsoft.bluebearlive.core.logic.User;

class AuthHandler extends AAuthHandler {

    private static final String TAG = "AUTH: ";

    @Override
    protected void handleAuthPacket(ServerAnswerAuthPacket serverAnswerAuthPacket) {
        if (!Application.getInstance().getUserApplication().isAuth){
//            User.sync.notify();
            Application.getInstance().getUserApplication().isAuth=true;
        }
        Log.d(TAG, "nameUser: "+serverAnswerAuthPacket.getNameUser());
        Log.d(TAG, "countryUser: "+serverAnswerAuthPacket.getCountryUser());
        Log.d(TAG, "sessionKey: "+serverAnswerAuthPacket.getSessionKey());
        Log.d(TAG, "permission: "+serverAnswerAuthPacket.getPermission());
    }

}
