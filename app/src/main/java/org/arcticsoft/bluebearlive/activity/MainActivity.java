package org.arcticsoft.bluebearlive.activity;

import android.media.Image;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mrheadshot62.api.types.AuthPacket;
import com.mrheadshot62.api.types.CommandPacket;
import com.mrheadshot62.api.types.ImagePacket;
import com.mrheadshot62.api.types.UserPacket;

import org.arcticsoft.bluebearlive.R;
import org.arcticsoft.bluebearlive.core.logic.Application;
import org.arcticsoft.bluebearlive.core.logic.PacketManager;
import org.arcticsoft.bluebearlive.core.logic.User;
import org.arcticsoft.bluebearlive.socket.ConnectionController;

public class MainActivity extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Application application = Application.getInstance();
        application.setServerConnection();
        application.sendAuth();

        PacketManager.PacketGenerator(application.getUserApplication(), new UserPacket("name"));
        PacketManager.PacketGenerator(application.getUserApplication(), new CommandPacket(22, "sd"));
    }

}
