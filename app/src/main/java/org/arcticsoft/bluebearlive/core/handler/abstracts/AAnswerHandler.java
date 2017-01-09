package org.arcticsoft.bluebearlive.core.handler.abstracts;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.types.answer.ServerAnswerPacket;

/**
 * Created by DmitriyRoot on 07.01.2017.
 */

public abstract class AAnswerHandler {

    ServerAnswerPacket serverAnswerPacket;

    public void handlePacket(Packet p) {
        try {
            serverAnswerPacket = (ServerAnswerPacket)p.getData();
            handleAnswerPacket(serverAnswerPacket);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected abstract void handleAnswerPacket(ServerAnswerPacket serverAnswerPacket);

    protected int initError(){
        return serverAnswerPacket.getServerAnswerCode();
    }
}
