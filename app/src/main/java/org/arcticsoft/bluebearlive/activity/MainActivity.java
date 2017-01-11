package org.arcticsoft.bluebearlive.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import org.arcticsoft.bluebearlive.R;
import org.arcticsoft.bluebearlive.fragments.FeedlineFragment;
import org.arcticsoft.bluebearlive.fragments.NoticeFragment;
import org.arcticsoft.bluebearlive.fragments.RandomUserFragment;
import org.arcticsoft.bluebearlive.fragments.TestFragment;
import org.arcticsoft.bluebearlive.fragments.UserProfileFragment;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends AppCompatActivity {

    NavigationTabBar navigationTabBar;
    FrameLayout fragment;

    int active = -1;

    private ArrayMap<Integer, Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_ntb);
        navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb);
        fragment = (FrameLayout) findViewById(R.id.ufragment);

        fragments = new ArrayMap<>();
        fragments.put(0, new FeedlineFragment());
        fragments.put(1, new UserProfileFragment());
        fragments.put(2, new TestFragment());
        fragments.put(3, new NoticeFragment());
        fragments.put(4, new RandomUserFragment());

        initUI();

    }


    private void initUI(){

        final String[] colors = getResources().getStringArray(R.array.default_preview);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_first),
                        Color.parseColor(colors[0]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_sixth))
                        .title("FeedLine")
                        .badgeTitle("+18")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_second),
                        Color.parseColor(colors[1]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("Profile")
                        .badgeTitle("with")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_third),
                        Color.parseColor(colors[2]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_seventh))
                        .title("MAIN_TEST")
                        .badgeTitle("state")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_fourth),
                        Color.parseColor(colors[3]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("NoticeFragment")
                        .badgeTitle("icon")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_fifth),
                        Color.parseColor(colors[4]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("Random_User")
                        .badgeTitle("777")
                        .build()
        );

//        Замена сообщения верхнего ярлыка
//        models.get(3).setTitle("Here some title to model");
//        models.get(3).hideBadge();
//        models.get(3).showBadge();
//        models.get(3).toggleBadge();
//        models.get(3).updateBadgeTitle("Here some title like NEW or some integer value");

        navigationTabBar.setModels(models);
        //navigationTabBar.setViewPager(viewPager);

        navigationTabBar.setOnTabBarSelectedIndexListener(new NavigationTabBar.OnTabBarSelectedIndexListener() {
            @Override
            public void onStartTabSelected(final NavigationTabBar.Model model, final int index) {

            }

            @Override
            public void onEndTabSelected(final NavigationTabBar.Model model, final int index) {

                final Fragment fragment = fragments.get(index);
                if(!fragment.isAdded()){
                    Log.d("ADD", index+"");
                    Log.d("isAdded", fragment.isAdded()+"");
                    if(active != -1){
                        getSupportFragmentManager().beginTransaction().hide(fragments.get(active)).commit();
                    }
                    getSupportFragmentManager().beginTransaction().add(R.id.ufragment, fragment).commit();
                    active = index;
                } else if (fragment.isHidden()){
                    Log.d("SHOW", index+"");
                    Log.d("isHidden", fragment.isHidden()+"");
                    getSupportFragmentManager().beginTransaction().hide(fragments.get(active)).commit();
                    getSupportFragmentManager().beginTransaction().show(fragment).commit();
                    active = index;
                }else {
                    Log.d("isAdded", fragment.isAdded()+"");
                    Log.d("isHidden", fragment.isHidden()+"");
                    Log.d("THIS", index+"");
                }
                model.hideBadge();
            }
        });

        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

        navigationTabBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
                    navigationTabBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            model.showBadge();
                        }
                    }, i * 100);
                }
            }
        }, 500);
    }

//        Application application = Application.getInstance();
//
//        textStatus = (TextView) findViewById(R.id.status_request);
//        connectToServer = (Button) findViewById(R.id.buttonConnect);
//        goAuthPacket = (Button) findViewById(R.id.buttonAuth);
//        goUserPacket = (Button) findViewById(R.id.buttonSendUserPacket);
//        goCommandPacket = (Button) findViewById(R.id.buttonSendCommandPacket);
//
//        connectToServer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                application.setServerConnection();
//            }
//        });
//
//        goAuthPacket.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                application.sendAuth();
//            }
//        });
//
//        goUserPacket.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                PacketManager.PacketGenerator(application.getUserApplication(), new UserPacket("name"));
//            }
//        });
//
//        goCommandPacket.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                PacketManager.PacketGenerator(application.getUserApplication(), new CommandPacket(22, "sd"));
//            }
//        });

}
