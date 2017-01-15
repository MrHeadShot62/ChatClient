package org.arcticsoft.bluebearlive.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mrheadshot62.api.types.CommandPacket;
import com.mrheadshot62.api.types.UserPacket;

import org.arcticsoft.bluebearlive.R;
import org.arcticsoft.bluebearlive.activity.SignInActivity;
import org.arcticsoft.bluebearlive.core.logic.Application;
import org.arcticsoft.bluebearlive.core.logic.DataBase;
import org.arcticsoft.bluebearlive.core.logic.PacketManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment {


    private Button goCommandPacket;
    private Button goUserPacket;
    private Button goAuthPacket;
    private TextView textStatus;
    private Button connectToServer;
    private Button dellUserFromDB;

    public TestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main, null);
        Application application = Application.getInstance();
        application.setContext(getActivity().getApplicationContext());
//
        textStatus = (TextView) v.findViewById(R.id.status_request);
        connectToServer = (Button) v.findViewById(R.id.buttonConnect);
        goAuthPacket = (Button) v.findViewById(R.id.buttonAuth);
        goCommandPacket = (Button) v.findViewById(R.id.buttonSendCommandPacket);
        dellUserFromDB = (Button) v.findViewById(R.id.delUserFromDB);

        connectToServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                application.setServerConnection();
            }
        });

        v.findViewById(R.id.buttonLoadAuth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Application.getInstance().loadUser();
            }
        });

        v.findViewById(R.id.buttonRetryAuth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Application.getInstance().setGuest();
            }
        });

        goCommandPacket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PacketManager.PacketGenerator(application.getUserApplication(), new CommandPacket(22, "sd"));
            }
        });
        dellUserFromDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DataBase.getInstance().deleteUser();
                    Toast.makeText(getContext(), "User Del", Toast.LENGTH_SHORT).show();
                    Log.e("DellUserFromDB", "DEL");
                    startActivity(new Intent(getActivity().getApplicationContext(), SignInActivity.class));
                } catch (java.lang.InstantiationException e) {
                    Log.e("DellUserFromDB", "NOT DEL", e);
                    e.printStackTrace();
                }

            }
        });

        return v;
    }

}
