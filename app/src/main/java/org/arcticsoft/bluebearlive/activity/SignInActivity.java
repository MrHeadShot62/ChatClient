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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.sing_in_ac);
//        editLogin = (EditText) findViewById(R.id.editEmail);
//        editPassword = (EditText) findViewById(R.id.editPassword);
//        sing = this;
//
//        final ActionProcessButton btnSignIn = (ActionProcessButton) findViewById(R.id.btnSignIn);
//        btnSignIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Application.getInstance().sendAuth(editLogin.getText().toString(), editPassword.getText().toString());
////                btnSignIn.setProgress(55);
//                VKSdk.login(sing, VKScope.FRIENDS);
//            }
//        });
//    }

    private boolean isResumed = false;

    /**
     * Scope is set of required permissions for your application
     *
     * @see <a href="https://vk.com/dev/permissions">vk.com api permissions documentation</a>
     */
    private static final String[] sMyScope = new String[]{
            VKScope.FRIENDS,
            VKScope.WALL,
            VKScope.PHOTOS,
            VKScope.NOHTTPS,
            VKScope.MESSAGES,
            VKScope.DOCS
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vk_activity_start);
        Log.d("LoginVk", "onCreate");
        VKSdk.wakeUpSession(this, new VKCallback<VKSdk.LoginState>() {
            @Override
            public void onResult(VKSdk.LoginState res) {
                if (isResumed) {
                    switch (res) {
                        case LoggedOut:
                            showLogin();
                            break;
                        case LoggedIn:
                            showLogout();
                            break;
                        case Pending:
                            break;
                        case Unknown:
                            break;
                    }
                }
            }

            @Override
            public void onError(VKError error) {

            }
        });

//        String[] fingerprint = VKUtil.getCertificateFingerprint(this, this.getPackageName());
//        Log.d("Fingerprint", fingerprint[0]);
    }

    private void showLogout() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new LogoutFragment())
                .commitAllowingStateLoss();
    }

    private void showLogin() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new LoginFragment())
                .commitAllowingStateLoss();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isResumed = true;
        if (VKSdk.isLoggedIn()) {
            showLogout();
        } else {
            showLogin();
        }
    }

    @Override
    protected void onPause() {
        isResumed = false;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        VKCallback<VKAccessToken> callback = new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                // User passed Authorization
                Log.d("sad00", "sdsd");
                startTestActivity();
            }

            @Override
            public void onError(VKError error) {
                Log.d("sad00", "sdsd1");
                // User didn't pass Authorization
            }
        };

        if (!VKSdk.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void startTestActivity() {
        startActivity(new Intent(this, TestActivity.class));
    }

    public static class LoginFragment extends android.support.v4.app.Fragment {
        public LoginFragment() {
            super();
            Log.d("LoginVk", "LoginFragmentCreate");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.vk_fragment_login, container, false);
            v.findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    VKSdk.login(getActivity(), sMyScope);
                    Log.d("StartLogin", "Start");
                }
            });
            return v;
        }

    }

    public static class LogoutFragment extends android.support.v4.app.Fragment {
        public LogoutFragment() {
            super();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.vk_fragment_logout, container, false);
            v.findViewById(R.id.continue_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((SignInActivity) getActivity()).startTestActivity();
                }
            });

            v.findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    VKSdk.logout();
                    if (!VKSdk.isLoggedIn()) {
                        ((SignInActivity) getActivity()).showLogin();
                    }
                }
            });
            return v;
        }
    }
}
