package org.arcticsoft.bluebearlive.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mrheadshot62.api.types.CommandPacket;
import com.mrheadshot62.api.types.UserPacket;

import org.arcticsoft.bluebearlive.R;
import org.arcticsoft.bluebearlive.core.logic.Application;
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
        goUserPacket = (Button) v.findViewById(R.id.buttonSendUserPacket);
        goCommandPacket = (Button) v.findViewById(R.id.buttonSendCommandPacket);

        connectToServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                application.setServerConnection();
            }
        });

        goAuthPacket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                application.sendAuth();
            }
        });

        goUserPacket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PacketManager.PacketGenerator(application.getUserApplication(), new UserPacket("name"));
            }
        });

        goCommandPacket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PacketManager.PacketGenerator(application.getUserApplication(), new CommandPacket(22, "sd"));
            }
        });
        return v;
    }

}
