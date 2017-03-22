package com.jborchardt.imagefeed.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;

import com.jborchardt.imagefeed.R;
import com.jborchardt.imagefeed.presentation.view.details.DetailsFragment;

public class DetailsActivity extends BaseActivity {

    public static final String ARG_ID = "id";

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.details);
    }

    @Override
    protected Fragment getContentFragment() {
        final String itemId = getItemId();

        return DetailsFragment.newInstance(itemId);
    }

    private String getItemId() {
        final Intent intent = getIntent();
        if(intent != null) {
            return intent.getStringExtra(ARG_ID);
        }
        return null;
    }
}
