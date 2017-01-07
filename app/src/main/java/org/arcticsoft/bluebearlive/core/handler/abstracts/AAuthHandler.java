package org.arcticsoft.bluebearlive.core.handler.abstracts;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.types.AuthPacket;
import com.mrheadshot62.api.types.answer.ServerAnswerAuthPacket;

/**
 * Created by novak on 05.01.2017.
 */
public abstract class AAuthHandler {

    public void handlePacket(Packet p) {
        try {
            ServerAnswerAuthPacket serverAnswerAuthPacket = (ServerAnswerAuthPacket)p.getData();
            handleAuthPacket(serverAnswerAuthPacket);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected abstract void handleAuthPacket(ServerAnswerAuthPacket serverAnswerAuthPacket);
}
