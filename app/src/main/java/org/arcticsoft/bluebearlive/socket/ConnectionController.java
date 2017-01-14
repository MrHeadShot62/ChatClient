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

    private static BlueBearOutputStream output;
    private static BlueBearInputStream input;
    public static boolean isStarted = false;

    public static boolean start(String ip){
        if (!isStarted) {
            try {
                AsyncTask<Void, Void, Boolean> task = new ServerConnection(ip);
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                return task.get();
            } catch (Exception e) {
                Log.e("NewClientThread", "Не удалось подключиться к серверу");
                return false;
            }
        }else{
            return false;
        }
    }

    public static BlueBearInputStream getInput() {
        return input;
    }

    public static BlueBearOutputStream getOutput() {
        return output;
    }

    static class ServerConnection extends AsyncTask<Void, Void, Boolean>{
        private String TAG = "ServerConnection";
        private String ip;


        public ServerConnection(String ip) {
            this.ip = ip;
        }

        @Override
        protected void onPostExecute(Boolean started) {
            super.onPostExecute(started);
            if (started){
                Toast.makeText(Application.getInstance().getContext(), "Connected to server!", Toast.LENGTH_LONG).show();
                Log.d(TAG, String.format("connected to %s", ip));
                ConnectionController.isStarted = true;
            }else{
                Toast.makeText(Application.getInstance().getContext(), "Failed to connect", Toast.LENGTH_LONG).show();
                Log.d(TAG, String.format("failed to connect %s", ip));
                ConnectionController.isStarted = false;
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
                ConnectionController.input = new BlueBearInputStream(socket.getInputStream());
                ConnectionController.output = new BlueBearOutputStream(socket.getOutputStream());
                publishProgress();
            }catch(Exception e){
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
