package org.arcticsoft.bluebearlive.socket;

import android.util.Log;

import com.mrheadshot62.api.MultiPacket;

/**
 * Created by novak on 05.01.2017.
 */

public class ConnectionController {

    private static ClientThread clientThread;
    public static boolean isStarted = false;


    public static boolean start(){
        try {
            clientThread = new ClientThread("192.168.1.6");
            clientThread.start();
            return true;
        }catch (Exception e){
            Log.e("NewClientThread", "Не удалось подключиться к серверу");
            return false;
        }

    }

    public static void sendMultiPacket(MultiPacket packet){
        clientThread.sendMultiPacket(packet);
    }

    public static ClientThread getClientThread() {
        return clientThread;
    }
}
