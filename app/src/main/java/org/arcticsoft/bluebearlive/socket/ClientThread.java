package org.arcticsoft.bluebearlive.socket;

import android.util.Log;

import com.mrheadshot62.api.MultiPacket;
import com.mrheadshot62.api.streams.BlueBearInputStream;
import com.mrheadshot62.api.streams.BlueBearOutputStream;

import java.net.Socket;
/**
 * Created by novak on 05.01.2017.
 */

public class ClientThread extends Thread {
    private final String ip;
    private ServerListener listener;
    private BlueBearOutputStream outputStream;
    private int countReconnect = 5;
    public boolean checkSendPacket = false;

    ClientThread(String ip) {
        this.ip = ip;
    }

    @Override
    public void run() {
        Log.d("CT", "227");
        try{
            Log.d("CT", "228");
            Socket socket = new Socket(ip, 27015);
            BlueBearInputStream input = new BlueBearInputStream(socket.getInputStream());
            outputStream = new BlueBearOutputStream(socket.getOutputStream());
            new ServerListener(input).execute();
            ConnectionController.isStarted = true;
            Log.d("CT", "229");
        }catch(Exception e){
            Log.e("CT", "EVREI", e);
        }
    }

    public boolean sendMultiPacket(final MultiPacket multiPacket){
        Log.d("CT", "230");
        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    if (ConnectionController.isStarted){
                        try{
                            outputStream.writeMultiPacket(multiPacket);
                            countReconnect = 0;
                            checkSendPacket = true;
                        } catch (Exception e) {
                            countReconnect--;
                            Log.e("CT", "SENDMP", e);
                        }
                    }else {
                        try {
                            sleep(3000);
                            Log.w("CT", "ResendMultiPacket");
                            countReconnect--;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }while (countReconnect != 0);
                countReconnect = 5;
            }
        }).run();
        return checkSendPacket;
    }

    public void resetStatusPacketManager(){
        this.checkSendPacket = false;
    }

}
