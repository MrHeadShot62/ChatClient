package org.arcticsoft.bluebearlive.core.handler;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.Types;
import com.mrheadshot62.api.types.PermissionPacket;

/**
 * Created by novak on 05.01.2017.
 */
class MainHandler {
    private Packet[] packets;
    private PermissionPacket permisson;


    public MainHandler(Packet[] packets) {
        this.packets = packets;
        if (havePermissionPacket()){
            for (Packet p : packets) {
                switch (p.getType()){
                    case Types.AUTH:
                        new AuthHandler().handlePacket(p);
                        break;
                    case Types.Command:
                        new CommandHandler().handlePacket(p);
                        break;
                    case Types.Image:
                        new ImageHandler().handlePacket(p);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private boolean checkSession(){
        return true;    //TODO Session
    }

    private boolean havePermissionPacket(){
        for (Packet p:packets){
            if (p.getType() == Types.PERMISSION){
                this.permisson = (PermissionPacket)p.getData();
                return true;
            }
        }
        return false;
    }
}
