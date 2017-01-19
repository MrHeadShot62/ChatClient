package org.arcticsoft.bluebearlive.fragments;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.jpardogo.listbuddies.lib.views.ListBuddiesLayout;

import org.arcticsoft.bluebearlive.R;
import org.arcticsoft.bluebearlive.fragments.FeedLine.CircularAdapter;
import org.arcticsoft.bluebearlive.fragments.FeedLine.DetailActivity;
import org.arcticsoft.bluebearlive.fragments.FeedLine.ImagesUrls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by DmitriyRoot on 10.01.2017.
 */

public class FeedlineFragment extends Fragment implements ListBuddiesLayout.OnBuddyItemClickListener {

    View view;

    private List<String> mImagesLeft = new ArrayList<String>();
    private List<String> mImagesRight = new ArrayList<String>();
    private boolean isOpenActivities = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.app_main_feed_line, container, false);

        mImagesLeft.addAll(Arrays.asList(ImagesUrls.imageUrls_left));
        mImagesRight.addAll(Arrays.asList(ImagesUrls.imageUrls_right));

        ListBuddiesLayout listBuddies = (ListBuddiesLayout) view.findViewById(R.id.listbuddies);
        CircularAdapter adapter = new CircularAdapter(getActivity(), getResources().getDimensionPixelSize(R.dimen.image_size1), mImagesLeft);
        CircularAdapter adapter2 = new CircularAdapter(getActivity(), getResources().getDimensionPixelSize(R.dimen.image_size2), mImagesRight);
        listBuddies.setAdapters(adapter, adapter2);
        listBuddies.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onBuddyItemClicked(AdapterView<?> parent, View view, int buddy, int position, long id) {
        if (isOpenActivities) {
            Log.d("CLICKIMG", "XXX");
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_URL, getImage(buddy, position));
            startActivity(intent);
        } else {
            Log.d("CLICKIMG", "XX");
            Resources resources = getResources();
            Toast.makeText(getActivity(), resources.getString(R.string.list) + ": " + buddy + " " + resources.getString(R.string.position) + ": " + position, Toast.LENGTH_SHORT).show();
        }
    }

    private String getImage(int buddy, int position) {
        return buddy == 0 ? ImagesUrls.imageUrls_left[position] : ImagesUrls.imageUrls_right[position];
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
