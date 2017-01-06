package org.arcticsoft.bluebearlive.socket;

import com.mrheadshot62.api.MultiPacket;

/**
 * Created by novak on 05.01.2017.
 */

public class ConnectionController {
    private static ClientThread clientThread;
    public static boolean isStarted = false;


    public static void start(){
        clientThread = new ClientThread("192.168.1.6");
        clientThread.start();
    }

    public static void sendMultiPacket(MultiPacket packet){
        clientThread.sendMultiPacket(packet);
    }
}
