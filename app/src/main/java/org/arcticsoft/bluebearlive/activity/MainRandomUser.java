package org.arcticsoft.bluebearlive.activity;

import android.view.View;
import android.widget.TextView;

import org.arcticsoft.bluebearlive.R;

/**
 * Created by DmitriyRoot on 09.01.2017.
 */

public class MainRandomUser {

    View view;
    TextView textView;

    public MainRandomUser(View view) {
        this.view = view;
        textView = (TextView) view.findViewById(R.id.textView);
        textView.setText("sdasdsaadsadasdas!");
    }
}
