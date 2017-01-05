package org.arcticsoft.bluebearlive.core.handler.abstracts;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.iLogic.IPacketCommandHandler;
import com.mrheadshot62.api.types.CommandPacket;

/**
 * Created by novak on 05.01.2017.
 */
public abstract class ACommandHandler{

    public void handlePacket(Packet p) {
        try{
            CommandPacket commandPacket = (CommandPacket)p.getData();
            handleCommandPacket(commandPacket);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected abstract void handleCommandPacket(CommandPacket commandPacket);
}
