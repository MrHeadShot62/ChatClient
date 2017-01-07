package org.arcticsoft.bluebearlive.core.handler;

import android.util.Log;

import com.mrheadshot62.api.types.answer.ServerAnswerPacket;

import org.arcticsoft.bluebearlive.core.handler.abstracts.AAnswerHandler;

/**
 * Created by DmitriyRoot on 07.01.2017.
 */

class AnswerHandler extends AAnswerHandler {

    private static final String TAG = "AUTH: ";

    @Override
    protected void handleAuthPacket(ServerAnswerPacket serverAnswerPacket) {
        Log.d(TAG, "CodeAnswerRequest: "+super.initError());
    }
}
