package org.arcticsoft.bluebearlive.activity;

import com.dd.processbutton.iml.ActionProcessButton;
import com.mrheadshot62.api.Packet;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.AppLaunchChecker;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.arcticsoft.bluebearlive.R;
import org.arcticsoft.bluebearlive.core.auth.ProgressGenerator;
import org.arcticsoft.bluebearlive.core.logic.Application;
import org.arcticsoft.bluebearlive.socket.ConnectionController;

import java.util.concurrent.TimeUnit;


public class SignInActivity extends AppCompatActivity {

    public static final String EXTRAS_ENDLESS_MODE = "EXTRAS_ENDLESS_MODE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sing_in_ac);

        final EditText editLogin = (EditText) findViewById(R.id.editEmail);
        final EditText editPassword = (EditText) findViewById(R.id.editPassword);

        final ActionProcessButton btnSignIn = (ActionProcessButton) findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Application.getInstance().sendAuth(editLogin.getText().toString(), editPassword.getText().toString());
                btnSignIn.setProgress(55);

            }
        });
    }


}
