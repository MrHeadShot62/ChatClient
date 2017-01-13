package org.arcticsoft.bluebearlive.core.handler;


import android.util.Log;

import com.mrheadshot62.api.types.answer.ServerAnswerAuthUserPacket;

import org.arcticsoft.bluebearlive.core.handler.abstracts.AAuthHandler;
import org.arcticsoft.bluebearlive.core.logic.Application;
import org.arcticsoft.bluebearlive.core.logic.DataBase;

class AuthHandler extends AAuthHandler {

    private static final String TAG = "AuthHandler";

    @Override
    protected void handleAuthPacket(ServerAnswerAuthUserPacket serverAnswerAuthPacket) {
        if (!Application.getInstance().getUserApplication().isAuth){
            Application.getInstance().getUserApplication().isAuth=true;
        }
        try {
            DataBase.getInstance().setUser(serverAnswerAuthPacket.getUser());
        } catch (InstantiationException e) {
            Log.e(TAG, "DataBase not instantiate");
        }
        Log.d(TAG, String.format("SESSION: %s", serverAnswerAuthPacket.getSession()));
    }

}
