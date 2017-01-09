package org.arcticsoft.bluebearlive.core.Notice;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.arcticsoft.bluebearlive.R;

import java.util.List;

import co.dift.ui.SwipeToAction;


public class NoticeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Notice> items;


    /** References to the views for each data item **/
    public class NoticeHolder extends SwipeToAction.ViewHolder<Notice> {
        public TextView titleView;
        public TextView authorView;
        public SimpleDraweeView imageView;

        public NoticeHolder(View v) {
            super(v);

            titleView = (TextView) v.findViewById(R.id.title);
            authorView = (TextView) v.findViewById(R.id.author);
            imageView = (SimpleDraweeView) v.findViewById(R.id.image);
        }
    }

    /** Constructor **/
    public NoticeAdapter(List<Notice> items) {
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);

        return new NoticeHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Notice item = items.get(position);
        NoticeHolder vh = (NoticeHolder) holder;
        vh.titleView.setText(item.getTitle());
        vh.authorView.setText(item.getAuthor());
        vh.imageView.setImageURI(Uri.parse(item.getImageUrl()));
        vh.data = item;
    }
}