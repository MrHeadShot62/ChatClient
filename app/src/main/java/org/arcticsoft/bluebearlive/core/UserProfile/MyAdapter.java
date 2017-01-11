package org.arcticsoft.bluebearlive.core.UserProfile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by DmitriyRoot on 10.01.2017.
 */

public class MyAdapter extends ArrayAdapter {



    public MyAdapter(Context context, int resource) {
        super(context, resource);
    }

    public MyAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public MyAdapter(Context context, int resource, Object[] objects) {
        super(context, resource, objects);
    }

    public MyAdapter(Context context, int resource, int textViewResourceId, Object[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public MyAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }

    public MyAdapter(Context context, int resource, int textViewResourceId, List objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getPosition(Object item) {
        return super.getPosition(item);
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }
}
