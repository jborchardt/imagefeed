package com.jborchardt.imagefeed.presentation.view.loading;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.jborchardt.imagefeed.presentation.R;
import com.jborchardt.imagefeed.presentation.common.LoadingView;


public class LoadingBarView extends FrameLayout implements LoadingView {

    private View mSolidBar;
    private ProgressBar mProgressBar;

    public LoadingBarView(Context context) {
        this(context, null);
    }

    public LoadingBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R.layout.loading_view, this);

        mSolidBar = findViewById(R.id.solid_bar);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mProgressBar.setIndeterminate(true);
    }

    public void setTintColor(@ColorInt int color) {
        mProgressBar.setIndeterminateTintList(ColorStateList.valueOf(color));
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
        mSolidBar.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        mSolidBar.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }
}
