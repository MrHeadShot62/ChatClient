package org.arcticsoft.bluebearlive.fragments.UserProfile;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gigamole.library.ArcProgressStackView;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;

import org.arcticsoft.bluebearlive.R;

import java.util.ArrayList;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class Statistic extends Fragment {

    private ObservableScrollView mScrollView;

    public final static int MODEL_COUNT = 4;

    private int[] mStartColors = new int[MODEL_COUNT];

    View view;

    public static Statistic newInstance() {
        return new Statistic();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.user_profile_statistic, container, false);

        final String[] bgColors = view.getResources().getStringArray(R.array.medical_express);

        final ArrayList<ArcProgressStackView.Model> models = new ArrayList<>();
        models.add(new ArcProgressStackView.Model("Circle", 30, Color.parseColor(bgColors[0]), mStartColors[0]));
        models.add(new ArcProgressStackView.Model("Progress", 20, Color.parseColor(bgColors[1]), mStartColors[1]));
        models.add(new ArcProgressStackView.Model("Stack", 95, Color.parseColor(bgColors[2]), mStartColors[2]));
        models.add(new ArcProgressStackView.Model("View", 2, Color.parseColor(bgColors[3]), mStartColors[3]));

        final ArcProgressStackView arcProgressStackView = (ArcProgressStackView) view.findViewById(R.id.apsv);
        arcProgressStackView.setModels(models);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mScrollView = (ObservableScrollView) view.findViewById(R.id.scrollView);

        MaterialViewPagerHelper.registerScrollView(getActivity(), mScrollView, null);
    }
}
