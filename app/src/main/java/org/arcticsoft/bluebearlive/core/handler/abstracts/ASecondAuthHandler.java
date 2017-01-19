package org.arcticsoft.bluebearlive.core.handler.abstracts;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.types.answer.ServerAnswerCheckSecretKeyAuth;

/**
 * Created by DmitriyRoot on 07.01.2017.
 */

public abstract class ASecondAuthHandler {

    ServerAnswerCheckSecretKeyAuth serverAnswerCheckSecretKeyAuth;

    public void handlePacket(Packet p) {
        try {
            serverAnswerCheckSecretKeyAuth = (ServerAnswerCheckSecretKeyAuth)p.getData();
            handleAnswerPacket(serverAnswerCheckSecretKeyAuth);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected abstract void handleAnswerPacket(ServerAnswerCheckSecretKeyAuth serverAnswerCheckSecretKeyAuth);
}
