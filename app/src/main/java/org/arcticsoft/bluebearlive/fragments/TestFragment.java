package org.arcticsoft.bluebearlive.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.arcticsoft.bluebearlive.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment {


    public TestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main, null);

        return v;
    }

}
