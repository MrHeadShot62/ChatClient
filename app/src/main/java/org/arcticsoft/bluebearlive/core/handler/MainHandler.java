package org.arcticsoft.bluebearlive.core.handler;

import android.util.Log;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.TypesAnswer;
import com.mrheadshot62.api.types.PermissionPacket;



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
                case TypesAnswer.USERAUTHPACKET:
                    Log.d("packetAuth", "prin9Il");
                    new AuthHandler().handlePacket(p);
                    break;
                case TypesAnswer.ONLYCODE:
                    new AnswerHandler().handlePacket(p);
                    break;
                case TypesAnswer.USERPACKET:
                    //TODO Большой Ди, ты знаешь что делать... Database.setUser(User u);Database.getUser();
                    new UserHandler().handlePacket(p);
                case TypesAnswer.REPORTPACKET:
                    new ReportHandler().handlePacket(p);
                    break;
                case TypesAnswer.SECOND_AUTH:
                    new SecondAuthHandler().handlePacket(p);
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
