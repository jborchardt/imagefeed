package com.jborchardt.imagefeed.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.jborchardt.imagefeed.R;
import com.jborchardt.imagefeed.presentation.view.details.DetailsFragment;

public class DetailsActivity extends AppCompatActivity {

    public static final String ARG_ID = "id";

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_details);

        showDetailsFragment();
    }

    private void showDetailsFragment() {
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        final Fragment fragment = DetailsFragment.newInstance(getItemId());
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }

    private String getItemId() {
        final Intent intent = getIntent();
        if(intent != null) {
            return intent.getStringExtra(ARG_ID);
        }
        return null;
    }
}
