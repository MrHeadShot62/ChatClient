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

import org.arcticsoft.bluebearlive.R;
import org.arcticsoft.bluebearlive.activity.SignInActivity;
import org.arcticsoft.bluebearlive.core.logic.DataBase;
import org.arcticsoft.bluebearlive.core.logic.PacketManager;
import org.arcticsoft.bluebearlive.core.logic.Util;

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
        View view = inflater.inflate(R.layout.app_main_test_poligon_activity, null);
//
        textStatus = (TextView) view.findViewById(R.id.status_request);
        connectToServer = (Button) view.findViewById(R.id.buttonConnect);
        goAuthPacket = (Button) view.findViewById(R.id.buttonAuth);
        goCommandPacket = (Button) view.findViewById(R.id.buttonSendCommandPacket);
        dellUserFromDB = (Button) view.findViewById(R.id.delUserFromDB);

        connectToServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.setServerConnection();
            }
        });

//        view.findViewById(R.id.buttonLoadAuth).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Application.getInstance().loadUser();
//            }
//        });

//        view.findViewById(R.id.buttonRetryAuth).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Application.getInstance().setGuest();
//            }
//        });

        goCommandPacket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PacketManager.PacketGenerator(Util.getUserApplication(), new CommandPacket(22, "sd"));
            }
        });

        dellUserFromDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DataBase.getInstance().deleteUser();
                    Toast.makeText(view.getContext(), "Sing Out", Toast.LENGTH_SHORT).show();
                    Log.d("LOGIN", "Sing Out");
                    Util.userApplicationSingOut();
                    startActivity(new Intent(getActivity().getApplicationContext(), SignInActivity.class));
                } catch (java.lang.InstantiationException e) {
                    Log.d("DellUserFromDB", "NOT SING OUT", e);
                    e.printStackTrace();
                }

            }
        });

        return view;
    }

}
