package org.arcticsoft.bluebearlive.fragments.UserProfile;

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
 * Created by florentchampigny on 24/04/15.
 */
public class Settings extends Fragment {

    private ObservableScrollView mScrollView;

    public static Settings newInstance() {
        return new Settings();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("P_MAIN", "Load");
        return inflater.inflate(R.layout.user_profile_setting, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("U_SETTING", "ON_VIEW");
        mScrollView = (ObservableScrollView) view.findViewById(R.id.profile_setting_scroll_view);

        MaterialViewPagerHelper.registerScrollView(getActivity(), mScrollView, null);
    }
}
