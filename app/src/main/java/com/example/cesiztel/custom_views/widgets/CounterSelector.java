package com.example.cesiztel.custom_views.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cesiztel.custom_views.R;

public class CounterSelector extends LinearLayout {
    private static final String COUNTER_STATE_INDEX="counter_state";

    private static final String STATE_SUPER_INDEX="super_class";

    private Drawable mDrawableDown;
    private Drawable mDrawableUp;
    private ImageView mUp;
    private ImageView mDown;
    private TextView mCounter;

    private int mCurrentCounter;

    public CounterSelector(Context context) {
        super(context);
        initializeViews(context);
    }

    public CounterSelector(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray attributes;
        attributes = context.
                obtainStyledAttributes(attrs, R.styleable.CounterSelector);
        mDrawableUp = attributes.getDrawable(R.styleable.CounterSelector_drawableUp);
        mDrawableDown = attributes.getDrawable(R.styleable.CounterSelector_drawableDown);

        attributes.recycle();

        initializeViews(context);
    }

    public CounterSelector(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray attributes;
        attributes = context.
                obtainStyledAttributes(attrs, R.styleable.CounterSelector);
        mDrawableUp = attributes.getDrawable(R.styleable.CounterSelector_drawableUp);
        mDrawableDown = attributes.getDrawable(R.styleable.CounterSelector_drawableDown);

        attributes.recycle();

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
        inflater.inflate(R.layout.counter_view, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        // Sets the resources
        mUp = (ImageView) this
                .findViewById(R.id.up_selector);
        mDown = (ImageView) this
                .findViewById(R.id.down_selector);
        mCounter = (TextView) this
                .findViewById(R.id.counter_text_view);

        // Set the drawer resources
        mUp.setImageDrawable(mDrawableUp);
        mDown.setImageDrawable(mDrawableDown);

        // Add listener/interactions
        mUp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentCounter++;
                mCounter.setText(String.valueOf(mCurrentCounter));
            }
        });

        mDown.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentCounter == 0) return;
                mCurrentCounter--;
                mCounter.setText(String.valueOf(mCurrentCounter));
            }
        });
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();

        bundle.putParcelable(STATE_SUPER_INDEX,
                super.onSaveInstanceState());
        bundle.putInt(COUNTER_STATE_INDEX, mCurrentCounter);

        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if(state instanceof Bundle) {
            Bundle bundle = (Bundle) state;

            super.onRestoreInstanceState(bundle.getParcelable(STATE_SUPER_INDEX));

            mCurrentCounter = bundle.getInt(COUNTER_STATE_INDEX);
            mCounter.setText(String.valueOf(mCurrentCounter));
        } else {
            super.onRestoreInstanceState(state);
        }
    }
}
