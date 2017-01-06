package org.arcticsoft.bluebearlive.socket;

import android.os.AsyncTask;
import android.util.Log;

import com.mrheadshot62.api.MultiPacket;
import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.streams.BlueBearInputStream;

import org.arcticsoft.bluebearlive.core.handler.MainHandler;

import java.io.IOException;

/**
 * Created by novak on 05.01.2017.
 */

class ServerListener extends AsyncTask<Void, MultiPacket, Void> {
    private final BlueBearInputStream input;

    ServerListener(BlueBearInputStream inputStream) {
        this.input = inputStream;
    }

    @Override
    protected Void doInBackground(Void[] voids) {
        while (input!=null){
            try {
                MultiPacket p = input.readMultiPacket();
                publishProgress(p);
            } catch (IOException e) {
                Log.w("ServerListener", "Connection Reset");
                return null;
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
