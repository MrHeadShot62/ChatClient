package org.arcticsoft.bluebearlive.core.logic.Temp;

import android.util.ArrayMap;

/**
 * Created by Роман on 11.01.2017.
 */

public class TypeAnswerReportMessage {

    private static TypeAnswerReportMessage instance = null;

    public ArrayMap<Integer, String> answerReportMessage = new ArrayMap<>();

    private TypeAnswerReportMessage() {
        answerReportMessage.put(TypeAnswerReport.OK, "Your report sended");
        answerReportMessage.put(TypeAnswerReport.NOTPERMISSION, "You not have permission for sended report");
        answerReportMessage.put(TypeAnswerReport.REPORTREADY, "Your report is considered");
    }

    public static TypeAnswerReportMessage getInstance(){
        if(instance == null){
            return instance = new TypeAnswerReportMessage();
        }else {
            return instance;
        }
    }

}
