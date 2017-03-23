package com.jborchardt.imagefeed.presentation.view.loading;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

import com.jborchardt.imagefeed.presentation.view.common.LoadingView;

public class LoadingCircleView extends ProgressBar implements LoadingView {
    public LoadingCircleView(final Context context) {
        this(context, null);
    }

    public LoadingCircleView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingCircleView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public LoadingCircleView(final Context context, final AttributeSet attrs, final int defStyleAttr, final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        setIndeterminate(true);
    }

    @Override
    public void showLoading() {
        setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        setVisibility(View.GONE);
    }
}
