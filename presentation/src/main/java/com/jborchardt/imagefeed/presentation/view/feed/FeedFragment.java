package com.jborchardt.imagefeed.presentation.view.feed;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jborchardt.imagefeed.data.RepositoryRegistry;
import com.jborchardt.imagefeed.domain.feed.FeedInteractor;
import com.jborchardt.imagefeed.domain.feed.FeedRepository;
import com.jborchardt.imagefeed.presentation.R;
import com.jborchardt.imagefeed.presentation.presenter.feed.FeedPresenter;
import com.jborchardt.imagefeed.presentation.presenter.feed.FeedView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by johannesborchardt on 22.03.17.
 */

public class FeedFragment extends Fragment implements FeedView {

    private FeedRecyclerView mFeedRecyclerView;
    private FeedPresenter mFeedPresenter;

    @Deprecated
    public FeedFragment() {
        // Required empty public constructor
    }

    public static FeedFragment newInstance() {
        @SuppressWarnings("deprecation")
        final FeedFragment fragment = new FeedFragment();

        return fragment;
    }

    @Nullable
    @Override
    public android.view.View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, final Bundle savedInstanceState) {
        mFeedRecyclerView = (FeedRecyclerView) inflater.inflate(R.layout.fragment_feed, container, false);
        setUp(mFeedRecyclerView);

        return mFeedRecyclerView;
    }

    private void setUp(final FeedRecyclerView feedRecyclerView) {
        final FeedRepository feedRepository = RepositoryRegistry.getInstance(getActivity()).getFeedRepository();
        final FeedInteractor feedInteractor = new FeedInteractor(Schedulers.io(), AndroidSchedulers.mainThread(), feedRepository);
        mFeedPresenter = new FeedPresenter(feedInteractor, this);
        feedRecyclerView.setAdapter(mFeedPresenter);
    }

    @Override
    public void onStart() {
        super.onStart();

        mFeedPresenter.register();
    }

    @Override
    public void onStop() {
        mFeedPresenter.unregister();

        super.onStop();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }
}
