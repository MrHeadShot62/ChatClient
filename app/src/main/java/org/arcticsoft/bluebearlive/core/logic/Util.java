package org.arcticsoft.bluebearlive.core.logic;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.mrheadshot62.api.MultiPacket;
import com.mrheadshot62.api.types.ReportPacket;
import com.mrheadshot62.api.types.answer.ServerAnswerAuthUserPacket;

import org.arcticsoft.bluebearlive.socket.ConnectionController;

import java.util.concurrent.ExecutionException;

public class Util{

    private static final String TAG = "UTIL";
    public static Activity mainActivity;
    private static Util instance;
    private static Context context;

    private Util(Context context){
        Util.context = context;
    }

    public static void createUtil(Context context) throws Exception {
        if (instance == null){
            instance = new Util(context);
        }else {
            throw new InstantiationException("Util is Already exist");
        }

    }

    public static Util getInstance() throws Exception{
        if(instance != null){
           return instance;
        }
        throw new NullPointerException("Util is not exist");
    }

    public static boolean sendPacket(MultiPacket p){
        AsyncTask<MultiPacket, Void, Boolean> task = new SendMultiPacket();
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, p);
        try {
            return task.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean setServerConnection() {
        if (!ConnectionController.isConnected){
            Log.d(TAG, "CONNNNNECTED!");
            return ConnectionController.start(Application.getServerIP());
        }
        Toast.makeText(getContext(), "IS CONNECTED!", Toast.LENGTH_SHORT).show();
        return false;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        Util.context = context;
    }

    public static User getUserApplication(){
        return User.getInstance();
    }

    public static void sendReportPacket(int userId, String message, byte typeReport, int ReportOnUserId) {
        PacketManager.PacketGenerator(getUserApplication(), new ReportPacket(userId, message, typeReport, ReportOnUserId) );
    }

    public static void setUserApplication(ServerAnswerAuthUserPacket serverAnswerAuthUserPacket){
        try {
            User.initAuthUser(serverAnswerAuthUserPacket);
        } catch (InstantiationException e) {
            Log.e(TAG, "Not Init Auth User");
            e.printStackTrace();
        }
    }

    public static void setUserApplication(){
        if(!User.userIsAuth()){
            User.initGuestUser();
        }else {
            // TODO initAuthUserFromDB(com.mrheadshot62.api.types.User user, String session) реализовать выборку текущего пользователя с локальной базы дынных
        }
    }

    public static void setUserApplicationTest(){
        User.initTestUser();
    }

    public static void userApplicationSingOut(){
        User.initGuestUser();
    }

    private static class SendMultiPacket extends AsyncTask<MultiPacket, Void, Boolean> {
        private static int countReconnect=5;

        @Override
        protected Boolean doInBackground(MultiPacket... multiPackets) {
            if(!ConnectionController.isConnected){
                Log.d("RECONNECT", "Reconnect to server "+ Application.getServerIP());
                setServerConnection();
            }
            do {
                Log.w(TAG, "Attempt sending packet on Server. Remaining - "+countReconnect);
                if (ConnectionController.isStarted){
                    try{
                        ConnectionController.getOutput().writeMultiPacket(multiPackets[0]);
                        countReconnect = 5;
                        Log.d(TAG, "Packet sended");
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
