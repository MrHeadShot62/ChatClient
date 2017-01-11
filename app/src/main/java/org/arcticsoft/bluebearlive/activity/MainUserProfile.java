package org.arcticsoft.bluebearlive.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import org.arcticsoft.bluebearlive.R;
import org.arcticsoft.bluebearlive.core.UserProfile.DrawerActivity;
import org.arcticsoft.bluebearlive.core.UserProfile.fragment.RecyclerViewFragment;
import org.arcticsoft.bluebearlive.core.UserProfile.fragment.ScrollFragment;
import org.arcticsoft.bluebearlive.core.UserProfile.fragment.WebViewFragment;

/**
 * Created by DmitriyRoot on 09.01.2017.
 */

public class MainUserProfile extends DrawerActivity {

    private View view;
    private MaterialViewPager mViewPager;

    public MainUserProfile(View view) {
        this.view = view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        mViewPager = (MaterialViewPager) this.view.findViewById(R.id.materialViewPager);

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                return ScrollFragment.newInstance();
            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return "Divertissement";
            }
        });

        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                return HeaderDesign.fromColorResAndUrl(
                        R.color.red,
                        "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
//                switch (page) {
//                    case 0:
//                        return HeaderDesign.fromColorResAndUrl(
//                                R.color.green,
//                                "https://fs01.androidpit.info/a/63/0e/android-l-wallpapers-630ea6-h900.jpg");
//                    case 1:
//                        return HeaderDesign.fromColorResAndUrl(
//                                R.color.blue,
//                                "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_51.jpg");
//                    case 2:
//                        return HeaderDesign.fromColorResAndUrl(
//                                R.color.cyan,
//                                "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
//                    case 3:
//                        return HeaderDesign.fromColorResAndUrl(
//                                R.color.red,
//                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
//                }
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

        View logo = this.view.findViewById(R.id.logo_white);
        Log.d("MUP", "check1");
        if (logo != null) {
            Log.d("MUP", "check2");
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.notifyHeaderChanged();
                    Toast.makeText(getApplicationContext(), "Yes, the title is clickable", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
