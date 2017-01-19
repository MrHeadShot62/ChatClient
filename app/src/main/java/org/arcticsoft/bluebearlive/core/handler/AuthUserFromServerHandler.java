package org.arcticsoft.bluebearlive.core.handler;


import android.content.Intent;
import android.util.Log;

import com.mrheadshot62.api.types.answer.ServerAnswerAuthUserPacket;

import org.arcticsoft.bluebearlive.activity.MainActivity;
import org.arcticsoft.bluebearlive.core.handler.abstracts.AAuthHandler;
import org.arcticsoft.bluebearlive.core.logic.DataBase;
import org.arcticsoft.bluebearlive.core.logic.Util;

class AuthUserFromServerHandler extends AAuthHandler {

    private static final String TAG = "AuthFromServerHandler";

    @Override
    protected void handleAuthPacket(ServerAnswerAuthUserPacket serverAnswerAuthPacket) {
        if (!Util.getUserApplication().isAuth){
            Util.setUserApplication(serverAnswerAuthPacket);
            Util.getContext().startActivity(new Intent(Util.getContext(), MainActivity.class));
        }
        try {
            DataBase.getInstance().setUser(serverAnswerAuthPacket.getUserDatas());
        } catch (InstantiationException e) {
            Log.e(TAG, "DataBase not install");
        }
        Log.d(TAG, String.format("SESSION: %s", serverAnswerAuthPacket.getSession()));
    }

}
