package org.arcticsoft.bluebearlive.core.handler;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.Types;
import com.mrheadshot62.api.types.PermissionPacket;

import org.arcticsoft.bluebearlive.core.logic.TypesAnswer;

/**
 * Created by novak on 05.01.2017.
 */
public class MainHandler {
    private Packet[] packets;
    private PermissionPacket permisson;


    public MainHandler(Packet[] packets) {
        this.packets = packets;
        for (Packet p : packets) {
            switch (p.getType()){
                case TypesAnswer.AUTHPACKET:
                    new AuthHandler().handlePacket(p);
                    break;
                default:
                    break;
            }
        }
    }

    private boolean checkSession(){
        return true;    //TODO Session
    }

}
