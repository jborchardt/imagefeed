package com.jborchardt.imagefeed;

import android.support.v4.app.Fragment;

import com.jborchardt.imagefeed.common.BaseActivity;
import com.jborchardt.imagefeed.presentation.view.feed.FeedFragment;

public class FeedActivity extends BaseActivity {

    @Override
    protected Fragment getContentFragment() {
        return FeedFragment.newInstance();
    }
}
