package org.arcticsoft.bluebearlive.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.arcticsoft.bluebearlive.R;

/**
 * Created by DmitriyRoot on 10.01.2017.
 */

public class FeedlineFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_feedline, null);

        return v;
    }
}
