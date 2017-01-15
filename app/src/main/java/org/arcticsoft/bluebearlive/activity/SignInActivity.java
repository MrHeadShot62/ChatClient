package org.arcticsoft.bluebearlive.activity;

import com.dd.processbutton.iml.ActionProcessButton;
import com.ulogin.sdk.UloginAuthActivity;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class SignInActivity extends AppCompatActivity {
    public static final int REQUEST_ULOGIN=228;
    public static final String EXTRAS_ENDLESS_MODE = "EXTRAS_ENDLESS_MODE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sing_in_ac);
        Intent intent = new Intent(getApplicationContext(), SocialViewHolder.SocialItem.class);
        String[] providers = getResources().getStringArray(R.array.ulogin_providers);
        String[] mandatory_fields = new String[]{"first_name", "last_name"};
        String[] optional_fields = new String[]{"email", "nickname", "bdate", "sex", "phone", "photo", "photo_big", "city", "country"};
        intent.putExtra(UloginAuthActivity.PROVIDERS, new ArrayList<>(Arrays.asList(providers)));
        intent.putExtra(UloginAuthActivity.FIELDS, new ArrayList<>(Arrays.asList(mandatory_fields)));
        intent.putExtra(UloginAuthActivity.OPTIONAL, new ArrayList<>(Arrays.asList(optional_fields)));

        startActivityForResult(intent, REQUEST_ULOGIN);
        final EditText editLogin = (EditText) findViewById(R.id.editEmail);
        final EditText editPassword = (EditText) findViewById(R.id.editPassword);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // requestCode должно сравниваться со значением константы,
        //   указанной при инициализации Intent
        if (requestCode == REQUEST_ULOGIN) {
            //получаем данные ответа:
            HashMap userdata = (HashMap) intent.getSerializableExtra (UloginAuthActivity.USERDATA);

            switch (resultCode) {
                case RESULT_OK:
                    //если авторизация прошла успешно, то приветствуем пользователя
                    Toast.makeText(this,
                            "Hello, " + userdata.get("first_name") + " "
                                    + userdata.get("last_name") + "!",
                            Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    //если авторизация завершилась с ошибкой, то выводим причину
                    if(userdata.get("error").equals("canceled")) {
                        Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Error: "+userdata.get("error"),
                                Toast.LENGTH_SHORT).show();
                    }
        final ActionProcessButton btnSignIn = (ActionProcessButton) findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Application.getInstance().sendAuth(editLogin.getText().toString(), editPassword.getText().toString());
                btnSignIn.setProgress(55);

            }
        }
    }


}
