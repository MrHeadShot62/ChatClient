package org.arcticsoft.bluebearlive.core.logic;

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
    private static final String SERVERIP = "194.117.253.99";

    private User userAplication;
    private String serverIP;
    private ClientThread clientThread;

    private static Application instance = null;

    public static Application getInstance(){
        if(instance == null){
            instance = new Application();
            return  instance;
        }else {
            return instance;
        }
    }

    public Application() {

    }

    @Override
    public boolean setUserApplication() {
        if(User.getInstance() == null){
            userAplication = User.guestUser("TestLogin", "TestName", "TestCountry");
            Log.d(TAG, "CreateNewGuestUser");
            return true;
        }else {
            userAplication = User.getInstance();
            Log.d(TAG, "LoadReadyUser");
            return false;
        }
    }

    @Override
    public void setServerIP() {

    }

    @Override
    public boolean setServerConnection() {
        if(ConnectionController.start()){
            clientThread = ConnectionController.getClientThread();
            Log.d(TAG, "CONNECTION_START | SERVER_IP -> "+ getServerIP());
            return true;
        }else {
            Log.d(TAG, "CONNECTION_NOT_STARTED | CONNECT_IP_ADDRESS -> "+ getServerIP());
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
        PacketManager.PacketGenerator(userAplication, new AuthPacket(userAplication.getLoginUser(), "asdsa"));
        if(clientThread.checkSendPacket){
            Log.d(TAG, "Авторизация успешна | CONNECT_IP_ADDRESS -> "+ getServerIP());
            return false;
        }else {
            Log.d(TAG, "Авторизация провалена | CONNECT_IP_ADDRESS -> "+ getServerIP());
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
}
