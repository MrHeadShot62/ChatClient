package org.arcticsoft.bluebearlive.core.handler.abstracts;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.types.UserPacket;
import com.mrheadshot62.api.types.answer.ServerAnswerUserPacket;

/**
 * Created by novak on 05.01.2017.
 */
public abstract class AUserHandler{

    public void handlePacket(Packet p) {
        try{
            ServerAnswerUserPacket userPacket = (ServerAnswerUserPacket) p.getData();
            handleCommandPacket(userPacket);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected abstract void handleCommandPacket(ServerAnswerUserPacket userPacket);
}
