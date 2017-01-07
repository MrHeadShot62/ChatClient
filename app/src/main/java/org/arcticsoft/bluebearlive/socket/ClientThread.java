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
        Log.d(TAG, "Старт потока подключения к серверу");
        try{
            Log.d(TAG, "Попытка подключения к серверу");
            Socket socket = new Socket(ip, 27015);
            BlueBearInputStream input = new BlueBearInputStream(socket.getInputStream());
            outputStream = new BlueBearOutputStream(socket.getOutputStream());
            new ServerListener(input).execute();
            ConnectionController.isStarted = true;
            Log.d(TAG, String.format("Подключение к серверу успешно! IP_ADDRESS -> %s", socket.getInetAddress().getHostAddress()));
        }catch(Exception e){
            Log.e(TAG, "Ошибка подключения к серверу", e);
        }
    }

    public boolean sendMultiPacket(final MultiPacket multiPacket){
        Log.d(TAG, "Отправка мульти пакета на сервер");
        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    Log.d(TAG, "Попытка отправки пакета на сервер. Осталось - "+countReconnect+" попыток");
                    if (ConnectionController.isStarted){
                        try{
                            outputStream.writeMultiPacket(multiPacket);
                            countReconnect = 0;
                            checkSendPacket = true;
                        } catch (Exception e) {
                            countReconnect--;
                            Log.e(TAG, "Ошибка отправки пакета", e);
                        }
                    }else {
                        try {
                            sleep(3000);
                            Log.d(TAG, "Подключение к серверу отсутствует. Ваш пакет не отправлен");
                            countReconnect--;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }while (countReconnect != 0);
                countReconnect = 5;
            }
        }).start();

        if(this.checkSendPacket){
            Log.d(TAG, "Ваш пакет был отправлен");
            resetStatusPacketManager();
            return true;
        }else {
            Log.e(TAG, "Возникала ошибка! Ваш пакет не отправлен");
            return false;
        }
    }

    private void resetStatusPacketManager(){
        this.checkSendPacket = false;
    }

}
