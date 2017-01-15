package org.arcticsoft.bluebearlive.activity;

import com.dd.processbutton.iml.ActionProcessButton;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.model.VKScopes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.arcticsoft.bluebearlive.R;
import org.arcticsoft.bluebearlive.core.UserProfile.fragment.SocialViewHolder;
import org.arcticsoft.bluebearlive.core.auth.ProgressGenerator;
import org.arcticsoft.bluebearlive.core.logic.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class SignInActivity extends AppCompatActivity {
    public static final int REQUEST_ULOGIN = 228;
    public static final String EXTRAS_ENDLESS_MODE = "EXTRAS_ENDLESS_MODE";

    EditText editLogin;
    EditText editPassword;
    SignInActivity sing;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sing_in_ac);
        editLogin = (EditText) findViewById(R.id.editEmail);
        editPassword = (EditText) findViewById(R.id.editPassword);
        sing = this;

        final ActionProcessButton btnSignIn = (ActionProcessButton) findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Application.getInstance().sendAuth(editLogin.getText().toString(), editPassword.getText().toString());
//                btnSignIn.setProgress(55);
                VKSdk.login(sing, VKScope.FRIENDS);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
// Пользователь успешно авторизовался
            }
            @Override
            public void onError(VKError error) {
// Произошла ошибка авторизации (например, пользователь запретил авторизацию)
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
