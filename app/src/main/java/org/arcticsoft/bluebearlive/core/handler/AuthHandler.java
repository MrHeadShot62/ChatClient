package org.arcticsoft.bluebearlive.core.handler;


import android.util.Log;

import com.mrheadshot62.api.types.AuthPacket;
import com.mrheadshot62.api.types.answer.ServerAnswerAuthPacket;

import org.arcticsoft.bluebearlive.core.handler.abstracts.AAuthHandler;

class AuthHandler extends AAuthHandler {

    private static final String TAG = "AUTH: ";

    @Override
    protected void handleAuthPacket(ServerAnswerAuthPacket serverAnswerAuthPacket) {
        Log.d(TAG, "nameUser: "+serverAnswerAuthPacket.getNameUser());
        Log.d(TAG, "countryUser: "+serverAnswerAuthPacket.getCountryUser());
        Log.d(TAG, "sessionKey: "+serverAnswerAuthPacket.getSessionKey());
        Log.d(TAG, "permission: "+serverAnswerAuthPacket.getPermission());
    }

}
