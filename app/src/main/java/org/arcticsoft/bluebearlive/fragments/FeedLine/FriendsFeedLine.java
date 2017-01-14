package org.arcticsoft.bluebearlive.fragments.FeedLine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;

import org.arcticsoft.bluebearlive.R;

/**
 * Created by DmitriyRoot on 13.01.2017.
 */

public class FriendsFeedLine extends Fragment {

    View view;

    public static FriendsFeedLine newInstance() {
        return new FriendsFeedLine();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.feed_line_friends, container, false);

        return view;
    }
}
