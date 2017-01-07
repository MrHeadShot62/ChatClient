package org.arcticsoft.bluebearlive.core.handler.abstracts;

import com.mrheadshot62.api.types.answer.ServerAnswerPacket;

/**
 * Created by DmitriyRoot on 07.01.2017.
 */

public abstract class AAnswerHandler {

    ServerAnswerPacket serverAnswerPacket;

    public void handlePacket(ServerAnswerPacket p) {
        try {
            serverAnswerPacket = (ServerAnswerPacket)p.getObjects();
            handleAuthPacket(serverAnswerPacket);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected abstract void handleAuthPacket(ServerAnswerPacket serverAnswerPacket);

    protected int initError(){
        return serverAnswerPacket.getServerAnswerCode();
    }
}
