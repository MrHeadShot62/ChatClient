package org.arcticsoft.bluebearlive.socket;

import com.mrheadshot62.api.streams.BlueBearInputStream;
import com.mrheadshot62.api.streams.BlueBearOutputStream;

import java.net.Socket;
/**
 * Created by novak on 05.01.2017.
 */

public class ClientThread extends Thread {
    private final String ip;
    private ServerListener listener;

    public ClientThread(String ip) {
        this.ip = ip;
    }

    @Override
    public void run() {
        try{
            Socket socket = new Socket(ip, 5555);
            BlueBearInputStream input = new BlueBearInputStream(socket.getInputStream());
            BlueBearOutputStream outputStream = new BlueBearOutputStream(socket.getOutputStream());
            new ServerListener(input).execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
