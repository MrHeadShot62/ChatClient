package org.arcticsoft.bluebearlive.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mrheadshot62.api.types.AuthReadyUser;

import org.arcticsoft.bluebearlive.R;
import org.arcticsoft.bluebearlive.core.logic.PacketManager;
import org.arcticsoft.bluebearlive.core.logic.Util;

public class SecondStepAuth extends AppCompatActivity {

    EditText secretKey;
    Button sendSecretKey;
    TextView infoForUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_second_step);

        secretKey = (EditText) findViewById(R.id.secretKey);
        sendSecretKey = (Button) findViewById(R.id.sendSecretKey);
        infoForUser = (TextView) findViewById(R.id.info_for_user);

        switch (getIntent().getStringExtra("typeAuth")){
            case "REG": sendRegData();
                break;
            case "LOG": sendAuthData();
                break;
            default:
                Toast.makeText(this, "WHAT?", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    private void sendRegData(){
        infoForUser.setText("This is registation!");
        sendSecretKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!secretKey.getText().toString().equals("")){
                    PacketManager.PacketGenerator(Util.getUserApplication(), new AuthReadyUser(secretKey.getText().toString(), getIntent().getIntExtra("userID", 0), true ));
                    Toast.makeText(Util.getContext(), "Packet Registr sended on server", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Util.getContext(), "empty in secret key", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendAuthData(){
        infoForUser.setText("This is auntification!");
        sendSecretKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!secretKey.getText().toString().equals("")){
                    PacketManager.PacketGenerator(Util.getUserApplication(), new AuthReadyUser(secretKey.getText().toString(), getIntent().getIntExtra("userID", 0), false ));
                    Toast.makeText(Util.getContext(), "Packet Login sended on server", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Util.getContext(), "empty in secret key", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}
