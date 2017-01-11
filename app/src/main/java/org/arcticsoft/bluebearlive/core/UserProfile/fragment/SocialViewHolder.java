package org.arcticsoft.bluebearlive.core.UserProfile.fragment;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.github.johnkil.print.PrintView;
import com.unnamed.b.atv.model.TreeNode;

import org.arcticsoft.bluebearlive.R;

import java.util.Random;

/**
 * Created by Bogdan Melnychuk on 2/13/15.
 */
public class SocialViewHolder extends TreeNode.BaseNodeViewHolder<SocialViewHolder.SocialItem> {

    private static final String[] NAMES = new String[]{"Bruce Wayne", "Clark Kent", "Barry Allen", "Hal Jordan"};

    public SocialViewHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(TreeNode node, SocialItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.layout_social_node, null, false);

        final PrintView iconView = (PrintView) view.findViewById(R.id.icon);
        iconView.setIconText(context.getResources().getString(value.icon));

        TextView connectionsLabel = (TextView) view.findViewById(R.id.connections);
        Random r = new Random();
        connectionsLabel.setText(value.desc);

        TextView userNameLabel = (TextView) view.findViewById(R.id.username);
        SpannableString ss = new SpannableString(value.title);
        ss.setSpan(new UnderlineSpan(), 0, value.title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        userNameLabel.setText(ss);

//        TextView sizeText = (TextView) view.findViewById(R.id.size);
//        sizeText.setText(r.nextInt(10) + " items");

        return view;
    }

    @Override
    public void toggle(boolean active) {
    }


    public static class SocialItem {
        public String title;
        public String desc;
        public int icon;

        public SocialItem(int icon, String title, String desc) {
            this.title = title;
            this.desc = desc;
            this.icon = icon;
        }
        // rest will be hardcoded
    }

}
