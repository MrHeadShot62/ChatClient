package org.arcticsoft.bluebearlive.core.handler;

import android.util.Log;

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
                    Log.d("packetAuth", "prin9Il");
                    new AuthHandler().handlePacket(p);
                    break;
                case TypesAnswer.ONLYCODE:
                    new AnswerHandler().handlePacket(p);
                    break;
                case TypesAnswer.REPORTPACKET:
                    new ReportHandler().handlePacket(p);
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
