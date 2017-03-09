package com.example.cesiztel.custom_views.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cesiztel.custom_views.R;

public class LikeSelector extends LinearLayout {
    private ImageView mThumbUp;
    private ImageView mThumbDown;
    private TextView mLikes;

    public LikeSelector(Context context) {
        super(context);
        initializeViews(context);
    }

    public LikeSelector(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context);
    }

    public LikeSelector(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeViews(context);
    }

    /**
     * Inflates de views in the layout
     *
     * @param context
     *          the current context for the view
     */
    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.likeselector_view, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        // Sets the resources
        mThumbUp = (ImageView) this
                .findViewById(R.id.thumb_up_selector);
        mThumbDown = (ImageView) this
                .findViewById(R.id.thumb_down_selector);
        mLikes = (TextView) this
                .findViewById(R.id.likes_text_view);

        // Add listener/interactions
        mThumbUp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int likes = Integer.valueOf(mLikes.getText().toString());
                likes++;
                mLikes.setText(String.valueOf(likes));
            }
        });

        mThumbDown.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int likes = Integer.valueOf(mLikes.getText().toString());
                if(likes == 0) return;
                likes--;
                mLikes.setText(String.valueOf(likes));
            }
        });
    }
}
