package org.arcticsoft.bluebearlive.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.arcticsoft.bluebearlive.R;
import org.arcticsoft.bluebearlive.core.logic.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    User newUser = new User("Donsy", "Dima", "Ukraine");
}
