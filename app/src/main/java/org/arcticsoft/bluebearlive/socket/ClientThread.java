package org.arcticsoft.bluebearlive.socket;

import android.os.Debug;
import android.util.Log;

import com.mrheadshot62.api.MultiPacket;
import com.mrheadshot62.api.streams.BlueBearInputStream;
import com.mrheadshot62.api.streams.BlueBearOutputStream;

import java.io.IOException;
import java.net.Socket;
/**
 * Created by novak on 05.01.2017.
 */

public class ClientThread extends Thread {
    private final String ip;
    private ServerListener listener;
    private BlueBearOutputStream outputStream;
    private Socket socket;
    BlueBearInputStream input;

    public ClientThread(String ip) {
        this.ip = ip;
    }

    @Override
    public void run() {
        Log.d("CT", "227");
        try{
            Log.d("CT", "228");
            socket = new Socket(ip, 27015);
            input = new BlueBearInputStream(socket.getInputStream());
            outputStream = new BlueBearOutputStream(socket.getOutputStream());
            new ServerListener(input).execute();
            Log.d("CT", "229");
        }catch(Exception e){
            Log.e("CT", "EVREI", e);
        }
    }

    public void sendMultiPacket(final MultiPacket multiPacket){
        Log.d("CT", "230");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    outputStream.writeMultiPacket(multiPacket);
                } catch (Exception e) {
                    Log.e("CT", "SENDMP", e);
                }
            }
        }).start();
    }
}
