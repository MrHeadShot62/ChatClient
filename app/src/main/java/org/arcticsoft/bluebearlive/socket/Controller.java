package org.arcticsoft.bluebearlive.socket;

import com.mrheadshot62.api.MultiPacket;

/**
 * Created by novak on 05.01.2017.
 */

public class Controller {
    private static ClientThread clientThread;

    public static void start(){
        clientThread = new ClientThread("127.0.0.1");
        clientThread.start();
    }

    public static void sendMultiPacket(MultiPacket packet){
        clientThread.sendMultiPacket(packet);
    }
}
