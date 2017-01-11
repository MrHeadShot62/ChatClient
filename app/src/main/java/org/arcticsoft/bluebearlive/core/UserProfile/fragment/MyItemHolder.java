package org.arcticsoft.bluebearlive.core.UserProfile.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.github.johnkil.print.PrintView;
import com.unnamed.b.atv.model.TreeNode;

import org.arcticsoft.bluebearlive.R;

/**
 * Created by Bogdan Melnychuk on 2/12/15.
 */
public class MyItemHolder extends TreeNode.BaseNodeViewHolder<MyItemHolder.IconTreeItem>{

    public MyItemHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(TreeNode node, MyItemHolder.IconTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.layout_profile_node, null, false);
        TextView tvValue = (TextView) view.findViewById(R.id.node_value);
        tvValue.setText(value.text);

        return view;
    }

    public static class IconTreeItem {
        public int icon;
        public String text;
    }
}
