package org.arcticsoft.bluebearlive.socket;

import android.os.AsyncTask;

import com.mrheadshot62.api.MultiPacket;
import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.streams.BlueBearInputStream;

import org.arcticsoft.bluebearlive.core.handler.MainHandler;

import java.io.IOException;

/**
 * Created by novak on 05.01.2017.
 */

public class ServerListener extends AsyncTask<Void, MultiPacket, Void> {
    private final BlueBearInputStream input;

    public ServerListener(BlueBearInputStream inputStream) {
        this.input = inputStream;
    }

    @Override
    protected Void doInBackground(Void[] voids) {
        while (input!=null){
            try {
                MultiPacket p = input.readMultiPacket();
                publishProgress(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(MultiPacket... values) {
        super.onProgressUpdate(values);
        new MainHandler(values[0].getPackets());
    }
}
