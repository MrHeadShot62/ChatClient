package org.arcticsoft.bluebearlive.activity;

import android.view.View;
import android.widget.TextView;

import org.arcticsoft.bluebearlive.R;

/**
 * Created by DmitriyRoot on 09.01.2017.
 */

public class MainFeedLine {

    View view;
    TextView textView;

    public MainFeedLine(View view) {
        this.view = view;
        textView = (TextView) view.findViewById(R.id.textView);
        textView.setText("sdasdsaadsadasdas!");
    }
}
