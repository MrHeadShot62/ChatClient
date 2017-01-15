package org.arcticsoft.bluebearlive.core.logic;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKSdk;

import org.arcticsoft.bluebearlive.activity.LoginActivity;

/**
 * Created by DmitriyRoot on 06.01.2017.
 */

public class Application extends android.app.Application{

    private static final String TAG = "APPLICATION";
    private static final String SERVERIP = "194.117.253.208";

    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(getApplicationContext());
        vkAccessTokenTracker.startTracking();
        try {
            Util.createUtil(getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        initDataBase();
    }

    VKAccessTokenTracker vkAccessTokenTracker = new VKAccessTokenTracker() {
        @Override
        public void onVKAccessTokenChanged(VKAccessToken oldToken, VKAccessToken newToken) {
            if (newToken == null) {
                Toast.makeText(Application.this, "AccessToken invalidated", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Application.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Log.d("LoginVk", "CreateIntent");
                startActivity(intent);
            }
        }
    };

    public void initDataBase(){
        new DataBase(getContext());
    }

    public Context getContext() {
        return getApplicationContext();
    }

    public static String getServerIP() {
        return SERVERIP;
    }
}
