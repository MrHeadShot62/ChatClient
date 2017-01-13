package org.arcticsoft.bluebearlive.core.aLogic;

import android.content.Context;
import android.util.Log;

import org.arcticsoft.bluebearlive.core.logic.Session;
import org.arcticsoft.bluebearlive.core.logic.User;

/**
 * Created by DmitriyRoot on 06.01.2017.
 */

public abstract class AApplication {


    private static final String TAG = "AAPPLICATION";


    public AApplication() {
        try {
//            setUserApplication();
            //setUserLanguage();
            //setServerIP();
        }catch (Exception e){
            Log.e("AAPLICATION", "Error Start Configuration in Application", e);
        }
    }

    public abstract boolean setUserApplication();

    public abstract void initDataBase(Context context);

    //public abstract void setUserLanguage();

    public abstract void setServerIP();

    public abstract boolean setServerConnection();

    public abstract String getServerIP();

    //public abstract void getSenderPacketManager();

    //public abstract void getListenerPacketManager();

    public abstract User getUserApplication();

    public abstract int getUserPermission();

    public abstract Session getSession();

    //public abstract int getUserLanguage();

    //public abstract void getErrorReport();

    //public abstract void getCommandBuilder();

    //public abstract void getPhoneInformation();

    //public abstract void sendLogOut();

    public abstract boolean sendAuth();

    public abstract boolean checkServerConnection();

    public abstract boolean reconnectServer();

    //public abstract void checkRobot();

    //public abstract void loadingFeedLine();

    //public abstract void reloadingFeedLine();

    //public abstract void loadStatisticUser();

    //public abstract void sendErrorFromApplication();

    //public abstract void sendServerStatus();

    //public abstract void sendUserStatus();

    public abstract boolean sendReportPacket(int userId, String message, int typeReport, int ReportOnUserId);

}
