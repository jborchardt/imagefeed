package com.jborchardt.imagefeed.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.jborchardt.imagefeed.R;
import com.jborchardt.imagefeed.presentation.view.toolbar.LoadingToolbar;
import com.jborchardt.imagefeed.presentation.view.toolbar.LoadingView;

public abstract class BaseActivity extends AppCompatActivity {

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

    private void showContent() {
        final Fragment fragment = getContentFragment();
        showFragment(fragment, false);
    }

    private void setupContentView() {
        mContentView = findViewById(R.id.content);
    }

    private void setupToolbar() {
        mToolbar = (LoadingToolbar) findViewById(R.id.toolbarModule);
        setSupportActionBar(mToolbar.getToolbar());
    }


    public View getContentView() {
        return mContentView;
    }

    /*Action bar*/

    public LoadingView getLoadingModule() {
        return mToolbar.getLoadingModule();
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed(); //TODO
                return true;
        }
        return super.onOptionsItemSelected(item);
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
