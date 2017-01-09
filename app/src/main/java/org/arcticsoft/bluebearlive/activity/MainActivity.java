package org.arcticsoft.bluebearlive.activity;

import android.graphics.Color;
import android.media.Image;
import android.os.StrictMode;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mrheadshot62.api.types.AuthPacket;
import com.mrheadshot62.api.types.CommandPacket;
import com.mrheadshot62.api.types.ImagePacket;
import com.mrheadshot62.api.types.UserPacket;

import org.arcticsoft.bluebearlive.R;
import org.arcticsoft.bluebearlive.core.logic.Application;
import org.arcticsoft.bluebearlive.core.logic.PacketManager;
import org.arcticsoft.bluebearlive.core.logic.User;
import org.arcticsoft.bluebearlive.fragments.NonSwipeableViewPager;
import org.arcticsoft.bluebearlive.socket.ConnectionController;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    Application application;
    TextView textStatus;
    Button connectToServer, goAuthPacket, goUserPacket, goCommandPacket;
    NavigationTabBar navigationTabBar;
    NonSwipeableViewPager viewPager;
    ArrayMap<Integer, View> pages;

    MainUserProfile mainUserProfile;
    MainFeedLine mainFeedLine;
    MainNotice mainNotice;
    MainRandomUser mainRandomUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_ntb);
        viewPager = (NonSwipeableViewPager) findViewById(R.id.vp_horizontal_ntb);
        navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb);
        navigationTabBar.setIsSwiped(true);

        pages = new ArrayMap<>();
        pages.put(0, LayoutInflater.from(
                getBaseContext()).inflate(R.layout.main_feedline, null, false) );
        pages.put(1, LayoutInflater.from(
                getBaseContext()).inflate(R.layout.main_profile, null, false) );
        pages.put(2, LayoutInflater.from(
                getBaseContext()).inflate(R.layout.activity_main, null, false) );
        pages.put(3, LayoutInflater.from(
                getBaseContext()).inflate(R.layout.main_notice, null, false) );
        pages.put(4, LayoutInflater.from(
                getBaseContext()).inflate(R.layout.main_random_user, null, false) );
        initUI();


        mainFeedLine = new MainFeedLine(pages.get(0));
        mainUserProfile = new MainUserProfile(pages.get(1));
        mainNotice = new MainNotice(pages.get(3));
        mainRandomUser = new MainRandomUser(pages.get(4));

    }


    private void initUI(){
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public boolean isViewFromObject(final View view, final Object object) {
                return view.equals(object);
            }

            @Override
            public void destroyItem(final View container, final int position, final Object object) {
                ((ViewPager) container).removeView((View) object);
            }

            @Override
            public Object instantiateItem(final ViewGroup container, final int position) {
                final View view = pages.get(position);

//                final TextView txtPage = (TextView) view.findViewById(R.id.txt_vp_item_page);
//                txtPage.setText(String.format("Page #%d", position));

                container.addView(view);
                return view;
            }
        });

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
                        .title("Notice")
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
        navigationTabBar.setViewPager(viewPager, 2);
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                navigationTabBar.getModels().get(position).hideBadge();
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
