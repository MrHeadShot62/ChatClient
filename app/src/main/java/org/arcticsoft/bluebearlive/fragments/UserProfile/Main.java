package org.arcticsoft.bluebearlive.fragments.UserProfile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import org.arcticsoft.bluebearlive.R;
import org.arcticsoft.bluebearlive.core.UserProfile.HeaderHolder;
import org.arcticsoft.bluebearlive.core.UserProfile.IconTreeItemHolder;
import org.arcticsoft.bluebearlive.core.UserProfile.PlaceHolderHolder;
import org.arcticsoft.bluebearlive.core.UserProfile.ProfileHolder;
import org.arcticsoft.bluebearlive.core.UserProfile.fragment.SocialViewHolder;

/**
 * Created by florentchampigny on 24/04/15.
 */

public class Main extends Fragment {

    View view;

    private ObservableScrollView mScrollView;

    private AndroidTreeView tView;

    public static Main newInstance() {
        return new Main();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.user_profile_main, container, false);
        Log.d("P_MAIN", "Load");

        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.rootInfo);

        final TreeNode root = TreeNode.root();

        TreeNode myProfileInfo = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_person, "Info")).setViewHolder(new ProfileHolder(getActivity()));
        TreeNode myProfileSettings = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_person, "Settings")).setViewHolder(new ProfileHolder(getActivity()));
        TreeNode myProfileAction = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_person, "Actions")).setViewHolder(new ProfileHolder(getActivity()));
        addMyProfileInfo(myProfileInfo);
        addMyProfileSettings(myProfileSettings);
        addMyProfileAction(myProfileAction);
        root.addChildren(myProfileInfo, myProfileSettings, myProfileAction);
        myProfileInfo.setExpanded(true);
        myProfileSettings.setExpanded(true);
        myProfileAction.setExpanded(true);

        tView = new AndroidTreeView(getActivity(), root);
        tView.setDefaultContainerStyle(R.style.TreeNodeStyleDivided, true);
        viewGroup.addView(tView.getView());

        if (savedInstanceState != null) {
            String state = savedInstanceState.getString("tState");
            if (!TextUtils.isEmpty(state)) {
                tView.restoreState(state);
            }
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("U_MAIN", "ON_VIEW");
        mScrollView = (ObservableScrollView) view.findViewById(R.id.profile_scroll_view);

        MaterialViewPagerHelper.registerScrollView(getActivity(), mScrollView, null);
    }

    private void addMyProfileInfo(TreeNode profile) {
        TreeNode socialNetworks = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_people, "Social")).setViewHolder(new HeaderHolder(getActivity()));
        TreeNode places = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_place, "Places")).setViewHolder(new HeaderHolder(getActivity()));

        TreeNode facebook = new TreeNode(new SocialViewHolder.SocialItem(R.string.ic_account_box, "First name", "Dima")).setViewHolder(new SocialViewHolder(getActivity()));
        TreeNode facebook2 = new TreeNode(new SocialViewHolder.SocialItem(R.string.ic_account_box, "Two name", "Andreevich")).setViewHolder(new SocialViewHolder(getActivity()));
        TreeNode facebook3 = new TreeNode(new SocialViewHolder.SocialItem(R.string.ic_tag_faces, "Nick name", "Hennessy")).setViewHolder(new SocialViewHolder(getActivity()));
        TreeNode facebook4 = new TreeNode(new SocialViewHolder.SocialItem(R.string.ic_star_outline, "Status in App", "Administrator")).setViewHolder(new SocialViewHolder(getActivity()));
        TreeNode facebook5 = new TreeNode(new SocialViewHolder.SocialItem(R.string.ic_extension, "Age", "19")).setViewHolder(new SocialViewHolder(getActivity()));
        TreeNode linkedin = new TreeNode(new SocialViewHolder.SocialItem(R.string.ic_earth, "Country", "UA")).setViewHolder(new SocialViewHolder(getActivity()));
        TreeNode google = new TreeNode(new SocialViewHolder.SocialItem(R.string.ic_gps_fixed, "City", "Chernigov")).setViewHolder(new SocialViewHolder(getActivity()));
        TreeNode twitter = new TreeNode(new SocialViewHolder.SocialItem(R.string.ic_people, "Count Friends", "81")).setViewHolder(new SocialViewHolder(getActivity()));
        TreeNode twitter1 = new TreeNode(new SocialViewHolder.SocialItem(R.string.ic_add_box, "Date Regist", "11.01.2017")).setViewHolder(new SocialViewHolder(getActivity()));
        TreeNode twitter2 = new TreeNode(new SocialViewHolder.SocialItem(R.string.ic_timelapse, "Rate", "7.1")).setViewHolder(new SocialViewHolder(getActivity()));
        TreeNode twitter3 = new TreeNode(new SocialViewHolder.SocialItem(R.string.ic_communities, "Balance", "45")).setViewHolder(new SocialViewHolder(getActivity()));

        TreeNode lake = new TreeNode(new PlaceHolderHolder.PlaceItem("A rose garden")).setViewHolder(new PlaceHolderHolder(getActivity()));
        TreeNode mountains = new TreeNode(new PlaceHolderHolder.PlaceItem("The white house")).setViewHolder(new PlaceHolderHolder(getActivity()));

        places.addChildren(lake, mountains);
        socialNetworks.addChildren(facebook, google, twitter, linkedin);
        profile.addChildren(facebook, facebook2, facebook3, facebook4, facebook5, linkedin, google, twitter, twitter1, twitter2, twitter3, places);
    }

    private void addMyProfileSettings(TreeNode profile) {
        TreeNode socialNetworks = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_people, "Social")).setViewHolder(new HeaderHolder(getActivity()));
        TreeNode places = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_place, "Places")).setViewHolder(new HeaderHolder(getActivity()));

        TreeNode facebook = new TreeNode(new SocialViewHolder.SocialItem(R.string.ic_post_facebook, "name", "desk")).setViewHolder(new SocialViewHolder(getActivity()));
        TreeNode linkedin = new TreeNode(new SocialViewHolder.SocialItem(R.string.ic_post_linkedin, "name", "desk")).setViewHolder(new SocialViewHolder(getActivity()));
        TreeNode google = new TreeNode(new SocialViewHolder.SocialItem(R.string.ic_post_gplus, "name", "desk")).setViewHolder(new SocialViewHolder(getActivity()));
        TreeNode twitter = new TreeNode(new SocialViewHolder.SocialItem(R.string.ic_post_twitter, "name", "desk")).setViewHolder(new SocialViewHolder(getActivity()));

        TreeNode lake = new TreeNode(new PlaceHolderHolder.PlaceItem("A rose garden")).setViewHolder(new PlaceHolderHolder(getActivity()));
        TreeNode mountains = new TreeNode(new PlaceHolderHolder.PlaceItem("The white house")).setViewHolder(new PlaceHolderHolder(getActivity()));

        places.addChildren(lake, mountains);
        socialNetworks.addChildren(facebook, google, twitter, linkedin);
        profile.addChildren(socialNetworks, places);
    }

    private void addMyProfileAction(TreeNode profile) {
        TreeNode socialNetworks = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_people, "Social")).setViewHolder(new HeaderHolder(getActivity()));
        TreeNode places = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_place, "Places")).setViewHolder(new HeaderHolder(getActivity()));

        TreeNode facebook = new TreeNode(new SocialViewHolder.SocialItem(R.string.ic_post_facebook, "name", "desk")).setViewHolder(new SocialViewHolder(getActivity()));
        TreeNode linkedin = new TreeNode(new SocialViewHolder.SocialItem(R.string.ic_post_linkedin, "name", "desk")).setViewHolder(new SocialViewHolder(getActivity()));
        TreeNode google = new TreeNode(new SocialViewHolder.SocialItem(R.string.ic_post_gplus, "name", "desk")).setViewHolder(new SocialViewHolder(getActivity()));
        TreeNode twitter = new TreeNode(new SocialViewHolder.SocialItem(R.string.ic_post_twitter, "name", "desk")).setViewHolder(new SocialViewHolder(getActivity()));

        TreeNode lake = new TreeNode(new PlaceHolderHolder.PlaceItem("A rose garden")).setViewHolder(new PlaceHolderHolder(getActivity()));
        TreeNode mountains = new TreeNode(new PlaceHolderHolder.PlaceItem("The white house")).setViewHolder(new PlaceHolderHolder(getActivity()));

        places.addChildren(lake, mountains);
        socialNetworks.addChildren(facebook, google, twitter, linkedin);
        profile.addChildren(socialNetworks, places);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
