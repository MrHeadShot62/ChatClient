package org.arcticsoft.bluebearlive.socket;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.mrheadshot62.api.MultiPacket;
import com.mrheadshot62.api.streams.BlueBearInputStream;
import com.mrheadshot62.api.streams.BlueBearOutputStream;

import org.arcticsoft.bluebearlive.core.logic.Application;

import java.net.Socket;

/**
 * Created by novak on 05.01.2017.
 */

public class ConnectionController {
    private static String TAG = "ServerConnection";
    private static BlueBearOutputStream output;
    private static BlueBearInputStream input;
    public static boolean isStarted = false;
    public static boolean isConnected=false;


    public static boolean start(String ip){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Application.getInstance().getContext(), "Connection attempt", Toast.LENGTH_SHORT).show();
                try{
                    Log.d(TAG, "Connection attempt");
                    Socket socket = new Socket(ip, 27015);
                    ConnectionController.input = new BlueBearInputStream(socket.getInputStream());
                    ConnectionController.output = new BlueBearOutputStream(socket.getOutputStream());
                    Toast.makeText(Application.getInstance().getContext(), "Connected to server!", Toast.LENGTH_LONG).show();
                    Log.d(TAG, String.format("connected to %s", ip));
                    ConnectionController.isStarted = true;
                    ConnectionController.isConnected =true;
                }catch(Exception e){
                    Toast.makeText(Application.getInstance().getContext(), "Failed to connect", Toast.LENGTH_LONG).show();
                    Log.d(TAG, String.format("failed to connect %s", ip));
                    ConnectionController.isStarted = false;
                }
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return isConnected;
    }

    public static BlueBearInputStream getInput() {
        return input;
    }

    public static BlueBearOutputStream getOutput() {
        return output;
    }
}
