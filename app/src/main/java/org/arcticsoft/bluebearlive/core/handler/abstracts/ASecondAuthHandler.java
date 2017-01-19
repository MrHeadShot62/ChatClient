package org.arcticsoft.bluebearlive.core.handler.abstracts;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.types.answer.ServerAnswerSecondAuth;

/**
 * Created by DmitriyRoot on 07.01.2017.
 */

public abstract class ASecondAuthHandler {

    ServerAnswerSecondAuth serverAnswerSecondAuth;

    public void handlePacket(Packet p) {
        try {
            serverAnswerSecondAuth = (ServerAnswerSecondAuth)p.getData();
            handleAnswerPacket(serverAnswerSecondAuth);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected abstract void handleAnswerPacket(ServerAnswerSecondAuth serverAnswerSecondAuth);
}
