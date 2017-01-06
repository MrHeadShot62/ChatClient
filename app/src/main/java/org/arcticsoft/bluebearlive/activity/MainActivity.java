package org.arcticsoft.bluebearlive.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mrheadshot62.api.types.AuthPacket;

import org.arcticsoft.bluebearlive.R;
import org.arcticsoft.bluebearlive.core.logic.PacketManager;
import org.arcticsoft.bluebearlive.core.logic.User;
import org.arcticsoft.bluebearlive.socket.ConnectionController;

public class MainActivity extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConnectionController.start();
        user = User.guestUser("mylogin", "myname", "mycounry");


        PacketManager.PacketGenerator(user, new AuthPacket(user.getLoginUser(), "asdsa"));
    }

}
