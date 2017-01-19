package org.arcticsoft.bluebearlive.core.handler;

import android.content.Intent;

import com.mrheadshot62.api.types.answer.ServerAnswerCheckSecretKeyAuth;

import org.arcticsoft.bluebearlive.activity.SecondStepAuth;
import org.arcticsoft.bluebearlive.core.handler.abstracts.ASecondAuthHandler;
import org.arcticsoft.bluebearlive.core.logic.Util;

/**
 * Created by DmitriyRoot on 07.01.2017.
 */

class SecondAuthHandler extends ASecondAuthHandler {

    private static final String TAG = "SECOND AUTH HANDLER: ";

    @Override
    protected void handleAnswerPacket(ServerAnswerCheckSecretKeyAuth serverAnswerCheckSecretKeyAuth) {
        Intent intent = new Intent(Util.getContext(), SecondStepAuth.class);
        if (serverAnswerCheckSecretKeyAuth.isToRegister()){
            intent.putExtra("typeAuth", "REG");
        }else {
            intent.putExtra("typeAuth", "LOG");
        }
        intent.putExtra("userID", serverAnswerCheckSecretKeyAuth.getId());
        Util.mainActivity.startActivity(intent);
    }
}
