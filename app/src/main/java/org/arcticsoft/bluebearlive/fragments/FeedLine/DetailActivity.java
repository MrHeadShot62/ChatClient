package org.arcticsoft.bluebearlive.fragments.FeedLine;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.arcticsoft.bluebearlive.R;

import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;

public class DetailActivity extends BaseActivity {

    public static final String EXTRA_URL = "url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_line_global_detail_activity);
        ButterKnife.bind(this);
        mBackground = (PhotoView) findViewById(R.id.image);
        String imageUrl = getIntent().getExtras().getString(EXTRA_URL);
        Picasso.with(this).load(imageUrl).placeholder(R.drawable.ic_launcher).into((ImageView) findViewById(R.id.image), new Callback() {
            @Override
            public void onSuccess() {
                //moveBackground();
                Toast.makeText(getApplicationContext(), "Contact info", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError() {
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
}


