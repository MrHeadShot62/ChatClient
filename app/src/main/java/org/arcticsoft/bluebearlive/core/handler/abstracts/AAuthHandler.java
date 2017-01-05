package org.arcticsoft.bluebearlive.core.handler.abstracts;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.types.AuthPacket;

/**
 * Created by novak on 05.01.2017.
 */
public abstract class AAuthHandler {

    public void handlePacket(Packet p) {
        try {
            AuthPacket authPacket = (AuthPacket)p.getData();
            handleAuthPacket(authPacket);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected abstract void handleAuthPacket(AuthPacket packet);
}
