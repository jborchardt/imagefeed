package com.jborchardt.imagefeed.presentation.view.loading;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.jborchardt.imagefeed.presentation.R;

public class LoadingToolbar extends FrameLayout {

    private Toolbar mToolbar;

    private LoadingBarView mLoadingModule;

    public LoadingToolbar(Context context) {
        super(context);
    }

    public LoadingToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LoadingToolbar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        LayoutInflater.from(getContext()).inflate(R.layout.loading_toolbar, this, true);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mLoadingModule = (LoadingBarView) findViewById(R.id.loading);
    }

    public void setTitle(@StringRes int stringRes) {
        mToolbar.setTitle(stringRes);
    }


    public Toolbar getToolbar() {
        return mToolbar;
    }

    public LoadingBarView getLoadingView() {
        return mLoadingModule;
    }
}
