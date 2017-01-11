package org.arcticsoft.bluebearlive.core.logic.Temp;

import com.mrheadshot62.api.types.CorePacket;

import java.io.Serializable;

/**
 * Created by Роман on 11.01.2017.
 */

public class ReportPacket extends CorePacket implements Serializable {

    private final int userId;
    private final String message;
    private final byte typeReport;
    private final int reportOnUserId;

    public ReportPacket(int userId, String message, byte typeReport, int reportOnUserId) {
        this.userId = userId;
        this.message = message;
        this.typeReport = typeReport;
        this.reportOnUserId = reportOnUserId;
    }

    public int getReportOnUserId() {
        return reportOnUserId;
    }

    public int getTypeReport() {
        return typeReport;
    }

    public String getMessage() {
        return message;
    }

    public int getUserId() {
        return userId;
    }
}
