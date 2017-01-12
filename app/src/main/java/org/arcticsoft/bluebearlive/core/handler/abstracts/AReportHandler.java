package org.arcticsoft.bluebearlive.core.handler.abstracts;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.types.answer.ServerAnswerReportPacket;

/**
 * Created by novak on 05.01.2017.
 */
public abstract class AReportHandler {

    public void handlePacket(Packet p) {
        try {
            ServerAnswerReportPacket serverAnswerReportPacket = (ServerAnswerReportPacket)p.getData();
            handleReportPacket(serverAnswerReportPacket);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected abstract void handleReportPacket(ServerAnswerReportPacket serverAnswerReportPacket);
}
