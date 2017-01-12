package org.arcticsoft.bluebearlive.core.logic;

import android.content.Context;
import android.util.Log;

import com.mrheadshot62.api.types.AuthPacket;

import org.arcticsoft.bluebearlive.core.aLogic.AApplication;
import org.arcticsoft.bluebearlive.socket.ClientThread;
import org.arcticsoft.bluebearlive.socket.ConnectionController;

/**
 * Created by DmitriyRoot on 06.01.2017.
 */

public class Application extends AApplication {

    private static final String TAG = "APPLICATION";
    private static final String SERVERIP = "192.168.0.102";

    private User userAplication;
    private ClientThread clientThread;
    private Context context;
    private static Application instance = null;

    public static Application getInstance(){
        if(instance == null){
            instance = new Application();
            return instance;
        }else {
            return instance;
        }
    }
    public Application() {

    }

    @Override
    public boolean setUserApplication() {
        if(User.getInstance() == null){
            userAplication = User.guestUser("guest", "TestName", "TestCountry");
            Log.d(TAG, "CreateNewGuestUser");
            return true;
        }else {
            if (User.getInstance().getPermissionLevel() < PermissionLevel.GUEST){
                userAplication = User.getInstance();
                return true;
            }else {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        User.authUser("guest", "guest", null, "guest" , PermissionLevel.GUEST);
                        Log.d(TAG, "LoadReadyUser");
                    }
                });
                t.start();
                return false;
            }
        }
    }

    @Override
    public void setServerIP() {

    }

    @Override
    public boolean setServerConnection() {
        if(ConnectionController.start()){
            clientThread = ConnectionController.getClientThread();
            //Log.d(TAG, "CONNECTION_START | SERVER_IP -> "+ getServerIP());
            return true;
        }else {
            Log.e(TAG, "CONNECTION_NOT_STARTED | CONNECT_IP_ADDRESS -> "+ getServerIP());
            return false;
        }

    }

    @Override
    public String getServerIP() {
        Log.d(TAG, "IP READY");
        return SERVERIP;
    }

    @Override
    public User getUserApplication() {
        return userAplication;
    }

    @Override
    public int getUserPermission() {
        return userAplication.getPermissionLevel();
    }

    @Override
    public Session getSession() {
        return userAplication.getSession();
    }

    @Override
    public boolean sendAuth() {
        PacketManager.PacketGenerator(userAplication, new AuthPacket(userAplication.getLoginUser(), "guest"));
        if(clientThread.checkSendPacket){
            Log.d(TAG, "AuthOK | CONNECT_IP_ADDRESS -> "+ getServerIP());
            return false;
        }else {
            Log.e(TAG, "AuthFailed | CONNECT_IP_ADDRESS -> "+ getServerIP());
            return true;
        }
    }

    @Override
    public boolean checkServerConnection() {
        return false;
    }

    @Override
    public boolean reconnectServer() {
        return false;
    }

    @Override
    public boolean sendReportPacket(int userId, String message, int typeReport, int ReportOnUserId) {
//        PacketManager.PacketGenerator(getUserApplication(), new ReportPacket(userId, message, typeReport, ReportOnUserId) );
        return true;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
