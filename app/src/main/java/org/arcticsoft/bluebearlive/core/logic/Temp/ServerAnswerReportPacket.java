package org.arcticsoft.bluebearlive.core.logic.Temp;

import java.io.Serializable;

/**
 * Created by Роман on 11.01.2017.
 */

public class ServerAnswerReportPacket implements Serializable {

    private final byte typeAnswerReport;

    public ServerAnswerReportPacket(byte typeAnswerReport) {
        this.typeAnswerReport = typeAnswerReport;
    }

    public byte getTypeAnswerReport() {
        return typeAnswerReport;
    }
}
