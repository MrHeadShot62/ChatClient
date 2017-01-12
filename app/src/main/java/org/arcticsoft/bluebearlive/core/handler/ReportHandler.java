package org.arcticsoft.bluebearlive.core.handler;


import android.util.Log;

import com.mrheadshot62.api.types.answer.ServerAnswerReportPacket;

import org.arcticsoft.bluebearlive.core.handler.abstracts.AReportHandler;

class ReportHandler extends AReportHandler {

    private static final String TAG = "REPORT: ";

    @Override
    protected void handleReportPacket(ServerAnswerReportPacket serverAnswerReportPacket) {

        Log.d(TAG, "answer report from server "+ serverAnswerReportPacket.getTypeAnswerReport());

    }
}
