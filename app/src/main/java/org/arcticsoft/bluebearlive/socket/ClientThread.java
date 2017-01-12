package org.arcticsoft.bluebearlive.socket;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.mrheadshot62.api.MultiPacket;
import com.mrheadshot62.api.streams.BlueBearInputStream;
import com.mrheadshot62.api.streams.BlueBearOutputStream;

import org.arcticsoft.bluebearlive.core.logic.Application;

import java.net.Socket;
import java.util.concurrent.ExecutionException;

/**
 * Created by novak on 05.01.2017.
 */

public class ClientThread{

    private static final String TAG = "ClientThread";

    private String ip;
    private ServerListener listener;
    private BlueBearOutputStream outputStream;
    private int countReconnect = 5;

    public boolean checkSendPacket = false;

    ClientThread(String ip) {
        this.ip = ip;
    }

    public boolean start(){
        new Connector().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        return true;
    }

    public synchronized boolean send(MultiPacket p){
        AsyncTask<MultiPacket, Void, Boolean> task = new SendMultiPacket();
        boolean success=false;
        try {
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, p);
            success = task.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return success;
    }

    private class SendMultiPacket extends AsyncTask<MultiPacket, Void, Boolean>{
        @Override
        protected Boolean doInBackground(MultiPacket... multiPackets) {
            do {
                Log.w(TAG, "Attempt sending packet on Server. Remaining - "+countReconnect);
                if (ConnectionController.isStarted){
                    try{
                        outputStream.writeMultiPacket(multiPackets[0]);
                        countReconnect = 0;
                        setCheckSendPacket(true);
                        Log.e(TAG, "Packet sended");
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
            }while (countReconnect != 0);
            countReconnect = 5;
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

    private class Connector extends AsyncTask<Void, Void, Boolean>{
        private String ipadr;
        private BlueBearInputStream input;
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean){
                Toast.makeText(Application.getInstance().getContext(), String.format("Connected! IP_ADDRESS -> %s", ipadr), Toast.LENGTH_LONG).show();
                ConnectionController.isStarted = true;
            }else{
                Toast.makeText(Application.getInstance().getContext(), "Failed to connect", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            new ServerListener(input).execute();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try{
                Log.d(TAG, "Connection attempt");
                Socket socket = new Socket(ip, 27015);
                input = new BlueBearInputStream(socket.getInputStream());
                outputStream = new BlueBearOutputStream(socket.getOutputStream());
                ipadr = socket.getInetAddress().getHostAddress();
                publishProgress();
                Log.d(TAG, String.format("Connected! IP_ADDRESS -> %s", socket.getInetAddress().getHostAddress()));
            }catch(Exception e){
                Log.e(TAG, "Failed connected", e);
                return false;
            }
            return true;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(Application.getInstance().getContext(), "Connection attempt", Toast.LENGTH_SHORT).show();
        }
    }
}
