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

    private static final String TAG = "ClientThread";

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
        Log.d(TAG, "Start Thread connect server");
        try{
            Log.d(TAG, "Connection attempt");
            Socket socket = new Socket(ip, 27015);
            BlueBearInputStream input = new BlueBearInputStream(socket.getInputStream());
            outputStream = new BlueBearOutputStream(socket.getOutputStream());
            new ServerListener(input).execute();
            ConnectionController.isStarted = true;
            Log.d(TAG, String.format("Connected! IP_ADDRESS -> %s", socket.getInetAddress().getHostAddress()));
        }catch(Exception e){
            Log.e(TAG, "Failed connected", e);
        }
    }

    public synchronized boolean sendMultiPacket(final MultiPacket multiPacket){
        Log.d(TAG, "Send MultiPacket on Server");
        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    Log.w(TAG, "Attempt sending packet on Server. Remaining - "+countReconnect);
                    if (ConnectionController.isStarted){
                        try{
                            outputStream.writeMultiPacket(multiPacket);
                            countReconnect = 0;
                            setCheckSendPacket(true);
                            Log.e(TAG, "Packet sended");
                        } catch (Exception e) {
                            countReconnect--;
                            Log.e(TAG, "Error sending Packet", e);
                        }
                    }else {
                        try {
                            sleep(3000);
                            Log.e(TAG, "Connect to Server off. Packet not sended");
                            countReconnect--;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }while (countReconnect != 0);
                countReconnect = 5;
            }
        }).start();

        if(isCheckSendPacket()){
            Log.d(TAG, "Packet is sending");
            resetStatusPacketManager();
            return true;
        }else {
            Log.e(TAG, "Error! Packet not sending");
            return false;
        }
    }

    private void resetStatusPacketManager(){
        this.checkSendPacket = false;
    }

    public synchronized boolean isCheckSendPacket() {
        return checkSendPacket;
    }

    public synchronized void setCheckSendPacket(boolean checkSendPacket) {
        this.checkSendPacket = checkSendPacket;
    }

}
