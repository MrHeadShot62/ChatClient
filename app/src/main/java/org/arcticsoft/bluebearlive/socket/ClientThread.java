package org.arcticsoft.bluebearlive.socket;

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

    public ClientThread(String ip) {
        this.ip = ip;
    }

    @Override
    public void run() {
        try{
            Socket socket = new Socket(ip, 5555);
            BlueBearInputStream input = new BlueBearInputStream(socket.getInputStream());
            outputStream = new BlueBearOutputStream(socket.getOutputStream());
            new ServerListener(input).execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void sendMultiPacket(final MultiPacket multiPacket){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    outputStream.writeMultiPacket(multiPacket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
