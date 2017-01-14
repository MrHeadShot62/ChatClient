package org.arcticsoft.bluebearlive.core.logic;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import com.mrheadshot62.api.MultiPacket;
import com.mrheadshot62.api.PermissionLevel;
import com.mrheadshot62.api.types.AuthPacket;
import com.mrheadshot62.api.types.ReportPacket;

import org.arcticsoft.bluebearlive.core.aLogic.AApplication;
import org.arcticsoft.bluebearlive.socket.ConnectionController;

import java.util.concurrent.ExecutionException;

/**
 * Created by DmitriyRoot on 06.01.2017.
 */

public class Application extends AApplication {

    private static final String TAG = "APPLICATION";
    private static final String SERVERIP = "192.168.0.102";

    private User userAplication;
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

    public void initDataBase(Context context){
        new DataBase(context);
    }

    public Application() {

    }

    public void setGuest(){
        userAplication = User.guestUser("guest", "TestName", "TestCountry");
        Log.d(TAG, "CreateNewGuestUser");
    }

    public void authUser(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                User.authUser("guest", "guest", null, "guest" , getUserPermission());
                Log.d(TAG, "LoadReadyUser");
            }
        });
        t.start();
    }

    public void loadUser(){
        if (User.getInstance() == null){
            Log.d("user", "null "+getUserApplication());
        }else {
            Log.d("user", "notnull "+getUserPermission());
        }
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
        return ConnectionController.start(getServerIP());
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
        return true;
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
    public boolean sendReportPacket(int userId, String message, byte typeReport, int ReportOnUserId) {
        PacketManager.PacketGenerator(getUserApplication(), new ReportPacket(userId, message, typeReport, ReportOnUserId) );
        return true;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
        initDataBase(context);
        try {
            com.mrheadshot62.api.types.User u = DataBase.getInstance().getUser();
            if (u!=null){
                Log.d("DB", "loaded "+u.getLogin());
            }else{
                Log.d("DB", "empty data base");
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public boolean sendPacket(MultiPacket p){
        AsyncTask<MultiPacket, Void, Boolean> task = new SendMultiPacket();
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, p);
        try {
            return task.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static class SendMultiPacket extends AsyncTask<MultiPacket, Void, Boolean> {
        private static int countReconnect=5;

        @Override
        protected Boolean doInBackground(MultiPacket... multiPackets) {
            do {
                Log.w(TAG, "Attempt sending packet on Server. Remaining - "+countReconnect);
                if (ConnectionController.isStarted){
                    try{
                        ConnectionController.getOutput().writeMultiPacket(multiPackets[0]);
                        countReconnect = 5;
                        Log.e(TAG, "Packet sended");
                        return true;
                    } catch (Exception e) {
                        countReconnect--;
                        Log.e(TAG, "Error sending Packet", e);
                    }
                }else {
                    try {
                        Thread.currentThread().sleep(3000);
                        Log.e(TAG, "Connect to Server off. Packet not sended");
                        countReconnect--;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }while (countReconnect >= 0);
            countReconnect = 5;
            return false;
        }
    }
}
