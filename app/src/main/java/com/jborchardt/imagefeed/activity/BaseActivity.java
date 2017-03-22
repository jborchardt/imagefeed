package com.jborchardt.imagefeed.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jborchardt.imagefeed.R;
import com.jborchardt.imagefeed.presentation.navigation.Navigator;
import com.jborchardt.imagefeed.presentation.view.loading.LoadingBarView;
import com.jborchardt.imagefeed.presentation.view.loading.LoadingToolbar;
import com.jborchardt.imagefeed.presentation.view.loading.LoadingViewProvider;

public abstract class BaseActivity extends AppCompatActivity implements LoadingViewProvider, Navigator {

    private View mContentView;
    private LoadingToolbar mToolbar;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base);

        setupContentView();

        setupToolbar();

        showContent();
    }

    private void setupContentView() {
        mContentView = findViewById(R.id.content);
    }

    private void setupToolbar() {
        mToolbar = (LoadingToolbar) findViewById(R.id.toolbarModule);
        setSupportActionBar(mToolbar.getToolbar());
    }

    private void showContent() {
        final Fragment fragment = getContentFragment();
        showFragment(fragment, false);
    }

    @Override
    public LoadingBarView provideLoadingView() {
        return mToolbar.getLoadingView();
    }

    @Override
    public void navigateToDetails(@NonNull final String itemId) {
        final Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(DetailsActivity.ARG_ID, itemId);

        startActivity(intent);
    }

     /*Fragment transactions*/

    protected void showFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, fragment);
        if (addToBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }

    protected abstract Fragment getContentFragment();
}
