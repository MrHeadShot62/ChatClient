package org.arcticsoft.bluebearlive.core.logic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;


import com.mrheadshot62.api.MultiPacket;
import com.mrheadshot62.api.PermissionLevel;
import com.mrheadshot62.api.types.AuthPacket;
import com.mrheadshot62.api.types.ReportPacket;
import com.mrheadshot62.api.types.answer.ServerAnswerAuthUserPacket;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKSdk;

import org.arcticsoft.bluebearlive.activity.LoginVkActivity;
import org.arcticsoft.bluebearlive.core.aLogic.AApplication;
import org.arcticsoft.bluebearlive.socket.ConnectionController;

import java.util.concurrent.ExecutionException;

/**
 * Created by DmitriyRoot on 06.01.2017.
 */

public class Application extends android.app.Application{

    private static final String TAG = "APPLICATION";
    private static final String SERVERIP = "194.117.253.208";
    private static Activity activity;

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


    @Override
    public void onCreate() {
        super.onCreate();
        vkAccessTokenTracker.startTracking();
        VKSdk.initialize(getApplicationContext());
    }

    public static Activity getActivity() {
        return activity;
    }

    public static void setActivity(Activity activity) {
        Application.activity = activity;
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

    public void authUser(ServerAnswerAuthUserPacket serverAnswerAuthUserPacket){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                User.authUser(serverAnswerAuthUserPacket);
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

    public boolean setUserApplication() {
        setGuest();
        return true;
    }

    public boolean setUserApplication(ServerAnswerAuthUserPacket serverAnswerAuthUserPacket) {
        User.authUser(serverAnswerAuthUserPacket);
        return true;
    }

    public boolean setUserApplication(boolean b) {
        User.authUser();
        return true;
    }


    public void setServerIP() {

    }

    public boolean setServerConnection() {
        if (!ConnectionController.isConnected){
            return ConnectionController.start(getServerIP());
        }
        Toast.makeText(getContext(), "IS CONNECTED!", Toast.LENGTH_SHORT).show();
        return false;
    }

    public String getServerIP() {
        Log.d(TAG, "IP READY");
        return SERVERIP;
    }

    public User getUserApplication() {
        return userAplication;
    }

    public int getUserPermission() {
        return userAplication.getPermissionLevel();
    }

    public Session getSession() {
        return userAplication.getSession();
    }

    public boolean sendAuth(String login, String pass) {
        PacketManager.PacketGenerator(userAplication, new AuthPacket(login, pass));
        return true;
    }

    public boolean checkServerConnection() {
        return false;
    }

    public boolean reconnectServer() {
        return false;
    }

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
            if(!ConnectionController.isConnected){
                Log.d("RECONNECT", "Reconnect to server "+ Application.SERVERIP);
                Application.getInstance().setServerConnection();
            }
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

    VKAccessTokenTracker vkAccessTokenTracker = new VKAccessTokenTracker() {
        @Override
        public void onVKAccessTokenChanged(VKAccessToken oldToken, VKAccessToken newToken) {
            if (newToken == null) {
                Toast.makeText(Application.this, "AccessToken invalidated", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Application.this, LoginVkActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }
    };
}
